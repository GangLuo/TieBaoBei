package com.zhixiangzhonggong.tiebaobei.model;

import java.util.ArrayList;
import java.util.Date;

public class CarInformation {
    private int carId;
    private String carType;
    private String carPictureLocalUrl;
    private String carPictureLocalName;
    private Date carPublishDate;
    private String carBrand;
    private String carModel;
    private String carUsedHours;
    private String carSite;
    private String carProducedYear;
    private double carPrice;
    private String carUsedState;
    private String carUsedPurpose;
    private String carUserDeclare;
    private String carUserName;
    private String carUserPhone;
    private boolean isApproved;

    public CarInformation(boolean isApproved, int carId, String carType, String carPictureLocalUrl, String carPictureLocalName, Date carPublishDate, String carBrand, String carModel, String carUsedHours, String carSite, String carProducedYear, double carPrice, String carUsedState, String carUsedPurpose, String carUserDeclare, String carUserName, String carUserPhone) {
        this.isApproved = isApproved;
        this.carId = carId;
        this.carType = carType;
        this.carPictureLocalUrl = carPictureLocalUrl;
        this.carPictureLocalName = carPictureLocalName;
        this.carPublishDate = carPublishDate;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carUsedHours = carUsedHours;
        this.carSite = carSite;
        this.carProducedYear = carProducedYear;
        this.carPrice = carPrice;
        this.carUsedState = carUsedState;
        this.carUsedPurpose = carUsedPurpose;
        this.carUserDeclare = carUserDeclare;
        this.carUserName = carUserName;
        this.carUserPhone = carUserPhone;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarPictureLocalUrl() {
        return carPictureLocalUrl;
    }

    public void setCarPictureLocalUrl(String carPictureLocalUrl) {
        this.carPictureLocalUrl = carPictureLocalUrl;
    }

    public String getCarPictureLocalName() {
        return carPictureLocalName;
    }

    public void setCarPictureLocalName(String carPictureLocalName) {
        this.carPictureLocalName = carPictureLocalName;
    }

    public Date getCarPublishDate() {
        return carPublishDate;
    }

    public void setCarPublishDate(Date carPublishDate) {
        this.carPublishDate = carPublishDate;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarUsedHours() {
        return carUsedHours;
    }

    public void setCarUsedHours(String carUsedHours) {
        this.carUsedHours = carUsedHours;
    }

    public String getCarSite() {
        return carSite;
    }

    public void setCarSite(String carSite) {
        this.carSite = carSite;
    }

    public String getCarProducedYear() {
        return carProducedYear;
    }

    public void setCarProducedYear(String carProducedYear) {
        this.carProducedYear = carProducedYear;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarUsedState() {
        return carUsedState;
    }

    public void setCarUsedState(String carUsedState) {
        this.carUsedState = carUsedState;
    }

    public String getCarUsedPurpose() {
        return carUsedPurpose;
    }

    public void setCarUsedPurpose(String carUsedPurpose) {
        this.carUsedPurpose = carUsedPurpose;
    }

    public String getCarUserDeclare() {
        return carUserDeclare;
    }

    public void setCarUserDeclare(String carUserDeclare) {
        this.carUserDeclare = carUserDeclare;
    }

    public String getCarUserName() {
        return carUserName;
    }

    public void setCarUserName(String carUserName) {
        this.carUserName = carUserName;
    }

    public String getCarUserPhone() {
        return carUserPhone;
    }

    public void setCarUserPhone(String carUserPhone) {
        this.carUserPhone = carUserPhone;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
}
