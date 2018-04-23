package com.example.chetanmuliya.studentpanel.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.app.AppConfig;
import com.example.chetanmuliya.studentpanel.app.AppController;
import com.example.chetanmuliya.studentpanel.helper.SQLiteLoginHandler;
import com.example.chetanmuliya.studentpanel.helper.SessionManager;
import com.example.chetanmuliya.studentpanel.model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputUsername;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteLoginHandler db;
    public static List<Student> studentList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        inputUsername = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
       // btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        //session
        session=new SessionManager(getApplicationContext());
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
         student=new Student();
        //db
        db=new SQLiteLoginHandler(getApplicationContext());
        studentList=new ArrayList<>();
        //session.setLogin(false);
       if(session.isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this, Student_Dashboard.class);
            startActivity(intent);
            finish();
        }
    }

    public void onLogin(View view) {
        String username=inputUsername.getText().toString().trim();
        String password=inputPassword.getText().toString().trim();

        if(!username.isEmpty() && !password.isEmpty()){
            checkLogin(username,password);
        }else{
            Toast.makeText(getApplicationContext(),"username or password is empty",Toast.LENGTH_LONG).show();
        }
    }

    private void checkLogin(final String username, final String password) {
        String tag_String_login="req_login";
        pDialog.setMessage("logging in...");
        showDialog();

        StringRequest req=new StringRequest(Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+ response.toString());
                hideDialog();

                try{
                    JSONObject jObj=new JSONObject(response);
                    boolean error= jObj.getBoolean("error");

                    if(!error){
                        session.setLogin(true);

                         String profilePic_Url=jObj.getString("photo");
                         String name=jObj.getString("St_name");
                         String fullname=jObj.getString("St_name");
                         String username=jObj.getString("username");
                         String address=jObj.getString("Paddress");
                         String mobile_no=jObj.getString("Cmobile_number");
                         String school=jObj.getString("School");
                         String emailid=jObj.getString("emailid");
                         String joiningId=jObj.getString("Joining_date");
                         String DOB=jObj.getString("DOB");
                             JSONArray course = jObj.getJSONArray("course");
                        String[] studentCourse=new String[course.length()];
                        session.setSize(course.length());
                       // Set<String> courseSet=new HashSet<>();
                        Map<String,String> courseMap=new HashMap<>();
                             for(int i=0;i<course.length();i++){
                                 JSONObject aObject=course.getJSONObject(i);
                                 String course1=aObject.optString("course").toString();
                                 studentCourse[i]=course1;
                                 //courseSet.add(course1);
                                 Log.d(TAG, "onResponse: course "+course1);
                             }

                        if(course.length()>0) {
                            courseMap.put("course1",studentCourse[0]);
                        }
                        if(course.length()>1) {
                            courseMap.put("course2", studentCourse[1]);
                        }
                        if(course.length()>2) {
                            courseMap.put("course3", studentCourse[2]);
                        }
                        if(course.length()>3)
                        courseMap.put("course4", studentCourse[3]);

                        Log.d(TAG, "onResponse: studentCourse[i] "+courseMap.toString());
                        session.setStudentCourses(courseMap);


                        db.addUser(name,fullname,username,address,mobile_no,joiningId,profilePic_Url,school,emailid,DOB);

                        Intent intent = new Intent(LoginActivity.this,
                                Student_Dashboard.class);
                        startActivity(intent);
                        finish();
                    }else{
                        String errorMsg=jObj.getString("error_msg");
                        print(errorMsg);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                    print("json exeception"+e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error.getMessage() );
                print(error.getMessage());
                hideDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> params=new HashMap<String,String>();
                params.put("username",username);
                params.put("password",password);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(req,tag_String_login);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private void print(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}
