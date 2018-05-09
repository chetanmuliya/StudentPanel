package com.example.chetanmuliya.studentpanel.activity;

import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.adapter.CourseAdapter;
import com.example.chetanmuliya.studentpanel.helper.SessionManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CoursesDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    CourseAdapter adapter;
    SessionManager session;
    String[] courseName;
    String[] batch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_details);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("Enrolled Courses");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        session=new SessionManager(getApplicationContext());
        int size=session.getSize();
        courseName=new String[size];
        batch=new String[size];

        if(courseName.length>=1){
        courseName[0]=session.getStudentCourses("course1");
        batch[0]=session.getStudentCourses("batch1");}
        if(courseName.length>2){
        courseName[1]=session.getStudentCourses("course2");
        batch[1]=session.getStudentCourses("batch2");}
        if(courseName.length>3) {
            batch[2] = session.getStudentCourses("batch3");
            courseName[2] = session.getStudentCourses("course3");
            courseName[3] = session.getStudentCourses("course4");
            batch[3] = session.getStudentCourses("batch4");
        }

        Log.d("*****", "onCreate: courseName"+courseName[0]);
        Log.e("*****", "onCreate: batch"+batch[1]);

        adapter = new CourseAdapter(courseName,batch);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
