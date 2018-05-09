
package com.example.chetanmuliya.studentpanel.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.app.ApiClient;
import com.example.chetanmuliya.studentpanel.app.ApiInterface;
import com.example.chetanmuliya.studentpanel.helper.CustomProgressDialog;
import com.example.chetanmuliya.studentpanel.model.TimeTableModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeTableActivity extends AppCompatActivity {

    private WebView webView;
    private String onSelect;
    private String batch;
    private CustomProgressDialog customProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        customProgressDialog = new CustomProgressDialog(this);
          if(getIntent()!=null){
              onSelect=getIntent().getStringExtra("onSelect");
              batch=getIntent().getStringExtra("selectedBatch");
          }
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if(onSelect.equals("timetable")) {
            toolbar.setTitle("Time Table");
        }else {
            toolbar.setTitle("Test Schedule");
        }
        webView=(WebView)findViewById(R.id.webview);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS,Window.PROGRESS_VISIBILITY_ON);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().getDisplayZoomControls();
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.setWebViewClient(new WebViewClient());

        fetchTimeTable();
    }

    private void fetchTimeTable() {
        CustomProgressDialog.addMessage("Loading... timetable");
        CustomProgressDialog.showProgressDialog();
        ApiInterface apiInterface= ApiClient.getClient().create((ApiInterface.class));
        Call<TimeTableModel> testRequestStatusCall = null;
        Call<TimeTableModel> testSheduleCall = null;
        if(onSelect.equals("timetable")) {
            Log.d("********", "timetable: "+batch);
             testRequestStatusCall = apiInterface.getTimeTable(batch);
            testRequestStatusCall.enqueue(new Callback<TimeTableModel>() {
                @Override
                public void onResponse(Call<TimeTableModel> call, final Response<TimeTableModel> response) {

                    if (response == null) {
                        Toast.makeText(getApplicationContext(), "Couldn't fetch the data! Please try again.", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }else {
                        webView.loadUrl(response.body().getUrl());
                        customProgressDialog.hideProgressDialog();
                    }
                    Log.d("retrofit", "onResponse: "+ new Gson().toJson(response.body()));
                }

                @Override
                public void onFailure(Call<TimeTableModel> call, Throwable t) {
                    customProgressDialog.hideProgressDialog();
                    Log.d("response error ", "onFailure: "+t.toString());
                }
            });
        }else if(onSelect.equals("testschedule")){
            Log.d("********", "test schedule: "+batch);
            testSheduleCall = apiInterface.getTestSchedule(batch);
            testSheduleCall.enqueue(new Callback<TimeTableModel>() {
                @Override
                public void onResponse(Call<TimeTableModel> call, Response<TimeTableModel> response) {
                    webView.loadUrl(response.body().getUrl());
                    if (response == null) {
                        Toast.makeText(getApplicationContext(), "Couldn't fetch the data! Please try again.", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }else {
                        webView.loadUrl(response.body().getUrl());
                        customProgressDialog.hideProgressDialog();
                    }
                    Log.d("retrofit", "onResponse: "+ new Gson().toJson(response.body()));
                }

                @Override
                public void onFailure(Call<TimeTableModel> call, Throwable t) {
                    customProgressDialog.hideProgressDialog();
                    Log.d("response error ", "onFailure: "+t.toString());
                }
            });
        }

    }
}
