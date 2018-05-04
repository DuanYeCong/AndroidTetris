package com.example.androidtetris;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by duanyecong on 17-9-28.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    private String TAG = "DatabaseHelper";
    private static final String DB_NAME = "game_database.db";//数据库文件名
    private static final int DB_VERSION = 1;//数据库版本号
    public static final String TABLE_NAME = "table_person";//数据库表名

    //构造方法用于创建数据库文件，并指定版本号
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.e(TAG, "onCreate: create db");
        mContext = context;
    }
    //该方法用于创建数据的表
    //db:代表被操作的数据库对象
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + TABLE_NAME + "(_id integer primary key autoincrement, player_name varchar, player_score integer)";
        db.execSQL(sql);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onCreate: create table");
    }
    //当前数据库版本更新时，执行该方法
    //oldVersion：旧版本
    //newVersion:新版本
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            Log.d("1606", "数据库版本更新");
        }

    }

}