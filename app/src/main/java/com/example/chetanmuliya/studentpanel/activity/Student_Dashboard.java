package com.example.chetanmuliya.studentpanel.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.app.AppController;
import com.example.chetanmuliya.studentpanel.helper.SQLiteLoginHandler;
import com.example.chetanmuliya.studentpanel.helper.SessionManager;
import com.example.chetanmuliya.studentpanel.libraryClass.CircularNetworkImageView;
import com.example.chetanmuliya.studentpanel.model.Student;

import java.util.List;

public class Student_Dashboard extends AppCompatActivity {

    private  TextView studentName;
    private CircularNetworkImageView studentProfilepic;
    private SQLiteLoginHandler db;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard);
        getWidgets();
        //data
        db=new SQLiteLoginHandler(getApplicationContext());
        session=new SessionManager(getApplicationContext());

        List<Student> studentList=db.getStudentdetails();
        //imageloder
        ImageLoader imageLoader= AppController.getInstance().getImageLoader();
        studentName.setText(studentList.get(0).getName());
        studentProfilepic.setImageUrl(studentList.get(0).getProfilePic_Url(),imageLoader);
        studentProfilepic.setBorderColor(Color.WHITE);
        studentProfilepic.setBorderWidth(5);
        studentProfilepic.addShadow();
    }

    private void getWidgets() {
        studentName=(TextView)findViewById(R.id.studentDashboardName);
        studentProfilepic=(CircularNetworkImageView)findViewById(R.id.homeprofilePic);
    }

    public void getAttendance(View view) {
        Intent intent=new Intent(Student_Dashboard.this,CoursesDetails.class);
        intent.putExtra("status","attendance");
        startActivity(intent);
    }

    public void oncoursesEnrolled(View view) {
        Intent intent=new Intent(Student_Dashboard.this,CoursesDetails.class);
        intent.putExtra("status","course");
        startActivity(intent);
    }

    public void getAnnouncements(View view) {
        Intent intent=new Intent(Student_Dashboard.this,NoticeActivity.class);
        startActivity(intent);
    }

    public void getTestSchedule(View view) {
    }

    public void getTestReport(View view) {
    }

    public void getStudentProfile(View view) {
        Intent intent=new Intent(Student_Dashboard.this,Profile_Student.class);
        startActivity(intent);
    }
}
