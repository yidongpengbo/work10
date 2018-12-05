package com.example.week2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;

import com.example.week2.Adapter.PullAdapter;
import com.example.week2.Model.IModelImp;
import com.example.week2.Presenter.PresenterImp;
import com.example.week2.View.IView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import static android.content.SharedPreferences.*;
import static android.widget.CompoundButton.*;

/**
 * @author lenovo
 */
public class MainActivity extends AppCompatActivity implements IView {
                EditText phone,pass;
                CheckBox remember,automatic;
                Button button;
                PresenterImp presenterImp;
    private SharedPreferences shard;
    private Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源ID
        phone=findViewById(R.id.phone);
        pass=findViewById(R.id.pass);
        remember=findViewById(R.id.remember);
        automatic=findViewById(R.id.automatic);
        button=findViewById(R.id.button);
        presenterImp=new PresenterImp(this);
        shard=getSharedPreferences("uuu",MODE_PRIVATE);
        editor=shard.edit();
        initData();
    }
    String shu_path;
    private void initData() {
        //********************
        boolean ji_checked = shard.getBoolean("ji_ischecked", false);
        if(ji_checked){
            //获取到shared的值
            String ji_name = shard.getString("phone", null);
            String ji_pass = shard.getString("pass", null);
            //放入到输入框
            phone.setText(ji_name);
            pass.setText(ji_pass);
            //记住账号选中
            remember.setChecked(true);
        }
        //**********************
        boolean zi_checked = shard.getBoolean("zi_checked", false);
        if(zi_checked){
            //不需要输入框直接跳到下一个页面
            Intent it=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(it);
            finish();
        }

        //当勾选了自动登录,每次启动项目，都会直接打开第二个页面
        automatic.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    //记住账号为选中状态
                    remember.setChecked(true);
                }else{
                    //如果没选，则将shared存的数据全都清空，然后进入第一个页面，
                    editor.clear();
                    editor.commit();
                }
            }
        });



        //登录的点击事件*********************************
        button.setOnClickListener(new View.OnClickListener() {
            private UserBean userBean;
            @Override
            public void onClick(View v) {
                //得到输入的数据
                String shu_phone = phone.getText().toString();
                String shu_pass = pass.getText().toString();
                userBean = new UserBean(shu_phone, shu_pass);
                //如果登录成功，将号码和密码放入到shard
                shu_path="http://www.xieast.com/api/user/login.php?username="+userBean.getPhone()+"&password="+userBean.getPass();
                presenterImp.startDengData(userBean,shu_path);
            }
        });
    }


    @Override       //data:shu_phone,shu_pass
    public void setData(Object data) {
        if(remember.isChecked()){
            //将值保存到shard
            UserBean data1 = (UserBean) data;
            String phone1 = ((UserBean) data).getPhone();
            String pass1 = data1.getPass();
            //将值存入到shard
            editor.putString("phone", phone1);
            editor.putString("pass", pass1);
            //存入一个勾选了的状态值
            editor.putBoolean("ji_ischecked", true);
            //提交，此时，输入的值存到shared中，再下次启动时，将值从shared中取出来放到输入框
            //且记住账号为选中状态，
            editor.commit();

        }else{
            editor.clear();
            editor.commit();
        }
        //判断自动登录是否选中
        if(automatic.isChecked()){
            editor.putBoolean("zi_ischecked", true);
            editor.commit();
        }

        //跳转
        Intent it=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(it);
        finish();


    }
}
