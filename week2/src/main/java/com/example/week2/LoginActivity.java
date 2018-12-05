package com.example.week2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.week2.Fragment.CadeFragment;
import com.example.week2.Fragment.DataFragment;

public class LoginActivity extends AppCompatActivity {
        ViewPager viewPager;
        RadioButton myData,myCade;
        RadioGroup radioGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logactivity);
        viewPager=findViewById(R.id.viewPager);
        myData=findViewById(R.id.myData);
        myCade=findViewById(R.id.myCade);
        radioGroup=findViewById(R.id.radioGroup);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new DataFragment();
                    case 1:
                        return new CadeFragment();
                    default:
                        return new Fragment();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.myData:
                    viewPager.setCurrentItem(0);
                    return;
                case R.id.myCade:
                    viewPager.setCurrentItem(1);
                    return;
            }
        }
    });




    }
}
