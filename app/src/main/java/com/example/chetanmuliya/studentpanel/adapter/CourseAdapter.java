package com.example.chetanmuliya.studentpanel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chetanmuliya.studentpanel.R;

import java.util.Set;

/**
 * Created by chetanmuliya on 12/25/2017.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    String[] courseName;
    String status;
    public CourseAdapter(String[] courseName,String status) {
        this.courseName=courseName;
        this.status=status;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View row=inflater.inflate(R.layout.course_layout_row_design,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.courseNameTv.setText(courseName[position]);
        if(status.equals("attendance")) {
            holder.courseNameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return courseName.length;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView courseNameTv;

        public MyViewHolder(View itemView) {
            super(itemView);

            courseNameTv=(TextView)itemView.findViewById(R.id.course_name);
        }
    }
}
