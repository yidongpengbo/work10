package com.example.week2.Model;
import com.example.week2.DengBean;
import com.example.week2.MyCallBack;
import com.example.week2.NetUtil;
import com.example.week2.NewsBean;
import com.example.week2.UserBean;

import static android.content.Context.MODE_PRIVATE;

public class IModelImp implements IModel {
       MyCallBack mycallBack;
    @Override//得到逻辑数据，返回值给接口
    public void requestData(String path, final MyCallBack mycallBack) {
                this.mycallBack=mycallBack;
        NetUtil.getInstance().getAsyn(path, NewsBean.class, new NetUtil.CallBack() {
            @Override
            public void onsuccess(Object o) {
                mycallBack.setIModeData(o);
            }
        });
    }


    @Override       //登录的逻辑
    public void dengData(final UserBean userBean, String path, MyCallBack callBack) {
                this.mycallBack=callBack;
          NetUtil.getInstance().getAsyn(path, DengBean.class, new NetUtil.CallBack<DengBean>() {
              @Override
              public void onsuccess(DengBean o) {
                  String msg = o.getMsg();
                  if (msg.equals("登录成功")){
                      //将号码和密码保存到接口
                      //跳转
                       mycallBack.setDengData(userBean);
                  }
              }
          });
    }


}
