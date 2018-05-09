package com.example.chetanmuliya.studentpanel.helper;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by domaine on 25/04/18.
 */

public  class CustomProgressDialog {

    public static ProgressDialog progressDialog;
    public Context c;

    public CustomProgressDialog(Context ctx) {
        this.c = ctx;
        progressDialog=new ProgressDialog(ctx);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public  static void  showProgressDialog(){
        if(progressDialog.isShowing())
            progressDialog.dismiss();
        progressDialog.show();
    }

    public  static void  hideProgressDialog(){
        progressDialog.dismiss();
    }
    public static void addMessage(String msg){
        progressDialog.setMessage(msg);
    }
}
