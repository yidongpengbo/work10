package com.example.week2;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {
    //单例
    private static NetUtil instance;
    private NetUtil(){}
    public static NetUtil getInstance(){
        if (instance==null){
            instance=new NetUtil();
        }
        return instance;
    }

    /**
     * 1.根据网址获取数据
     * @param path
     * @return
     */
    public String getNet(String path){
        String net="";
        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode==200){
                InputStream inputStream = urlConnection.getInputStream();
                //转换成字符
                net = getString(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return net;
    }

    /**
     * 2.转换成字符
     * @param stream
     * @return
     * @throws IOException
     */
    public String getString(InputStream stream) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);
        StringBuilder builder = new StringBuilder();
        for (String i = reader.readLine(); i !=null ; i=reader.readLine()) {
            builder.append(i);
        }
        return builder.toString();
    }

    /**
     * 3.解析数据
     * @param path
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T  getGson(String path,Class clazz){
        T o=null;
        String net = getNet(path);
        Gson gson = new Gson();
        o = (T) gson.fromJson(net, clazz);
        return o;
    }

    /**
     * 4.定义接口
     * @param <T>
     */
    public interface CallBack<T>{
        void onsuccess(T t);
    }

    /**
     * 5.子线程运行
     * @param path
     * @param clazz
     * @param callBack
     */

    @SuppressLint("StaticFieldLeak")
    public void getAsyn(String path, final Class clazz, final CallBack callBack){
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                return getGson(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callBack.onsuccess(o);
            }
        }.execute(path);
    }
}
