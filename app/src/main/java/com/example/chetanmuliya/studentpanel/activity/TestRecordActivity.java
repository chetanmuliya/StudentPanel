package com.example.chetanmuliya.studentpanel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.adapter.TestRecordAdapter;
import com.example.chetanmuliya.studentpanel.app.ApiClient;
import com.example.chetanmuliya.studentpanel.app.ApiInterface;
import com.example.chetanmuliya.studentpanel.model.TestRecord;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestRecordActivity extends AppCompatActivity {

    private RecyclerView testRecordRV;
    private List<TestRecord> testRecordList;
    private TestRecordAdapter adapter;
    private String username,batch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_record);
        testRecordRV=(RecyclerView)findViewById(R.id.testRecordListRV);
        testRecordRV.setHasFixedSize(true);
        testRecordRV.setLayoutManager(new LinearLayoutManager(this));
        if(getIntent()!=null){
            batch = getIntent().getExtras().getString("selectedBatch");
        }
        fetchTestRecord();
    }

    private void fetchTestRecord() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TestRecord>> call = apiInterface.getTestRecord("username",batch);
        call.enqueue(new Callback<List<TestRecord>>() {
            @Override
            public void onResponse(Call<List<TestRecord>> call, Response<List<TestRecord>> response) {
                Log.d("******", "onResponse: "+new Gson().toJson(response));
            }

            @Override
            public void onFailure(Call<List<TestRecord>> call, Throwable t) {

            }
        });
    }
}
