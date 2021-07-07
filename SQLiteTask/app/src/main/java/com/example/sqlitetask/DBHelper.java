package com.example.sqlitetask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final  String DBNAME = "users.db";

    public DBHelper(Context context) {
        super(context, "users.db",null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(email TEXT Primary Key,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop table if exists users");

    }
    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Boolean insertData (String email,String passwordmd5){
        SQLiteDatabase myDB = this.getReadableDatabase();
        passwordmd5 = md5(passwordmd5);
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",passwordmd5);
        long result = myDB.insert("users",null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ?",new String[] {email});
        if(cursor.getCount()>0){
            return  true;
        }else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ? and password = ?",new String[] {email,password});
        if(cursor.getCount()>0){
            return  true;
        }else {
            return false;
        }
    }
    public Cursor getAllUsers() {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users", null);
        return cursor;
    }
}
