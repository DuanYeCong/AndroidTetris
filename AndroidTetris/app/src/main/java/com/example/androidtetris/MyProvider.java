package com.example.androidtetris;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


/**
 * Created by duanyecong on 17-10-9.
 */

public class MyProvider extends ContentProvider {
    //数据库助手
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    //uri路径
    public static final String PATH_PLAYER="player";
    //匹配编码
    public static final int  CODE_PLAYER=1;
    //服务端授权
    private static final String AUTHORITIES = "com.example.androidtetris.provider";
    //匹配Uri路径
    private static UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITIES, PATH_PLAYER, CODE_PLAYER);
    }

    @Override
    public boolean onCreate() {
        helper = new DatabaseHelper(getContext());//初始化
        db = helper.getReadableDatabase();
        return false;
    }

    @Override
    //当其他程序向本应用查询数据时，执行该方法，当前程序向自身数据库进行查询操作
    public Cursor query(Uri uri, String[] projection,String selection, String[]
            selectionArgs,String sortOrder) {
        Cursor cursor =null;
        cursor = db.query(DatabaseHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri,ContentValues values) {
        return null;
    }

    @Override
    public int update(Uri uri,ContentValues values,String selection, String[]
            selectionArgs) {
        return 0;
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}
