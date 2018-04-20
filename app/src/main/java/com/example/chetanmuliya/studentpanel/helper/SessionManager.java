package com.example.chetanmuliya.studentpanel.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by chetanmuliya on 12/14/2017.
 */

public class SessionManager {

    private static String TAG=SessionManager.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context ctx;

    int PRIVATE_MODE=0;
    private static final String PREF_NAME="DomainLogin";
    private static final String KEY_IS_LOGGEDIN="isLoggedIn";

    public SessionManager(Context ctx) {
        this.ctx = ctx;
        pref=ctx.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=pref.edit();
    }

    public void setLogin(boolean loggedIn){
        editor.putBoolean(KEY_IS_LOGGEDIN,loggedIn);
        editor.commit();
        Log.d(TAG, "login session modified");
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN,false);
    }

    public  void setStudentCourses(Map<String,String> courses){
        for (int i=0;i<courses.size();i++){
            editor.putString("course1",courses.get("course1"));
            editor.putString("course2",courses.get("course2"));
            editor.putString("course3",courses.get("course3"));
            editor.putString("course4",courses.get("course4"));
            editor.commit();
            Log.d(TAG, "courses session modified ");
        }
    }

    public String getStudentCourses(String value){

        return pref.getString(value,"default");
    }

    public void setSize(int length) {
        editor.putInt("courseSize",length);
        editor.commit();
        Log.d(TAG, "setSize: "+length);
    }
    public int getSize(){
        return  pref.getInt("courseSize",0);
    }
    public void clearEditor(){
        editor.clear();
    }
}
