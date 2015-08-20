package com.zhixiangzhonggong.tiebaobei.model;


import java.util.List;

public class ExcavatorBrandModel {
    private String name;
    private List<ExcavatorModelModel> excavatorModelList;
    public ExcavatorBrandModel() {
        super();
    }

    public ExcavatorBrandModel(String name) {
        super();
        this.name = name;
    }

    public List<ExcavatorModelModel> getExcavatorModelList() {
        return excavatorModelList;
    }

    public void setExcavatorModelList(List<ExcavatorModelModel> excavatorModelList) {
        this.excavatorModelList = excavatorModelList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
