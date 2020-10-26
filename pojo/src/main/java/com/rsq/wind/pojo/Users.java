package com.rsq.wind.pojo;

import java.io.Serializable;

/**
 * Author: shaoqing
 * date-time: 2020-10-18 20:18
 **/
public class Users implements Serializable {
    private String userId;
    private String userName;
    private String password;
    private String userRand;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRand() {
        return userRand;
    }

    public void setUserRand(String userRand) {
        this.userRand = userRand;
    }
}
