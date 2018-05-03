package com.example.chetanmuliya.studentpanel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.model.TestRecord;

import java.util.List;

/**
 * Created by domaine on 03/05/18.
 */

public class TestRecordAdapter extends RecyclerView.Adapter<TestRecordAdapter.MyViewHolder> {

    List<TestRecord> testRecordList;
    TestRecord testRecordData;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.custom_test_record,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        testRecordData = testRecordList.get(position);
        holder.topic.setText(testRecordList.get(position).getTopic());
        holder.date.setText(testRecordList.get(position).getDate());
        holder.marksobtained.setText(testRecordList.get(position).getObtMarks());
        holder.outOfmarks.setText(testRecordList.get(position).getMaxMarks());
    }

    @Override
    public int getItemCount() {
        return testRecordList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView topic,date,marksobtained,outOfmarks;

        public MyViewHolder(View itemView) {
            super(itemView);
            topic = (TextView)itemView.findViewById(R.id.topic);
            date = (TextView)itemView.findViewById(R.id.date);
            marksobtained = (TextView)itemView.findViewById(R.id.marksObt);
            outOfmarks = (TextView)itemView.findViewById(R.id.outOf);
        }

    }
}
