package com.demotest.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * @author 李涛
 * @description
 * @Date 2018/5/9.
 */


public class SqliteDb extends SQLiteOpenHelper {


    private static SqliteDb sqliteDb;
    public static int VERSION = 5;
    private static String SQLITE__NAME = "update_sqlite_test";
    private static String TABLE_NAME = "update_table";
    private static String TEMP_TABLE_NAME = "temp_" + TABLE_NAME;
    //存储历史版本
    private ArrayList<Integer> sqliteVersion = new ArrayList<>();
    //多出字段时数据单引号的拼接
    private StringBuilder builder = new StringBuilder();

    public static SqliteDb getInstance(Context context) {
        if (sqliteDb == null) {
            return sqliteDb = new SqliteDb(context, SQLITE__NAME, null, VERSION);
        }
        return sqliteDb;
    }

    /**
     * 添加历史版本  用于更新
     */
    private void addVersion() {
        sqliteVersion.add(1);
        sqliteVersion.add(2);
        sqliteVersion.add(3);
        sqliteVersion.add(4);
        sqliteVersion.add(5);
    }

    public SqliteDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        addVersion();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        switch (VERSION) {
            case 1:
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name text)");
                break;
            case 2:
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name text,name2 text,name3 text)"); //第一次升级
                break;
            case 3:
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name text,name2 text,name3 text,name4 text)"); //第二次升级
                break;
            case 4:
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name text,name2 text,name3 text,name4 INTEGER)"); //第三次升级
                break;
            case 5:
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name text,name2)"); //第四次升级
                break;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库更新数据迁移
        if (newVersion > oldVersion) {
            judgeVersion(db, oldVersion);
        }
    }


    /**
     * 升级版本数据库
     *
     * @param db
     * @param version
     */
    private void judgeVersion(SQLiteDatabase db, int version) {
        for (int i = sqliteVersion.indexOf(version); i < sqliteVersion.size(); i++) {
            switch (sqliteVersion.get(i)) {
                case 2:
                    updateTableIfAddField(db, TABLE_NAME, 2, sqliteVersion.get(i)); //新添加两个个字段 name2 name3
                    break;
                case 3:
                    updateTableIfAddField(db, TABLE_NAME, 1, sqliteVersion.get(i));//新添加一个字段 name4
                    break;
                case 4:
                    updateTableIfAddField(db, TABLE_NAME, 0, sqliteVersion.get(i));//修改最后一个字段 name4的类型
                    break;
                case 5:
                    updateTableIfDeleteField(db, TABLE_NAME, sqliteVersion.get(i));//修改最后一个字段 name4的类型
                    break;
            }
        }
    }


    /***
     * 数据库升级多出字段的情况瞎
     * @param db
     * @param tableName 当前迁移的表格名子
     * @param addFields  新添加字段的个数   0表示没有添加的字段  也可用作修改字段的数据类型
     */
    private void updateTableIfAddField(SQLiteDatabase db, String tableName, int addFields, int version) {
        TEMP_TABLE_NAME = "temp_" + tableName;
        try {
            db.beginTransaction();
            db.execSQL("ALTER TABLE " + tableName + " RENAME TO  " + TEMP_TABLE_NAME + "");
            createVersionTable(db, version);
            if (addFields > 0) {
                for (int i = 1; i <= addFields; i++) {
                    builder.append(" ''".trim());
                    if (i != addFields) {
                        builder.append(",");
                    }
                }
                db.execSQL("INSERT INTO " + tableName + " SELECT *," + builder.toString() + "FROM  " + TEMP_TABLE_NAME + "");
            } else {
                db.execSQL("INSERT INTO " + tableName + " SELECT * FROM  " + TEMP_TABLE_NAME + "");
            }
            db.execSQL("DROP TABLE  " + TEMP_TABLE_NAME + "");
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("数据库升级失败", "" + e);
        } finally {
            builder.replace(0, builder.length(), "");
            db.endTransaction();
        }
    }


    private  void updateTableIfDeleteField(SQLiteDatabase db, String tableName, int version){

        try{
            db.beginTransaction();
            db.execSQL("ALTER TABLE " + tableName + " RENAME TO  " + TEMP_TABLE_NAME + "");
            createVersionTable(db, version);
            db.execSQL("INSERT INTO " + tableName + "(id,name,name2) SELECT id,name,name2 FROM  " + TEMP_TABLE_NAME + "");
            db.execSQL("DROP TABLE  " + TEMP_TABLE_NAME + "");
            db.setTransactionSuccessful();
        }catch (SQLiteException e){
            Log.e("数据库升级失败", "" + e);
        }finally {
            db.endTransaction();
        }

    }






    private void createVersionTable(SQLiteDatabase db, int version) {
        switch (version) {
            case 1:
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name text)");
                break;
            case 2:
                Log.i("aaa", "更新数据库第一次");
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name text,name2 text,name3 text)"); //第一次升级
                break;
            case 3:
                Log.i("aaa", "更新数据库第二次");
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name text,name2 text,name3 text,name4 text)"); //第二次升级
                break;
            case 4:
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name text,name2 text,name3 text,name4 INTEGER)"); //第三次升级
                break;
            case 5:
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name text,name2)"); //删除三个字段
                break;
        }
    }

    /***
     * 插入數據
     * @param insertData
     */
    public void insertSQlite(ArrayList<String> insertData) {
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < insertData.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("name", insertData.get(i));
            db.insert("update_table", null, values);
        }
        db.close();
    }


    /***
     * 查詢數據
     * @return
     */
    public ArrayList<String> querySQlite() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("update_table", null, null, null, null, null, null);
        ArrayList<String> dataList = new ArrayList<>();
        while (c.moveToNext()) {
            dataList.add(c.getString(c.getColumnIndex("name")));
        }
        return dataList;
    }

}
