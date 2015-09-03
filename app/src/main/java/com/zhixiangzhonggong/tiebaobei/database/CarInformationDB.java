package com.zhixiangzhonggong.tiebaobei.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.Constants;
import com.zhixiangzhonggong.tiebaobei.CustomizedView.CustomAutoCompleteView;
import com.zhixiangzhonggong.tiebaobei.model.CarInformation;
import com.zhixiangzhonggong.tiebaobei.util.LYDateString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CarInformationDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private int size;
    private int itemFinished;
    public CarInformationDB(Context context) {
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
    public ArrayList<CarInformation> getAllInformatons() {

        this.openReadableDB();
        Cursor cursor = db.query(Constants.CAR_INFORMATION_TABLE, null,
                null, null,
                null, null, null);
        ArrayList<CarInformation> carInformations = new ArrayList<CarInformation>();
        cursor.moveToFirst();
        carInformations.add(getCarInformationFromCursor(cursor));
        while (cursor.moveToNext()) {
            carInformations.add(getCarInformationFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return carInformations;
    }

    public ArrayList<CarInformation> getCarInformationByCarType(String  carType) {
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
    }



    public CarInformation getCarInformationByCarId(long id) {
        String where = Constants.CAR_ID + "= ?";
        String[] whereArgs = { Long.toString(id) };

        // handle exceptions?
        this.openReadableDB();
        Cursor cursor = db.query(Constants.CAR_INFORMATION_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        CarInformation carInformation = getCarInformationFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return carInformation;
    }

    private static CarInformation getCarInformationFromCursor(Cursor cursor) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                CarInformation storeDish = new CarInformation(

                        cursor.getInt(Constants.CAR_ID_COL),
                        cursor.getString(Constants.CAR_TYPE_COL),
                        cursor.getString(Constants.CAR_PICTURE_LOCAL_URL_COL),
                        cursor.getString(Constants.CAR_PICTURE_LOCAL_NAME_COL),
                        cursor.getString(Constants.CAR_PUBLISH_DATE_COL),
                       // LYDateString.stringToDate(cursor.getString(Constants.CAR_PUBLISH_DATE_COL), 3),
                        cursor.getString(Constants.CAR_BRAND_COL),
                        cursor.getString(Constants.CAR_MODEL_COL),
                        cursor.getInt(Constants.CAR_USED_HOURS_COL),
                        cursor.getString(Constants.CAR_SITE_COL),
                        cursor.getString(Constants.CAR_PRODUCED_YEAR_COL),
                        cursor.getDouble(Constants.CAR_PRICE_COL),
                        cursor.getString(Constants.CAR_USED_STATE_COL),
                        cursor.getString(Constants.CAR_USED_PURPOSE_COL),
                        cursor.getString(Constants.CAR_USER_DESCRIER_COL),
                        cursor.getString(Constants.CAR_USER_NAME_COL),
                        cursor.getString(Constants.CAR_USER_PHONE_COL),
                        cursor.getInt(Constants.IS_APPROVED_COL)==1?true:false

                );
                return storeDish;
            }
            catch(Exception e) {
                return null;
            }
        }
    }


    public long insertCarInformation(CarInformation carInformation) {
        ContentValues cv = new ContentValues();
       // cv.put(Constants.CAR_ID, carInformation.getCarId());
        cv.put(Constants.CAR_TYPE, carInformation.getCarType());
        cv.put(Constants.CAR_PICTURE_LOCAL_URL,carInformation.getCarPictureLocalUrl());
        cv.put(Constants.CAR_PICTURE_LOCAL_NAME, carInformation.getCarPictureLocalName());
        cv.put(Constants.CAR_PUBLISH_DATE,String.valueOf(carInformation.getCarPublishDate()) );
        cv.put(Constants.CAR_BRAND, carInformation.getCarBrand());
        cv.put(Constants.CAR_MODEL, carInformation.getCarModel());
        cv.put(Constants.CAR_USED_HOURS, carInformation.getCarUsedHours());
        cv.put(Constants.CAR_SITE, carInformation.getCarSite());
        cv.put(Constants.CAR_PRODUCED_YEAR, carInformation.getCarProducedYear());
        cv.put(Constants.CAR_PRICE, carInformation.getCarPrice());
        cv.put(Constants.CAR_USED_STATE, carInformation.getCarUsedState());
        cv.put(Constants.CAR_USED_PURPOSE, carInformation.getCarUsedPurpose());
        cv.put(Constants.CAR_USER_DESCRIER, carInformation.getCarUserDescriber());
        cv.put(Constants.CAR_USER_NAME, carInformation.getCarUserName());
        cv.put(Constants.CAR_USER_PHONE, carInformation.getCarUserPhone());
        cv.put(Constants.IS_APPROVED, carInformation.isApproved()==true?1:0);
        this.openWritableDB();
        long rowID = db.insert(Constants.CAR_INFORMATION_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    public int updateCarInformation(CarInformation carInformation) {
        ContentValues cv = new ContentValues();
        //cv.put(Constants.CAR_ID, carInformation.getCarId());
        cv.put(Constants.CAR_TYPE, carInformation.getCarType());
        cv.put(Constants.CAR_PICTURE_LOCAL_URL,carInformation.getCarPictureLocalUrl());
        cv.put(Constants.CAR_PICTURE_LOCAL_NAME, carInformation.getCarPictureLocalName());
        cv.put(Constants.CAR_PUBLISH_DATE,String.valueOf(carInformation.getCarPublishDate()) );
        cv.put(Constants.CAR_BRAND, carInformation.getCarBrand());
        cv.put(Constants.CAR_MODEL, carInformation.getCarModel());
        cv.put(Constants.CAR_USED_HOURS, carInformation.getCarUsedHours());
        cv.put(Constants.CAR_SITE, carInformation.getCarSite());
        cv.put(Constants.CAR_PRODUCED_YEAR, carInformation.getCarProducedYear());
        cv.put(Constants.CAR_PRICE, carInformation.getCarPrice());
        cv.put(Constants.CAR_USED_STATE, carInformation.getCarUsedState());
        cv.put(Constants.CAR_USED_PURPOSE, carInformation.getCarUsedPurpose());
        cv.put(Constants.CAR_USER_DESCRIER, carInformation.getCarUserDescriber());
        cv.put(Constants.CAR_USER_NAME, carInformation.getCarUserName());
        cv.put(Constants.CAR_USER_PHONE, carInformation.getCarUserPhone());
        cv.put(Constants.IS_APPROVED, carInformation.isApproved());
        String where = Constants.CAR_ID + "= ?";
        String[] whereArgs = { String.valueOf(carInformation.getCarId()) };

        CarInformation carInformationFromOriginalDB = getCarInformationByCarId(carInformation.getCarId());
        int rowCount = 0;
        if(carInformationFromOriginalDB == null){
            insertCarInformation(carInformation);
        }else{
            this.openWritableDB();
            rowCount = db.update(Constants.CAR_INFORMATION_TABLE, cv, where, whereArgs);
            this.closeDB();
        }

        return rowCount;
    }

    public int deleteStoreDish(long id) {
        String where = Constants.CAR_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWritableDB();
        int rowCount = db.delete(Constants.CAR_INFORMATION_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }


    public List<CarInformation> readCarInformationFromDB(String searchTerm){
        List<CarInformation> recordsList = new ArrayList<CarInformation>();
        // select query
        String sql = "";
        sql += "SELECT * FROM " + Constants.CAR_INFORMATION_TABLE;
        sql += " WHERE " + Constants.CAR_BRAND + " OR " + Constants.CAR_MODEL + " LIKE '%" + searchTerm + "%'";
        sql += " ORDER BY " + Constants.CAR_ID + " DESC";
        sql += " LIMIT 0,5";
        this.openWritableDB();
        Cursor cursor=db.rawQuery(sql,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                CarInformation carInformation = getCarInformationFromCursor(cursor);
                //String objectName = cursor.getString(cursor.getColumnIndex(fieldObjectName));
               // MyObject myObject = new MyObject(objectName);

                // add to list
                recordsList.add(carInformation);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }

    public ArrayList<CarInformation> readAllCarInformationFromDB(String searchTerm){
        ArrayList<CarInformation> recordsList = new ArrayList<CarInformation>();
        // select query
        String sql = "";
        sql += "SELECT * FROM " + Constants.CAR_INFORMATION_TABLE;
        sql += " WHERE " + Constants.CAR_BRAND + " OR " + Constants.CAR_MODEL + " LIKE '%" + searchTerm + "%'";
        sql += " ORDER BY " + Constants.CAR_ID + " DESC";
        this.openWritableDB();
        Cursor cursor=db.rawQuery(sql,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                CarInformation carInformation = getCarInformationFromCursor(cursor);
                //String objectName = cursor.getString(cursor.getColumnIndex(fieldObjectName));
                // MyObject myObject = new MyObject(objectName);

                // add to list
                recordsList.add(carInformation);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }
}
