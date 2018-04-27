package com.example.chetanmuliya.studentpanel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chetanmuliya.studentpanel.R;
import com.example.chetanmuliya.studentpanel.model.NoticeModel;

import java.util.List;

/**
 * Created by domaine on 22/01/18.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyNoticeViewHolder> {

    private List<NoticeModel> noticeList;
    private NoticeModel noticeModel;
    private Context context;

    public NoticeAdapter(Context ctx, List<NoticeModel> noticeList) {
        this.noticeList = noticeList;
        this.context=ctx;
    }

    @Override
    public MyNoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.custom_notice_layout,parent,false);
        return new MyNoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyNoticeViewHolder holder, int position) {
          noticeModel=noticeList.get(position);
          holder.date.setText(noticeModel.getDate());
          holder.noticetype.setText(noticeModel.getNoticeType());
          holder.subject.setText(noticeModel.getSubject());
          holder.message.setText(noticeModel.getMessage());
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    class MyNoticeViewHolder extends RecyclerView.ViewHolder{

        private  TextView date,noticetype,subject,message;

        public MyNoticeViewHolder(View itemView) {
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.dateTV);
            noticetype=(TextView)itemView.findViewById(R.id.noticeType);
            subject=(TextView)itemView.findViewById(R.id.subjectTv);
            message=(TextView)itemView.findViewById(R.id.messageTv);
        }
    }
}
