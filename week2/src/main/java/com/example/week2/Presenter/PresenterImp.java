package com.example.week2.Presenter;

import com.example.week2.Model.IModelImp;
import com.example.week2.MyCallBack;
import com.example.week2.NetUtil;
import com.example.week2.UserBean;
import com.example.week2.View.IView;

public class PresenterImp implements IPresenter {
    IModelImp iModelImp;
    IView iView;

    public PresenterImp(IView iView) {
        this.iView = iView;
        iModelImp=new IModelImp();
    }

    @Override
    public void startData(String path) {
             iModelImp.requestData(path, new MyCallBack() {
                 @Override
                 public void setIModeData(Object data) {
                     iView.setData(data);
                 }

                 @Override
                 public void setDengData(Object data) {
                     return;
                 }
             });
    }

    @Override
    public void startDengData(UserBean userBean, String path) {
                iModelImp.dengData(userBean, path, new MyCallBack() {
                    @Override
                    public void setIModeData(Object data) {
                       return;
                    }

                    @Override
                    public void setDengData(Object data) {
                        iView.setData(data);
                    }
                });
    }
}
