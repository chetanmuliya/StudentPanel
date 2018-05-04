
package com.example.chetanmuliya.studentpanel.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AttendanceMonth {

    @SerializedName("attendence_count")
    private Long mAttendenceCount;
    @SerializedName("month")
    private String mMonth;

    public Long getAttendenceCount() {
        return mAttendenceCount;
    }

    public void setAttendenceCount(Long attendenceCount) {
        mAttendenceCount = attendenceCount;
    }

    public String getMonth() {
        return mMonth;
    }

    public void setMonth(String month) {
        mMonth = month;
    }

}
