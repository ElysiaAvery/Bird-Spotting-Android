package com.example.guest.aviary.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.aviary.R;
import com.example.guest.aviary.adapters.BirdPageAdapter;
import com.example.guest.aviary.models.Bird;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BirdDetailActivity extends AppCompatActivity{
    @Bind(R.id.viewPager) ViewPager mViewPager;
    @Bind(R.id.pagerHeader) PagerTabStrip mPagerHeader;
    private BirdPageAdapter adapterViewPager;
    ArrayList<Bird> mBirds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_detail);
        ButterKnife.bind(this);

        Typeface elegantFont = Typeface.createFromAsset(getAssets(), "fonts/AquilineTwo.ttf");

        for (int i = 0; i < mPagerHeader.getChildCount(); ++i) {
            View nextChild = mPagerHeader.getChildAt(i);
            if (nextChild instanceof TextView) {
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(elegantFont);
            }
        }

        mBirds = Parcels.unwrap(getIntent().getParcelableExtra("birds"));

        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new BirdPageAdapter(getSupportFragmentManager(), mBirds);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }


}
