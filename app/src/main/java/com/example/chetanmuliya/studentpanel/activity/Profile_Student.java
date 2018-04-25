package com.example.chetanmuliya.studentpanel.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.app.AppController;
import com.example.chetanmuliya.studentpanel.helper.SQLiteLoginHandler;
import com.example.chetanmuliya.studentpanel.helper.SessionManager;
import com.example.chetanmuliya.studentpanel.libraryClass.CircularNetworkImageView;
import com.example.chetanmuliya.studentpanel.model.Student;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile_Student extends AppCompatActivity {

    private TextView student_name;
    private TextView student_fullname;
    private TextView username;
    private TextView address;
    private TextView mobileNo;
    private TextView school;
    private TextView emailid;
    private TextView joining_date;
    private CircularNetworkImageView profile_pic;
    SQLiteLoginHandler db;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile__student);
        //widgets

        student_name=(TextView)findViewById(R.id.studentName);
        student_fullname=(TextView)findViewById(R.id.student_FullName);
        username=(TextView)findViewById(R.id.username);
        address=(TextView)findViewById(R.id.student_Address);
        mobileNo=(TextView)findViewById(R.id.student_MobileNo);
        school=(TextView)findViewById(R.id.student_SchoolName);
        emailid=(TextView)findViewById(R.id.student_emailId);
        joining_date=(TextView)findViewById(R.id.student_JoiningDate);
        profile_pic=(CircularNetworkImageView) findViewById(R.id.profilePic);
        //data
        db=new SQLiteLoginHandler(getApplicationContext());
        session=new SessionManager(getApplicationContext());

            List<Student> studentList=db.getStudentdetails();

        //imageloder
        ImageLoader imageLoader= AppController.getInstance().getImageLoader();
            student_name.setText(studentList.get(0).getName());
            student_fullname.setText(studentList.get(0).getFullname());
            username.setText(studentList.get(0).getUsername());
            address.setText(studentList.get(0).getAddress());
            mobileNo.setText(studentList.get(0).getMobile_no());
            school.setText(studentList.get(0).getSchool());
            emailid.setText(studentList.get(0).getEmailid());
            joining_date.setText(studentList.get(0).getJoiningId());
            profile_pic.setImageUrl(studentList.get(0).getProfilePic_Url(),imageLoader);
            profile_pic.setBorderColor(Color.WHITE);
            profile_pic.setBorderWidth(5);
            profile_pic.addShadow();
    }

    public void onSignOut(View view) {
        session.setLogin(false);
        session.clearEditor();
        db.deleteUsers();
        Intent intent=new Intent(Profile_Student.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onCourses(View view) {
        Intent intent=new Intent(Profile_Student.this,CoursesDetails.class);
        startActivity(intent);
    }
}
