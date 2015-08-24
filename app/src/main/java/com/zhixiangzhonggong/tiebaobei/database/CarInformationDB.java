package com.zhixiangzhonggong.tiebaobei.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.Constants;

import java.util.ArrayList;

public class CarInformationDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private int size;
    private
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
    public ArrayList<CarInformation> getStoreDishes() {
        String where =  Constants.DISH_DELETED + "= ?";
        String[] whereArgs = {"0"};

        this.openReadableDB();
        Cursor cursor = db.query(Constants.DISH_TABLE, null,
                null, null,
                null, null, null);
        ArrayList<CarInformation> storeDishes = new ArrayList<CarInformation>();

        while (cursor.moveToNext()) {
            storeDishes.add(getStoreDishFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return storeDishes;
    }

    public ArrayList<CarInformation> getDishesByStoreTypeId(long typeId) {
        String where = Constants.DISH_STORE_TYPE_ID + "= ?" + " AND " + Constants.DISH_DELETED + "= ?";
        String[] whereArgs = { Long.toString(typeId), "0"};
        size=0;
        this.openReadableDB();
        Cursor cursor = db.query(Constants.DISH_TABLE, null,
                where, whereArgs,
                null, null, null);

        size=cursor.getCount();

        ArrayList<StoreDish> StoreDishes = new ArrayList<StoreDish>();

        itemFinished=0;
        while (cursor.moveToNext()) {
            StoreDishes.add(getStoreDishFromCursor(cursor));
            itemFinished++;
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return StoreDishes;
    }

    public ArrayList<StoreDish> getNewDishesFromDB(long isNew) {
        String where = Constants.DISH_IS_NEW + "= ?" + " AND " + Constants.DISH_DELETED + "= ?";
        String[] whereArgs = { Long.toString(isNew), "0"};
        this.openReadableDB();
        Cursor cursor = db.query(Constants.DISH_TABLE, null,
                where, whereArgs,
                null, null, null);

        ArrayList<StoreDish> StoreNewDishes = new ArrayList<StoreDish>();

        while (cursor.moveToNext()) {
            StoreNewDishes.add(getStoreDishFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return StoreNewDishes;
    }

    public ArrayList<StoreDish> getSpecialDishesFromDB(long isSpecial) {
        String where = Constants.DISH_IS_SPECIALTY + "= ?" + " AND " + Constants.DISH_DELETED + "= ?";
        String[] whereArgs = { Long.toString(isSpecial) , "0"};
        this.openReadableDB();
        Cursor cursor = db.query(Constants.DISH_TABLE, null,
                where, whereArgs,
                null, null, null);

        ArrayList<StoreDish> StoreSpecialDishes = new ArrayList<StoreDish>();

        while (cursor.moveToNext()) {
            StoreSpecialDishes.add(getStoreDishFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return StoreSpecialDishes;
    }

    public ArrayList<StoreDish> getOnSaleDishesFromDB(long isDeal) {
        String where = Constants.DISH_IS_DEAL + "= ?" + " AND " + Constants.DISH_DELETED + "= ?";
        String[] whereArgs = { Long.toString(isDeal), "0"};
        this.openReadableDB();
        Cursor cursor = db.query(Constants.DISH_TABLE, null,
                where, whereArgs,
                null, null, null);

        ArrayList<StoreDish> StoreOnSaleDishes = new ArrayList<StoreDish>();

        while (cursor.moveToNext()) {
            StoreOnSaleDishes.add(getStoreDishFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return StoreOnSaleDishes;
    }

    public StoreDish getStoreDishById(long id) {
        String where = Constants.DISH_ID + "= ?";
        String[] whereArgs = { Long.toString(id) };

        // handle exceptions?
        this.openReadableDB();
        Cursor cursor = db.query(Constants.DISH_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        StoreDish storeDish = getStoreDishFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return storeDish;
    }

    private static StoreDish getStoreDishFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                StoreDish storeDish = new StoreDish(

                        cursor.getInt(Constants.DISH_ID_COL),
                        cursor.getInt(Constants.DISH_DELETED_COL)==1?true:false,
                        LYDateString.stringToDate(cursor.getString(Constants.DISH_UPDATED_TS_COL), 3),
                        LYDateString.stringToDate(cursor.getString(Constants.DISH_CREATED_TS_COL), 3),
                        cursor.getString(Constants.DISH_NAME_COL),
                        cursor.getInt(Constants.DISH_STORE_ID_COL),
                        cursor.getInt(Constants.DISH_STORE_TYPE_ID_COL),
                        cursor.getString(Constants.DISH_LOCAL_ID_COL),
                        cursor.getInt(Constants.DISH_SEQUENCE_COL),
                        cursor.getInt(Constants.DISH_TYPE_COL),
                        cursor.getInt(Constants.DISH_IS_SPECIALTY_COL)==1?true:false,
                        cursor.getInt(Constants.DISH_IS_DEAL_COL)==1?true:false,
                        cursor.getInt(Constants.DISH_IS_NEW_COL)==1?true:false,
                        cursor.getDouble(Constants.DISH_PRICE_COL),
                        cursor.getDouble(Constants.DISH_SALE_PERCENT_COL),
                        LYDateString.stringToDate(cursor.getString(Constants.DISH_SALE_EXPIRE_COL), 3),
                        cursor.getString(Constants.DISH_MATERIALS_COL),
                        cursor.getString(Constants.DISH_MEDIA_URL_COL),
                        cursor.getString(Constants.DISH_NUTRITION_COL),
                        cursor.getString(Constants.DISH_INFO_COL),
                        cursor.getString(Constants.DISH_LOCAL_URL_COL)

                );
                return storeDish;
            }
            catch(Exception e) {
                return null;
            }
        }
    }


    public long insertStoreDish(StoreDish storeDish) {
        ContentValues cv = new ContentValues();
        cv.put(Constants.DISH_ID, storeDish.getId());
        cv.put(Constants.DISH_DELETED, storeDish.isDeleted());
        cv.put(Constants.DISH_UPDATED_TS,String.valueOf(storeDish.getUpdatedTs()));
        cv.put(Constants.DISH_CREATED_TS,String.valueOf(storeDish.getCreatedTs()) );
        cv.put(Constants.DISH_NAME, storeDish.getName());
        cv.put(Constants.DISH_STORE_ID, storeDish.getStoreId());
        cv.put(Constants.DISH_STORE_TYPE_ID, storeDish.getStoreTypeId());
        cv.put(Constants.DISH_LOCAL_ID, storeDish.getLocalId());
        cv.put(Constants.DISH_SEQUENCE, storeDish.getSequence());
        cv.put(Constants.DISH_TYPE, storeDish.getType());
        cv.put(Constants.DISH_IS_SPECIALTY, storeDish.isSpecialty());
        cv.put(Constants.DISH_IS_DEAL, storeDish.isDeal());
        cv.put(Constants.DISH_IS_NEW, storeDish.isNew());
        cv.put(Constants.DISH_PRICE, storeDish.getPrice());
        cv.put(Constants.DISH_SALE_PERCENT, storeDish.getSalePercent());
        cv.put(Constants.DISH_SALE_EXPIRE, String.valueOf(storeDish.getSaleExpire()));
        cv.put(Constants.DISH_MATERIALS, storeDish.getMaterials());
        cv.put(Constants.DISH_MEDIA_URL, storeDish.getMediaUrl());
        cv.put(Constants.DISH_NUTRITION, storeDish.getNutrition());
        cv.put(Constants.DISH_INFO, storeDish.getInfo());
        cv.put(Constants.DISH_LOCAL_URL, storeDish.getMediaLocalUrl());
        this.openWritableDB();
        long rowID = db.insert(Constants.DISH_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    public int updateStoreDish(StoreDish storeDish) {
        ContentValues cv = new ContentValues();
        cv.put(Constants.DISH_ID, storeDish.getId());
        cv.put(Constants.DISH_DELETED, storeDish.isDeleted());
        cv.put(Constants.DISH_UPDATED_TS,String.valueOf(storeDish.getUpdatedTs()));
        cv.put(Constants.DISH_CREATED_TS,String.valueOf(storeDish.getCreatedTs()) );
        cv.put(Constants.DISH_NAME, storeDish.getName());
        cv.put(Constants.DISH_STORE_ID, storeDish.getStoreId());
        cv.put(Constants.DISH_STORE_TYPE_ID, storeDish.getStoreTypeId());
        cv.put(Constants.DISH_LOCAL_ID, storeDish.getLocalId());
        cv.put(Constants.DISH_SEQUENCE, storeDish.getSequence());
        cv.put(Constants.DISH_TYPE, storeDish.getType());
        cv.put(Constants.DISH_IS_SPECIALTY, storeDish.isSpecialty());
        cv.put(Constants.DISH_IS_DEAL, storeDish.isDeal());
        cv.put(Constants.DISH_IS_NEW, storeDish.isNew());
        cv.put(Constants.DISH_PRICE, storeDish.getPrice());
        cv.put(Constants.DISH_SALE_PERCENT, storeDish.getSalePercent());
        cv.put(Constants.DISH_SALE_EXPIRE, String.valueOf(storeDish.getSaleExpire()));
        cv.put(Constants.DISH_MATERIALS, storeDish.getMaterials());
        cv.put(Constants.DISH_MEDIA_URL, storeDish.getMediaUrl());
        cv.put(Constants.DISH_NUTRITION, storeDish.getNutrition());
        cv.put(Constants.DISH_INFO, storeDish.getInfo());
        String where = Constants.DISH_ID + "= ?";
        String[] whereArgs = { String.valueOf(storeDish.getId()) };

        StoreDish storedishFromOriginalDB = getStoreDishById(storeDish.getId());
        int rowCount = 0;
        if(storedishFromOriginalDB == null){
            insertStoreDish(storeDish);
        }else{
            this.openWritableDB();
            rowCount = db.update(Constants.DISH_TABLE, cv, where, whereArgs);
            this.closeDB();
        }

        return rowCount;
    }

    public int deleteStoreDish(long id) {
        String where = Constants.DISH_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWritableDB();
        int rowCount = db.delete(Constants.DISH_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }
}
