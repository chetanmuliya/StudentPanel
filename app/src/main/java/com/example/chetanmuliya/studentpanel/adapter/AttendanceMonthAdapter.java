package com.example.chetanmuliya.studentpanel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.helper.CustomOnCLickListener;
import com.example.chetanmuliya.studentpanel.model.AttendanceMonth;

import java.util.List;

/**
 * Created by domaine on 24/04/18.
 */

public class AttendanceMonthAdapter extends RecyclerView.Adapter<AttendanceMonthAdapter.MyViewHolder> {


    List<AttendanceMonth> attendanceMonthList;
    AttendanceMonth attendanceMonthData;
    Context ctx;
    CustomOnCLickListener customOnCLickListener;

    public AttendanceMonthAdapter(List<AttendanceMonth> attendanceMonthList, Context c,CustomOnCLickListener customOnCLickListener) {
        this.attendanceMonthList = attendanceMonthList;
        this.ctx=c;
        this.customOnCLickListener = customOnCLickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View row=inflater.inflate(R.layout.custom_monthly_attendance_lay,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
       attendanceMonthData = attendanceMonthList.get(position);
       holder.month.setText(attendanceMonthData.getMonth());
       holder.attendance_count.setText(String.valueOf(attendanceMonthData.getAttendenceCount()));
       holder.detailAttendance.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               customOnCLickListener.onCLick(v,position);
           }
       });
    }

    @Override
    public int getItemCount() {
        return attendanceMonthList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView month;
        private TextView attendance_count;
        private Button detailAttendance;

        public MyViewHolder(View itemView) {
            super(itemView);

            month = (TextView)itemView.findViewById(R.id.attendanceMonth);
            attendance_count = (TextView)itemView.findViewById(R.id.attendanceCount);
            detailAttendance = (Button)itemView.findViewById(R.id.detailAttendanceBtn);
        }
    }
}

