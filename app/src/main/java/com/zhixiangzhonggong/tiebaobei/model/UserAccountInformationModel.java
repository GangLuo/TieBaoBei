package com.zhixiangzhonggong.tiebaobei.model;


public class UserAccountInformationModel {
    private String userTelephone;
    private String userAliasName;
    private String email;
    private String password;

    public UserAccountInformationModel(String userTelephone, String userAliasName, String email, String password) {
        this.userTelephone = userTelephone;
        this.userAliasName = userAliasName;
        this.email = email;
        this.password = password;
    }

    public UserAccountInformationModel() {
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserAliasName() {
        return userAliasName;
    }

    public void setUserAliasName(String userAliasName) {
        this.userAliasName = userAliasName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
