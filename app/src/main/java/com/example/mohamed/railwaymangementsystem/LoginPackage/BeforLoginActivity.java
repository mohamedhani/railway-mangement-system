package com.example.mohamed.railwaymangementsystem.LoginPackage;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mohamed.railwaymangementsystem.R;

public class BeforLoginActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_befor_login);
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout =(LinearLayout) findViewById(R.id.dotsLayout);
        addDotsIndicator(0);



    }
    public void addDotsIndicator (int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for(int i =0;i<mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);

            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length >0){

            mDots[position].setTextColor(getResources().getColor(R.color.white));

        }
    }

}
