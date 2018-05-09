package com.example.chetanmuliya.studentpanel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.chetanmuliya.studentpanel.R;

public class TestScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_schedule);
    }

    public void onViewTestSchedule(View view) {
        Intent intent = new Intent(this,SelectCourseDailogActivity.class);
        intent.putExtra("onSelect","testschedule");
        intent.putExtra("status","testschedule");
        startActivity(intent);
    }

    public void onViewTimeTable(View view) {
        Intent intent = new Intent(this,SelectCourseDailogActivity.class);
        intent.putExtra("onSelect","timetable");
        intent.putExtra("status","timetable");
        startActivity(intent);
    }
}
