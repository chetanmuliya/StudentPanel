
package com.example.chetanmuliya.studentpanel.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NoticeModel {

    @SerializedName("date")
    private String mDate;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("notice_type")
    private String mNoticeType;
    @SerializedName("subject")
    private String mSubject;
    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getNoticeType() {
        return mNoticeType;
    }

    public void setNoticeType(String noticeType) {
        mNoticeType = noticeType;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

}
