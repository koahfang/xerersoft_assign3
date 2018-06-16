package com.example.user.exam1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user";
    private static final String TABLE_NAME = "ex_user";
    private static final int VERSION_DATABASE = 1;
    private static  final String user_id = "user_id";
    private static  final String user_title = "user_title";
    private static  final String user_description = "user_description";
    private SQLiteDatabase db;

    private static  final  String CREATE_DATABASE = "CREATE TABLE "+TABLE_NAME +"("+user_id+" INTEGER PRIMARY KEY,"+user_title+" VARCHAR2(50),"+user_description+" VARCHAR2(50)"+");";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null,VERSION_DATABASE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        Log.d("DATABASE UPGRADE","UPGRADE DATABASE");
        onCreate(db);
    }
    public void insertUser(String user_title,String user_description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_title",user_title);
        values.put("user_description",user_description);
        db.insertOrThrow(TABLE_NAME,null,values);
    }
    public void updateUser(String id,String title, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_title",user_title);
        values.put("user_description",user_description);

        db.update(TABLE_NAME,values,user_id+ " = ? ",new String[] {id});
        Log.d("UPDATE COLUMN","Update Column Complete");
    }

    public void deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,user_id+" = ? ",new String[] {id});
        Log.d("DELETE COLUMN","Delete Column Complete");
    }

    public Cursor SelectAllData(){
    try{
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "SELECT " + user_id+ " As _id, * FROM " +TABLE_NAME;
        Cursor cursor = db.rawQuery(strSQL,null);

        return cursor;
    }catch (Exception e){
        return null;
    }
    }
}
