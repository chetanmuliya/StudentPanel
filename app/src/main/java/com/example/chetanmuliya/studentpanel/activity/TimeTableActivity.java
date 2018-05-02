
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
    private CustomProgressDialog customProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        customProgressDialog = new CustomProgressDialog(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle("Time Table");
        webView=(WebView)findViewById(R.id.webview);


        getWindow().setFeatureInt(Window.FEATURE_PROGRESS,Window.PROGRESS_VISIBILITY_ON);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int progress) {
                setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if(progress == 100)
                    customProgressDialog.hideProgressDialog();
                setTitle(R.string.app_name);
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().getDisplayZoomControls();
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        //webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.setWebViewClient(new WebViewClient());

        fetchTimeTable();
    }

    private void fetchTimeTable() {
        CustomProgressDialog.addMessage("Loading... timetable");
        CustomProgressDialog.showProgressDialog();
        ApiInterface apiInterface= ApiClient.getClient().create((ApiInterface.class));
        Call<TimeTableModel> testRequestStatusCall=apiInterface.getTimeTable("Kota01-01m12m-01");
        testRequestStatusCall.enqueue(new Callback<TimeTableModel>() {
            @Override
            public void onResponse(Call<TimeTableModel> call, Response<TimeTableModel> response) {
                Display display = getWindowManager().getDefaultDisplay();
                int width=display.getWidth();

                //String data="<html><head><title>Example</title><meta name=\"viewport\"\"content=\"width="+width+", initial-scale=0.65 \" /></head>";
                //data=data+"<body><center><a href =\""+response.body().getUrl()+"\" /></a></center></body></html>";
                //webView.loadData(data, "text/html", null);
                webView.loadUrl(response.body().getUrl());
                //webView.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + response.body().getUrl(), "text/html", "UTF-8", null);
                if (response == null) {
                    Toast.makeText(getApplicationContext(), "Couldn't fetch the data! Please try again.", Toast.LENGTH_LONG).show();
                    return;
                }

                customProgressDialog.hideProgressDialog();
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
