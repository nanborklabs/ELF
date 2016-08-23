package com.elf.UserPrefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nandhu on 23/8/16.
 */
public class MyPrefs {

    private final String PREFS = "ELF_PARENT";
    private Context mContext;
    private SharedPreferences sf;
    public MyPrefs(Context mContext) {
        this.mContext =mContext;
        sf = mContext.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
    }


    public String getStudentId(){

        String Id = sf.getString("studentid","null");
        if (Id.equals("null")){
           throw new NullPointerException("Student id is null");
        }else {
            return Id;

        }

    }

    public String getStandard(){
        String Id = sf.getString("standard","null");
        if (Id.equals("null")){
return  "10";
        }else {
            return Id;

        }
    }

    public String getCityName(){
        String Id = sf.getString("cityname","null");
        if (Id.equals("null")){
            throw new NullPointerException("lcoation id is null");
        }else {
            return Id;

        }
    }
    public String getSchoolName(){
        String Id = sf.getString("schoolname","null");
        if (Id.equals("null")){
            return  "School Name";
        }else {
            return Id;

        }
    }

    public String getBoardName(){
        String Id = sf.getString("boardname","null");
        if (Id.equals("null")){
           return "Null Board";
        }else {
            return Id;

        }


    }
    public String  getStudentName(){
        String Id = sf.getString("firstname","null");
        if (Id.equals("null")){
            throw new NullPointerException("Student id is null");
        }else {
            return Id;

        }
    }




}
