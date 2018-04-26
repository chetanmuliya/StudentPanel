package com.example.chetanmuliya.studentpanel.app;

import com.example.chetanmuliya.studentpanel.model.AttendanceMonth;
import com.example.chetanmuliya.studentpanel.model.DetailAttendanceData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by domaine on 24/04/18.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("attendance_record.php")
    Call<List<AttendanceMonth>> getAttendanceMonth(@Field("batch") String batch, @Field("username") String username);

    @FormUrlEncoded
    @POST("detail_attendance_record.php")
    Call<List<DetailAttendanceData>> getDetailAttendanceMonth(@Field("batch") String batch, @Field("username") String username,  @Field("my") String my);
}
