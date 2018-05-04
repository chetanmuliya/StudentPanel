
package com.example.chetanmuliya.studentpanel.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DetailAttendanceData {

    @SerializedName("day")
    private int mDay;
    @SerializedName("status")
    private String mStatus;

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
