package com.zhixiangzhonggong.tiebaobei.model;


import java.util.ArrayList;

public class UserLoadPictureUrl {
    private String pictureUrl1;
    private String pictureUrl2;
    private String pictureUrl3;
    private String pictureUrl4;
    private String pictureUrl5;
    private String pictureUrl6;
    private String pictureUrl7;
    private String pictureUrl8;
    private String pictureUrl9;
    private int carId;
    private ArrayList<UserLoadPictureUrl> userLoadPictureUrlArrayList;

    public UserLoadPictureUrl(String pictureUrl1, String pictureUrl2, String pictureUrl3,
                              String pictureUrl4, String pictureUrl5, String pictureUrl6,
                              String pictureUrl7, String pictureUrl8, String pictureUrl9,
                              int carId) {
        this.pictureUrl1 = pictureUrl1;
        this.pictureUrl2 = pictureUrl2;
        this.pictureUrl3 = pictureUrl3;
        this.pictureUrl4 = pictureUrl4;
        this.pictureUrl5 = pictureUrl5;
        this.pictureUrl6 = pictureUrl6;
        this.pictureUrl7 = pictureUrl7;
        this.pictureUrl8 = pictureUrl8;
        this.pictureUrl9 = pictureUrl9;
        this.carId=carId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getPictureUrl1() {
        return pictureUrl1;
    }

    public void setPictureUrl1(String pictureUrl1) {
        this.pictureUrl1 = pictureUrl1;
    }

    public String getPictureUrl2() {
        return pictureUrl2;
    }

    public void setPictureUrl2(String pictureUrl2) {
        this.pictureUrl2 = pictureUrl2;
    }

    public String getPictureUrl3() {
        return pictureUrl3;
    }

    public void setPictureUrl3(String pictureUrl3) {
        this.pictureUrl3 = pictureUrl3;
    }

    public String getPictureUrl4() {
        return pictureUrl4;
    }

    public void setPictureUrl4(String pictureUrl4) {
        this.pictureUrl4 = pictureUrl4;
    }

    public String getPictureUrl5() {
        return pictureUrl5;
    }

    public void setPictureUrl5(String pictureUrl5) {
        this.pictureUrl5 = pictureUrl5;
    }

    public String getPictureUrl6() {
        return pictureUrl6;
    }

    public void setPictureUrl6(String pictureUrl6) {
        this.pictureUrl6 = pictureUrl6;
    }

    public String getPictureUrl7() {
        return pictureUrl7;
    }

    public void setPictureUrl7(String pictureUrl7) {
        this.pictureUrl7 = pictureUrl7;
    }

    public String getPictureUrl8() {
        return pictureUrl8;
    }

    public void setPictureUrl8(String pictureUrl8) {
        this.pictureUrl8 = pictureUrl8;
    }

    public String getPictureUrl9() {
        return pictureUrl9;
    }

    public void setPictureUrl9(String pictureUrl9) {
        this.pictureUrl9 = pictureUrl9;
    }

    public ArrayList<UserLoadPictureUrl> getUserLoadPictureUrlArrayList() {
        return userLoadPictureUrlArrayList;
    }

    public void setUserLoadPictureUrlArrayList(ArrayList<UserLoadPictureUrl> userLoadPictureUrlArrayList) {
        this.userLoadPictureUrlArrayList = userLoadPictureUrlArrayList;
    }
}
