
package com.example.chetanmuliya.studentpanel.model;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TestRecord {

    @SerializedName("date")
    private String mDate;
    @SerializedName("max_marks")
    private String mMaxMarks;
    @SerializedName("obt_marks")
    private String mObtMarks;
    @SerializedName("topic")
    private String mTopic;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getMaxMarks() {
        return mMaxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        mMaxMarks = maxMarks;
    }

    public String getObtMarks() {
        return mObtMarks;
    }

    public void setObtMarks(String obtMarks) {
        mObtMarks = obtMarks;
    }

    public String getTopic() {
        return mTopic;
    }

    public void setTopic(String topic) {
        mTopic = topic;
    }

}
