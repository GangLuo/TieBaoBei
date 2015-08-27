package com.zhixiangzhonggong.tiebaobei.CustomizedClass;

/**
 * Created by Shaohua Mao on 2015-07-09.
 */
public class Constants {


    // database constants
    public static final String DB_NAME = "CarInfomation.db";
    public static final int    DB_VERSION = 1;

    // car information table constants
    public static final String CAR_INFORMATION_TABLE = "car_information";

    public static final String CAR_ID = "car_id";
    public static final int    CAR_ID_COL = 0;

    public static final String CAR_TYPE = "car_type";
    public static final int    CAR_TYPE_COL = 1;

    public static final String CAR_PICTURE_LOCAL_URL = "car_picture_local_url";
    public static final int    CAR_PICTURE_LOCAL_URL_COL = 2;

    public static final String CAR_PICTURE_LOCAL_NAME= "car_picture_local_name";
    public static final int    CAR_PICTURE_LOCAL_NAME_COL = 3;

    public static final String CAR_PUBLISH_DATE = "car_publish_date";
    public static final int    CAR_PUBLISH_DATE_COL = 4;

    public static final String CAR_BRAND = "car_brand";
    public static final int    CAR_BRAND_COL = 5;

    public static final String CAR_MODEL = "car_model";
    public static final int    CAR_MODEL_COL = 6;

    public static final String CAR_USED_HOURS = "car_used_hours";
    public static final int    CAR_USED_HOURS_COL = 7;

    public static final String CAR_SITE = "car_site";
    public static final int    CAR_SITE_COL = 8;

    public static final String CAR_PRODUCED_YEAR = "car_produced_year";
    public static final int    CAR_PRODUCED_YEAR_COL = 9;

    public static final String CAR_PRICE = "car_price";
    public static final int    CAR_PRICE_COL =10;

    public static final String CAR_USED_STATE = "car_used_state";
    public static final int    CAR_USED_STATE_COL = 11;

    public static final String CAR_USED_PURPOSE = "car_used_purpose";
    public static final int    CAR_USED_PURPOSE_COL = 12;

    public static final String CAR_USER_DESCRIER = "car_used_describer";
    public static final int    CAR_USER_DESCRIER_COL = 13;

    public static final String CAR_USER_NAME = "car_used_name";
    public static final int    CAR_USER_NAME_COL = 14;

    public static final String CAR_USER_PHONE = "car_user_phone";
    public static final int    CAR_USER_PHONE_COL = 15;

    public static final String IS_APPROVED = "is_approved";
    public static final int    IS_APPROVED_COL = 16;



    // CREATE and DROP TABLE statements
    public static final String CREATE_CAR_INFORMATION_TABLE =
            "CREATE TABLE " + CAR_INFORMATION_TABLE + " (" +
                    CAR_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    CAR_TYPE      + " TEXT, " +
                    CAR_PICTURE_LOCAL_URL  + " TEXT, " +
                    CAR_PICTURE_LOCAL_NAME  + " TEXT, " +
                    CAR_PUBLISH_DATE  + " TEXT, " +
                    CAR_BRAND  + " TEXT, " +
                    CAR_MODEL  + " TEXT, " +
                    CAR_USED_HOURS  + " INTEGER, " +
                    CAR_SITE  + " TEXT, " +
                    CAR_PRODUCED_YEAR  + " TEXT, " +
                    CAR_PRICE     + " REAL, " +
                    CAR_USED_STATE  + " TEXT, " +
                    CAR_USED_PURPOSE  + " TEXT, " +
                    CAR_USER_DESCRIER  + " TEXT, " +
                    CAR_USER_NAME  + " TEXT, " +
                    CAR_USER_PHONE  + " TEXT, " +
                    IS_APPROVED  + " INTEGER NOT NULL);";

    public static final String DROP_CAR_INFORMATION_TABLE =
            "DROP TABLE IF EXISTS " + CAR_INFORMATION_TABLE;


    // user upload picture local url
    public static final String CAR_PICTURE_URL_TABLE = "car_information";

    public static final String PICTURE_URL_ID = "car_id";
    public static final int    PICTURE_URL_ID_COL = 0;

    public static final String PICTURE_URL_1 = "picture_url_1";
    public static final int    PICTURE_URL_1_COL = 1;

    public static final String PICTURE_URL_2 = "picture_url_2";
    public static final int    PICTURE_URL_2_COL = 2;

    public static final String PICTURE_URL_3 = "picture_url_3";
    public static final int    PICTURE_URL_3_COL = 3;

    public static final String PICTURE_URL_4 = "picture_url_4";
    public static final int    PICTURE_URL_4_COL = 4;

    public static final String PICTURE_URL_5 = "picture_url_5";
    public static final int    PICTURE_URL_5_COL = 5;

    public static final String PICTURE_URL_6 = "picture_url_6";
    public static final int    PICTURE_URL_6_COL = 6;

    public static final String PICTURE_URL_7 = "picture_url_7";
    public static final int    PICTURE_URL_7_COL = 7;

    public static final String PICTURE_URL_8 = "picture_url_8";
    public static final int    PICTURE_URL_8_COL = 8;

    public static final String PICTURE_URL_9 = "picture_url_9";
    public static final int    PICTURE_URL_9_COL = 9;

    public static final String CAR_ID_1 = "car_id";
    public static final int    CAR_ID_1_COL = 10;



    // CREATE and DROP TABLE statements
    public static final String CREATE_PICTURE_URL_TABLE =
            "CREATE TABLE " + CAR_PICTURE_URL_TABLE + " (" +
                    PICTURE_URL_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    PICTURE_URL_1      + " TEXT, " +
                    PICTURE_URL_2  + " TEXT, " +
                    PICTURE_URL_3  + " TEXT, " +
                    PICTURE_URL_4  + " TEXT, " +
                    PICTURE_URL_5  + " TEXT, " +
                    PICTURE_URL_6  + " TEXT, " +
                    PICTURE_URL_7  + " TEXT, " +
                    PICTURE_URL_8  + " TEXT, " +
                    PICTURE_URL_9  + " TEXT, " +
                    CAR_ID_1  + " INTEGER);";

    public static final String DROP_PICTURE_URL_TABLE =
            "DROP TABLE IF EXISTS " + CAR_PICTURE_URL_TABLE;

}
