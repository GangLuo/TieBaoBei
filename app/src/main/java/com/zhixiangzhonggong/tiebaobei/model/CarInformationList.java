package com.zhixiangzhonggong.tiebaobei.model;

import android.content.Context;

import com.zhixiangzhonggong.tiebaobei.database.CarInformationDB;

import java.util.ArrayList;
import java.util.Date;


public class CarInformationList {
    private ArrayList<CarInformation> carInformationArrayList=new ArrayList<>();
    private Context context;

    public ArrayList<CarInformation> getCarInformationArrayList() {
        return carInformationArrayList;
    }

    public void setCarInformationArrayList(ArrayList<CarInformation> carInformationArrayList) {
        this.carInformationArrayList = carInformationArrayList;
    }


}
