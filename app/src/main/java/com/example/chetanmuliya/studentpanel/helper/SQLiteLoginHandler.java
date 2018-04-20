package com.example.chetanmuliya.studentpanel.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chetanmuliya.studentpanel.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chetanmuliya on 12/14/2017.
 */

public class SQLiteLoginHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteLoginHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DomainLogin";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    private static final String STUDENT_NAME = "name";
    private static final String FULL_NAME = "fullname";
    private static final String USERNAME = "username";
    private static final String ADDRESS = "address";
    private static final String MOBILENO = "mobileno";
    private static final String JOINING_DATE = "joining_date";
    private static final String PHOTO = "photo";
    private static final String SCHOOL = "school";
    private static final String EMAILID = "emailid";
    private static final String DOB = "dob";

    private static final String STATUS = "status";

    public SQLiteLoginHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + STUDENT_NAME + " TEXT,"
                + FULL_NAME + " TEXT,"
                + USERNAME + " TEXT,"
                + ADDRESS + " TEXT,"
                + MOBILENO + " TEXT,"
                + JOINING_DATE + " TEXT,"
                + PHOTO + " TEXT,"
                + SCHOOL + " TEXT,"
                + DOB + " TEXT,"
                + EMAILID + " TEXT );";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String student_name, String fullname,String username,String address,String mobileno,String joining_date,String photo,String school,String emailid,String dob) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student_name);
        values.put(FULL_NAME, fullname);
        values.put(USERNAME, username);
        values.put(ADDRESS, address);
        values.put(MOBILENO, mobileno);
        values.put(JOINING_DATE, joining_date);
        values.put(PHOTO, photo);
        values.put(SCHOOL, school);
        values.put(EMAILID, emailid);
        values.put(DOB, dob);

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("username", cursor.getString(1));
            user.put("status", cursor.getString(2));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
     public List<Student> getStudentdetails(){

        List<Student> studentlist=new ArrayList<>();
        String selectQuery="SELECT * FROM "+TABLE_USER;
         SQLiteDatabase db = this.getReadableDatabase();

         Cursor c=db.rawQuery(selectQuery,null);
         if(c.moveToFirst()){
             do{
                 Student student=new Student();

                 student.setName(c.getString(c.getColumnIndex(STUDENT_NAME)));
                 student.setFullname(c.getString(c.getColumnIndex(FULL_NAME)));
                 student.setUsername(c.getString(c.getColumnIndex(USERNAME)));
                 student.setAddress(c.getString(c.getColumnIndex(ADDRESS)));
                 student.setMobile_no(c.getString(c.getColumnIndex(MOBILENO)));
                 student.setJoiningId(c.getString(c.getColumnIndex(JOINING_DATE)));
                 student.setSchool(c.getString(c.getColumnIndex(SCHOOL)));
                 student.setEmailid(c.getString(c.getColumnIndex(EMAILID)));
                 student.setDOB(c.getString(c.getColumnIndex(DOB)));
                 student.setProfilePic_Url(c.getString(c.getColumnIndex(PHOTO)));

                 studentlist.add(student);

             }while(c.moveToNext());
         }

        return studentlist;
     }
}

