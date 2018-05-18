package com.studyproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 李涛
 * @description
 * @Date 2018/3/12.
 */


public class TitleShowModle  extends SQLiteOpenHelper {

    private String TABLE_NAME = "sqlitetable";
    private String PUNLIC_ID = "id";
    private String PUNLIC_NAME = "name";
    private static Map<String,TitleShowModle> map = new LinkedHashMap<>();
    private long sqliteLong;


    public static TitleShowModle getInstance(Context context,  String name){

        TitleShowModle  db  =  map.get(name);
        if(db!=null){
            return db;
        }

        synchronized (TitleShowModle.class){
            if(map.get(name)==null){
                map.put(name,new TitleShowModle(context,name));
            }
            return map.get(name);
        }
    }



    public TitleShowModle(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  "+ TABLE_NAME
                +"("+PUNLIC_ID+"  integer primary key autoincrement, "
                +  PUNLIC_NAME+" TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /**
     * 插入数据
     * @param list
     */
    public void insertData(ArrayList<String> list){

            SQLiteDatabase db = getWritableDatabase();
            for (int i=0;i<list.size();i++){
                ContentValues values = new ContentValues();
                Log.i("bbb","插入的"+list.get(i));
                values.put(PUNLIC_NAME,list.get(i));

               try {
                    sqliteLong = db.insert(TABLE_NAME,null,values);
                }catch (SQLException e){
                    Log.e("Exception","SQLException"+String.valueOf(e.getMessage()));
                    e.printStackTrace();
                }

        }
         db.close();
    }



    public  ArrayList<String> selectData(){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            list.add(cursor.getString(cursor.getColumnIndex(PUNLIC_NAME)));
        }

        return list;
    }


}
