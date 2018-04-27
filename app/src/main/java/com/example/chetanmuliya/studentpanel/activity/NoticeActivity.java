package com.example.chetanmuliya.studentpanel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.adapter.NoticeAdapter;
import com.example.chetanmuliya.studentpanel.app.ApiClient;
import com.example.chetanmuliya.studentpanel.app.ApiInterface;
import com.example.chetanmuliya.studentpanel.helper.CustomProgressDialog;
import com.example.chetanmuliya.studentpanel.helper.SQLiteLoginHandler;
import com.example.chetanmuliya.studentpanel.model.NoticeModel;
import com.example.chetanmuliya.studentpanel.model.Student;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeActivity extends AppCompatActivity {

    private  String username;
    private NoticeAdapter noticeAdapter;
    private CustomProgressDialog progressDialog;
    private RecyclerView noticeRV;
    private TextView status;
    private SQLiteLoginHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        //toolbar
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Announcement");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //RV
        noticeRV = (RecyclerView)findViewById(R.id.noticeRV);
        noticeRV.setHasFixedSize(true);
        noticeRV.setLayoutManager(new LinearLayoutManager(this));
        status = (TextView)findViewById(R.id.noticeMessage);
        //progressDialog
        progressDialog=new CustomProgressDialog(this);
        progressDialog.addMessage("Please wait loading... Announcement");
        progressDialog.showProgressDialog();
        //DB
        db = new SQLiteLoginHandler(getApplicationContext());
        List<Student> studentList = db.getStudentdetails();
        username = studentList.get(0).getUsername();
        fetchNotice();
    }

    private void fetchNotice() {
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Log.d("******", "fetchNotice: username" + username);
        Call<List<NoticeModel>> call =apiService.getNotice(username);
        call.enqueue(new Callback<List<NoticeModel>>() {
            @Override
            public void onResponse(Call<List<NoticeModel>> call, Response<List<NoticeModel>> response) {
                progressDialog.hideProgressDialog();
                Log.e("***", "onResponse: "+new Gson().toJson(response));
                List<NoticeModel> data=response.body();
                if(data.get(0).getStatus().contains("No Message")){
                     noticeRV.setVisibility(View.GONE);
                     status.setVisibility(View.VISIBLE);
                     status.setText(data.get(0).getStatus());
                }else {
                    noticeAdapter = new NoticeAdapter(getApplicationContext(), data);
                    Log.d("retrofit", "onResponse: " + data.size());
                    noticeRV.setAdapter(noticeAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<NoticeModel>> call, Throwable t) {
                progressDialog.hideProgressDialog();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),"Failed to load annocement",Toast.LENGTH_LONG).show();
                Log.d("retrofit", "onResponse: " + t.toString());
            }
        });
    }
}
