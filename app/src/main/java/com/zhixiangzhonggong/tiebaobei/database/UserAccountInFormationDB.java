package com.zhixiangzhonggong.tiebaobei.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.Constants;
import com.zhixiangzhonggong.tiebaobei.model.CarInformation;
import com.zhixiangzhonggong.tiebaobei.model.UserAccountInformationModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserAccountInFormationDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private int size;
    private int itemFinished;
    public UserAccountInFormationDB(Context context) {
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
    public ArrayList<UserAccountInformationModel> getAllUserAccountInFormations() {

        this.openReadableDB();
        Cursor cursor = db.query(Constants.USER_ACCOUNT_INFORMATION_TABLE, null,
                null, null,
                null, null, null);
        ArrayList<UserAccountInformationModel> userAccountInformationList = new ArrayList<UserAccountInformationModel>();

        while (cursor.moveToNext()) {
            userAccountInformationList.add(getUserAccountInFormationFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return userAccountInformationList;
    }



/*    public ArrayList<UserAccountInformationModel> getUserAccountInFormationByCarType(String  carType) {
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
            carInformations.add(getUserAccountInFormationFromCursor(cursor));
            itemFinished++;
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return carInformations;
    }*/



    public UserAccountInformationModel getUserAccountInformationByPhoneNumber(String phoneNumber) {
        String where = Constants.USER_ACCOUNT_TELEPHONE + "= ?";
        String[] whereArgs = { phoneNumber };

        // handle exceptions?
        this.openReadableDB();
        Cursor cursor = db.query(Constants.USER_ACCOUNT_INFORMATION_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        UserAccountInformationModel userAccountInformation = getUserAccountInFormationFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return userAccountInformation;
    }

    private static UserAccountInformationModel getUserAccountInFormationFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                UserAccountInformationModel userAccountInformation = new UserAccountInformationModel(
                        cursor.getString(Constants.USER_ACCOUNT_TELEPHONE_COL),
                        cursor.getString(Constants.USER_ACCOUNT_ALIAS_NAME_COL),
                        cursor.getString(Constants.USER_ACCOUNT_EMAIL_COL),
                        cursor.getString(Constants.USER_ACCOUNT_PASSWORD_COL)

                );
                return userAccountInformation;
            }
            catch(Exception e) {
                return null;
            }
        }
    }


    public long insertUserAccountInformation(UserAccountInformationModel userAccountInformationModel) {
        ContentValues cv = new ContentValues();

        cv.put(Constants.USER_ACCOUNT_TELEPHONE, userAccountInformationModel.getUserTelephone());
        cv.put(Constants.USER_ACCOUNT_ALIAS_NAME,userAccountInformationModel.getUserAliasName());
        cv.put(Constants.USER_ACCOUNT_EMAIL, userAccountInformationModel.getEmail());
        cv.put(Constants.USER_ACCOUNT_PASSWORD, userAccountInformationModel.getPassword());
        this.openWritableDB();
        long rowID = db.insert(Constants.USER_ACCOUNT_INFORMATION_TABLE, null, cv);
        this.closeDB();
        return rowID;
    }

    public int updateUserAccountPasswordByPhone(UserAccountInformationModel userAccountInformationModel) {
        ContentValues cv = new ContentValues();
        //cv.put(Constants.CAR_ID, carInformation.getCarId());
        cv.put(Constants.USER_ACCOUNT_TELEPHONE, userAccountInformationModel.getUserTelephone());
        cv.put(Constants.USER_ACCOUNT_ALIAS_NAME,userAccountInformationModel.getUserAliasName());
        cv.put(Constants.USER_ACCOUNT_EMAIL, userAccountInformationModel.getEmail());
        cv.put(Constants.USER_ACCOUNT_PASSWORD, userAccountInformationModel.getPassword());
        String where = Constants.USER_ACCOUNT_TELEPHONE + "= ?";
        String[] whereArgs = { userAccountInformationModel.getUserTelephone()};

        UserAccountInformationModel userAccountInformationFromOriginalDB = getUserAccountInformationByPhoneNumber(userAccountInformationModel.getUserTelephone());
        int rowCount = 0;
        if(userAccountInformationFromOriginalDB == null){
            insertUserAccountInformation(userAccountInformationFromOriginalDB);
        }else{
            this.openWritableDB();
            rowCount = db.update(Constants.USER_ACCOUNT_INFORMATION_TABLE, cv, where, whereArgs);
            this.closeDB();
        }

        return rowCount;
    }

    public int deleteUserAccountInformation(long id) {
        String where = Constants.USER_ACCOUNT_INFORMATION_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWritableDB();
        int rowCount = db.delete(Constants.USER_ACCOUNT_INFORMATION_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }
}
