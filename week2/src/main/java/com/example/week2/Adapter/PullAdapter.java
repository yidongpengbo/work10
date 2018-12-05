package com.example.week2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.week2.NewsBean;
import com.example.week2.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PullAdapter extends BaseAdapter {
    private List<NewsBean.DataBean> mjihe;
    private Context mcon;

    public PullAdapter(Context mcon) {
        this.mcon = mcon;
        mjihe=new ArrayList<>();
    }

    /**
     * 刷新
     * @param jihe
     */
    public void setMjihe(List <NewsBean.DataBean> jihe) {
        if (jihe!=null){
            mjihe.clear();
            mjihe.addAll(jihe);
            notifyDataSetChanged();
        }
    }

    /**
     * 加载
     * @return jihe
     */
    public void addMjihe(List <NewsBean.DataBean> jihe){
        if (jihe!=null){
            mjihe.addAll(jihe);
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return mjihe.size();
    }

    @Override
    public NewsBean.DataBean getItem(int position) {
        return mjihe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=LayoutInflater.from(mcon).inflate(R.layout.pulladapter,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.bindData(getItem(position));
        return convertView;
    }
    class ViewHolder{
        TextView title;
        ImageView thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03;
        View view;

        public ViewHolder(View view){
            title=view.findViewById(R.id.title);
            thumbnail_pic_s=view.findViewById(R.id.thumbnail_pic_s);
            thumbnail_pic_s02=view.findViewById(R.id.thumbnail_pic_s02);
            thumbnail_pic_s03=view.findViewById(R.id.thumbnail_pic_s03);
            view.setTag(this);
        }


        public void bindData(NewsBean.DataBean item) {
            title.setText(item.getTitle());
            ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s(),thumbnail_pic_s);
            ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s02(),thumbnail_pic_s02);
            ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s03(),thumbnail_pic_s03);
        }
    }
}
