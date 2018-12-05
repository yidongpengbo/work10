package com.example.week2.Model;

import com.example.week2.MyCallBack;
import com.example.week2.UserBean;


public interface IModel {
    void requestData(String path, MyCallBack callBack);
    void dengData(UserBean userBean,String path, MyCallBack callBack);
}
