package com.example.week2.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.week2.Adapter.PullAdapter;
import com.example.week2.NewsBean;
import com.example.week2.Presenter.PresenterImp;
import com.example.week2.R;
import com.example.week2.View.IView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class DataFragment extends Fragment implements IView{
            PullToRefreshListView pull;
            PullAdapter adapter;
            int mPage;
            PresenterImp presenterImp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.datafragment,container,false);
            pull=view.findViewById(R.id.pull);
            init();
        return view;
    }

    private void init() {
        adapter=new PullAdapter(getActivity());
        presenterImp=new PresenterImp(this);
        pull.setAdapter(adapter);
        pull.setMode(PullToRefreshListView.Mode.BOTH);
        mPage=1;
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2 <ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase <ListView> refreshView) {
                mPage=1;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase <ListView> refreshView) {
                initData();
            }
        });
        initData();
    }

    private void initData() {
        path="http://www.xieast.com/api/news/news.php";
        //将参数放入到P
        presenterImp.startData(path);
    }

    String path;
    @Override
    public void setData(Object data) {
        NewsBean data1 = (NewsBean) data;
        if (mPage==1){
            adapter.setMjihe(data1.getData());
        }else {
            adapter.addMjihe(data1.getData());
        }
        pull.onRefreshComplete();
        mPage++;
    }
}
