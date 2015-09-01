package com.zhixiangzhonggong.tiebaobei.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.Constants;


public class DBHelper extends SQLiteOpenHelper{
    private static DBHelper mInstance = null;

    public DBHelper(Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static DBHelper getInstance(Context context, String databaseName,
                                       SQLiteDatabase.CursorFactory factory, int version) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new DBHelper(context, databaseName,factory,version);
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create tables

        db.execSQL(Constants.CREATE_CAR_INFORMATION_TABLE);
        db.execSQL(Constants.CREATE_PICTURE_URL_TABLE);
        db.execSQL(Constants.CREATE_USER_ACCOUNT_INFORMATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {


        db.execSQL(Constants.DROP_CAR_INFORMATION_TABLE);
        db.execSQL(Constants.DROP_PICTURE_URL_TABLE);
        db.execSQL(Constants.DROP_USER_ACCOUNT_INFORMATION_TABLE);
        onCreate(db);
    }
}
