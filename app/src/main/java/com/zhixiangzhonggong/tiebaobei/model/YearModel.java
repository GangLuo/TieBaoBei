package com.zhixiangzhonggong.tiebaobei.model;


import java.util.List;

public class YearModel {
    private String name;
    private List<MonthModel> monthList;

    public YearModel() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MonthModel> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<MonthModel> monthList) {
        this.monthList = monthList;
    }
}
