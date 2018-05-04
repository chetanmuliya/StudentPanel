package com.example.chetanmuliya.studentpanel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.adapter.SelectCourseAdapter;
import com.example.chetanmuliya.studentpanel.helper.SessionManager;

import java.util.List;

public class SelectCourseDailogActivity extends AppCompatActivity {

    private SelectCourseAdapter adapter;
    private String[] courseName;
    private String[] batch;
    private String status;
    private SessionManager session;
    private RecyclerView selectCourseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_course_dailog);

        selectCourseList = (RecyclerView)findViewById(R.id.selectCourseList);
      if(getIntent()!=null){
          status =getIntent().getExtras().getString("status");
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

        adapter = new SelectCourseAdapter(batch,status,courseName,this);
        selectCourseList.setLayoutManager(new LinearLayoutManager(this));
        selectCourseList.setHasFixedSize(true);
        selectCourseList.setAdapter(adapter);
    }
}
