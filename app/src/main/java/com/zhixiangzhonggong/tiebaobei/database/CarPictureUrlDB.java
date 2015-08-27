package com.zhixiangzhonggong.tiebaobei.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.Constants;
import com.zhixiangzhonggong.tiebaobei.model.CarInformation;
import com.zhixiangzhonggong.tiebaobei.model.UserLoadPictureUrl;
import com.zhixiangzhonggong.tiebaobei.util.LYDateString;

import java.util.ArrayList;

public class CarPictureUrlDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private int size;
    private int itemFinished;
    public CarPictureUrlDB(Context context) {
        dbHelper=DBHelper.getInstance(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }
    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }
    private void openWritableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }



    public int getSize() {
        return size;
    }

    // public methods
    public ArrayList<UserLoadPictureUrl> getAllUserLoadPictureUrls() {

        this.openReadableDB();
        Cursor cursor = db.query(Constants.CAR_PICTURE_URL_TABLE, null,
                null, null,
                null, null, null);
        ArrayList<UserLoadPictureUrl> userLoadPictureUrlArrayList = new ArrayList<UserLoadPictureUrl>();

        while (cursor.moveToNext()) {
            userLoadPictureUrlArrayList.add(getUserLoadPictureUrlFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return userLoadPictureUrlArrayList;
    }

  /*  public ArrayList<CarInformation> getCarInformationByCarType(String  carType) {
        String where = Constants.CAR_TYPE + "= ?" + " AND " + Constants.IS_APPROVED + "= ?";
        String[] whereArgs = { carType, "1"};
        size=0;
        this.openReadableDB();
        Cursor cursor = db.query(Constants.CAR_INFORMATION_TABLE, null,
                where, whereArgs,
                null, null, null);

        size=cursor.getCount();

        ArrayList<CarInformation> carInformations = new ArrayList<CarInformation>();

        itemFinished=0;
        while (cursor.moveToNext()) {
            carInformations.add(getCarInformationFromCursor(cursor));
            itemFinished++;
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return carInformations;
    }*/



    public UserLoadPictureUrl getUserLoadPictureUrlByCarId(long id) {
        String where = Constants.CAR_ID_1 + "= ?";
        String[] whereArgs = { Long.toString(id) };

        // handle exceptions?
        this.openReadableDB();
        Cursor cursor = db.query(Constants.CAR_PICTURE_URL_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        UserLoadPictureUrl userLoadPictureUrl = getUserLoadPictureUrlFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return userLoadPictureUrl;
    }

    private static UserLoadPictureUrl getUserLoadPictureUrlFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                UserLoadPictureUrl userLoadPictureUrl = new UserLoadPictureUrl(

                        cursor.getString(Constants.PICTURE_URL_1_COL),
                        cursor.getString(Constants.PICTURE_URL_2_COL),
                        cursor.getString(Constants.PICTURE_URL_3_COL),
                        cursor.getString(Constants.PICTURE_URL_4_COL),
                        cursor.getString(Constants.PICTURE_URL_5_COL),
                        cursor.getString(Constants.PICTURE_URL_6_COL),
                        cursor.getString(Constants.PICTURE_URL_7_COL),
                        cursor.getString(Constants.PICTURE_URL_8_COL),
                        cursor.getString(Constants.PICTURE_URL_9_COL),
                        cursor.getInt(Constants.CAR_ID_1_COL)

                );
                return userLoadPictureUrl;
            }
            catch(Exception e) {
                return null;
            }
        }
    }


    public long insertUserLoadPictureUrl(UserLoadPictureUrl userLoadPictureUrl) {
        ContentValues cv = new ContentValues();
        // cv.put(Constants.CAR_ID, carInformation.getCarId());
        cv.put(Constants.PICTURE_URL_1, userLoadPictureUrl.getPictureUrl1());
        cv.put(Constants.PICTURE_URL_2,userLoadPictureUrl.getPictureUrl2());
        cv.put(Constants.PICTURE_URL_3, userLoadPictureUrl.getPictureUrl3());
        cv.put(Constants.PICTURE_URL_4,String.valueOf(userLoadPictureUrl.getPictureUrl4()) );
        cv.put(Constants.PICTURE_URL_5, userLoadPictureUrl.getPictureUrl5());
        cv.put(Constants.PICTURE_URL_6, userLoadPictureUrl.getPictureUrl6());
        cv.put(Constants.PICTURE_URL_7, userLoadPictureUrl.getPictureUrl7());
        cv.put(Constants.PICTURE_URL_8, userLoadPictureUrl.getPictureUrl8());
        cv.put(Constants.PICTURE_URL_9, userLoadPictureUrl.getPictureUrl9());
        cv.put(Constants.CAR_ID_1, userLoadPictureUrl.getCarId());

        this.openWritableDB();
        long rowID = db.insert(Constants.CAR_PICTURE_URL_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    public int updateUserLoadPictureUrl(UserLoadPictureUrl userLoadPictureUrl) {
        ContentValues cv = new ContentValues();
        //cv.put(Constants.CAR_ID, carInformation.getCarId());
        cv.put(Constants.PICTURE_URL_1, userLoadPictureUrl.getPictureUrl1());
        cv.put(Constants.PICTURE_URL_2,userLoadPictureUrl.getPictureUrl2());
        cv.put(Constants.PICTURE_URL_3, userLoadPictureUrl.getPictureUrl3());
        cv.put(Constants.PICTURE_URL_4,String.valueOf(userLoadPictureUrl.getPictureUrl4()) );
        cv.put(Constants.PICTURE_URL_5, userLoadPictureUrl.getPictureUrl5());
        cv.put(Constants.PICTURE_URL_6, userLoadPictureUrl.getPictureUrl6());
        cv.put(Constants.PICTURE_URL_7, userLoadPictureUrl.getPictureUrl7());
        cv.put(Constants.PICTURE_URL_8, userLoadPictureUrl.getPictureUrl8());
        cv.put(Constants.PICTURE_URL_9, userLoadPictureUrl.getPictureUrl9());
        cv.put(Constants.CAR_ID_1, userLoadPictureUrl.getCarId());
        String where = Constants.CAR_ID_1 + "= ?";
        String[] whereArgs = { String.valueOf(userLoadPictureUrl.getCarId()) };

        UserLoadPictureUrl userLoadPictureUrlFromOriginalDB = getUserLoadPictureUrlByCarId(userLoadPictureUrl.getCarId());
        int rowCount = 0;
        if(userLoadPictureUrlFromOriginalDB == null){
            insertUserLoadPictureUrl(userLoadPictureUrl);
        }else{
            this.openWritableDB();
            rowCount = db.update(Constants.CAR_PICTURE_URL_TABLE, cv, where, whereArgs);
            this.closeDB();
        }

        return rowCount;
    }

    public int deleteUserLoadPictureUrl(long id) {
        String where = Constants.CAR_ID_1 + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWritableDB();
        int rowCount = db.delete(Constants.CAR_PICTURE_URL_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }
}
