package com.example.chetanmuliya.studentpanel.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.app.ApiClient;
import com.example.chetanmuliya.studentpanel.app.ApiInterface;
import com.example.chetanmuliya.studentpanel.helper.SQLiteLoginHandler;
import com.example.chetanmuliya.studentpanel.model.PasswordStatusModel;
import com.example.chetanmuliya.studentpanel.model.Student;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText oldPassword,newPassword,confirmPassword;
    private TextInputLayout oldPasswordLay,newPassLay,cnfmPassLay;
    private SQLiteLoginHandler db;
    private ProgressDialog dialog;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Change password");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //db
        db = new SQLiteLoginHandler(this);
        List<Student> student = db.getStudentdetails();
        username = student.get(0).getUsername();
        oldPassword=(EditText)findViewById(R.id.oldPasswordInput);
        newPassword=(EditText)findViewById(R.id.newPasswordInput);
        confirmPassword=(EditText)findViewById(R.id.confirmPasswordInput);

        oldPasswordLay=(TextInputLayout)findViewById(R.id.oldPasswordLay);
        newPassLay=(TextInputLayout)findViewById(R.id.newPasswordLay);
        cnfmPassLay=(TextInputLayout)findViewById(R.id.confirmPasswordLay);

        dialog=new ProgressDialog(this);
        dialog.setMessage("Updating... password");
        dialog.setCanceledOnTouchOutside(false);
    }

    public void resetPassword(View view) {
        dialog.show();
        String oldPass=oldPassword.getText().toString().trim();
        String newPass=newPassword.getText().toString().trim();
        String cnfmPass=confirmPassword.getText().toString().trim();
        changePassword(username,oldPass,newPass,cnfmPass);
    }

    private void changePassword(String username,String oldPass, String newPass, String cnfmPass) {
        if(oldPass.isEmpty()){
            oldPasswordLay.setError("please enter old password");
        }else{
            oldPasswordLay.setErrorEnabled(false);
        }
        if(newPass.isEmpty()){
            newPassLay.setError("please enter new password");
        }else{
            newPassLay.setErrorEnabled(false);
        }
        if(cnfmPass.isEmpty()){
            cnfmPassLay.setError("please enter old password");
        }else{
            cnfmPassLay.setErrorEnabled(false);
        }
        ApiInterface service= ApiClient.getClient().create(ApiInterface.class);
        Call<List<PasswordStatusModel>> call=service.changePassword(username,newPass,oldPass,cnfmPass);
        call.enqueue(new Callback<List<PasswordStatusModel>>() {
            @Override
            public void onResponse(Call<List<PasswordStatusModel>> call, Response<List<PasswordStatusModel>> response) {
                dialog.dismiss();

                Log.d("*****", "onResponse: status"+new Gson().toJson(response.body()));
                String status=response.body().get(0).getStatus();
                if(status.equals("Old Password Does Not Match")){
                    oldPasswordLay.setError("Old Password Does Not Match");
                    return;
                }else {oldPasswordLay.setErrorEnabled(false);}
                if(status.equals("New Password & Confirm Password does not match.")){
                    cnfmPassLay.setError("New Password & Confirm Password does not match.");
                    return;
                }else {cnfmPassLay.setErrorEnabled(false);}

                Toast.makeText(getApplicationContext(),status,Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<List<PasswordStatusModel>> call, Throwable t) {
                Log.e("******", "onFailure: "+t.getMessage() );
                dialog.dismiss();
            }
        });
    }
}
