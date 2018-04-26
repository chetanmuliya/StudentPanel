package com.example.chetanmuliya.studentpanel.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.Toast;

import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.app.ApiClient;
import com.example.chetanmuliya.studentpanel.app.ApiInterface;
import com.example.chetanmuliya.studentpanel.model.DetailAttendanceData;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAttendanceActivity extends AppCompatActivity {

    MaterialCalendarView calendarView;
    String my_Parameter,username,batch,year;
    ProgressDialog progressDialog;
    int pos,month,year2;
    List<DetailAttendanceData> attendanceModelsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_attendance);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading... attendance details");
        progressDialog.show();

        calendarView=(MaterialCalendarView)findViewById(R.id.calendarview1);
        if(getIntent().getExtras()!=null){
            my_Parameter=getIntent().getExtras().getString("my");
            username=getIntent().getExtras().getString("username");
            batch=getIntent().getExtras().getString("batch");
            pos=getIntent().getIntExtra("position",0);
        }
        String[] divide=my_Parameter.split(" ");

        if(divide[0].equals("Jan"))
            month=-1;
        if(divide[0].equals("Feb"))
            month=0;
        if(divide[0].equals("Mar"))
            month=1;
        if(divide[0].equals("April"))
            month=2;
        if(divide[0].equals("May"))
            month=3;
        if(divide[0].equals("Jun"))
            month=4;
        if(divide[0].equals("Jul"))
            month=5;
        if(divide[0].equals("Aug"))
            month=6;
        if(divide[0].equals("Sep"))
            month=7;
        if(divide[0].equals("Oct"))
            month=8;
        if(divide[0].equals("Nov"))
            month=9;
        if(divide[0].equals("Dec"))
            month=10;
        year="20"+divide[1];
        if(year.equals("2017"))
            year2=2017;
        if(year.equals("2018"))
            year2=2018;
        if(year.equals("2019"))
            year2=2019;
        if(year.equals("2020"))
            year2=2020;

        Log.d("split ", "onCreate: "+month);
        Log.d("split ", "onCreate: "+year);

        my_Parameter=my_Parameter.replaceAll("\\s","-");


        attendanceModelsList=new ArrayList<>();
        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(year2, month, 1))
                .setMaximumDate(CalendarDay.from(year2, month+1, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .isCacheCalendarPositionEnabled(true)
                .commit();

        fetchDetailAttendence();

    }

    void fetchDetailAttendence(){
        //detail_attendance.php
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<List<DetailAttendanceData>> call1=apiService.getDetailAttendanceMonth(batch,username,my_Parameter);
        call1.enqueue(new Callback<List<DetailAttendanceData>>() {
            @Override
            public void onResponse(Call<List<DetailAttendanceData>> call, Response<List<DetailAttendanceData>> response) {
                progressDialog.dismiss();
                if(response==null){
                    Toast.makeText(getApplicationContext(),"Please! go back and try again",Toast.LENGTH_LONG);
                }
                List<DetailAttendanceData> items= response.body();

                Log.d("******", "Detail Attendence : retrofit "+new Gson().toJson(response.body()));
               attendanceModelsList.addAll(items);
                Log.d("****  ", "retrofit: " + attendanceModelsList.size());
                Log.d("******", "VALES: batch "+ batch +"username " + username +" myParameter" + my_Parameter);
                initView();
            }

            @Override
            public void onFailure(Call<List<DetailAttendanceData>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Poor Network! Please try again",Toast.LENGTH_LONG);
                Intent intent=new Intent(DetailAttendanceActivity.this,Profile_Student.class);
                startActivity(intent);
                finish();
                Log.d("response ", "onFailure: "+t.toString());
            }
        });
    }
    void initView(){
        final HashSet<CalendarDay> presentDays=new HashSet<>();

        for (int i=0;i<attendanceModelsList.size();i++) {
            if(attendanceModelsList.get(i).getStatus().equals("present"))
                presentDays.add(CalendarDay.from(year2, month+1, attendanceModelsList.get(i).getDay()));
        }
        calendarView.addDecorator(new CalendarCustom(presentDays,getApplicationContext()));
    }

    private class CalendarCustom implements DayViewDecorator {

        private final HashSet<CalendarDay> dates;
        private final Drawable drawable1;

        public CalendarCustom(HashSet<CalendarDay> dates, Context applicationContext) {
            this.dates = dates;
            this.drawable1 = ContextCompat.getDrawable(applicationContext, R.drawable.circle_background);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {

            view.setBackgroundDrawable(drawable1);
            view.addSpan(new ForegroundColorSpan(Color.WHITE));
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.5f));
        }

    }
}


