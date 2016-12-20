package com.example.guest.aviary.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.aviary.models.Bird;
import com.example.guest.aviary.ui.BirdDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 12/20/16.
 */
public class BirdPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Bird> mBirds;

    public BirdPageAdapter(FragmentManager fm, ArrayList<Bird> birds) {
        super(fm);
        mBirds = birds;
    }


    @Override
    public Fragment getItem(int position) {
        return BirdDetailFragment.newInstance(mBirds.get(position));
    }

    @Override
    public int getCount() {
        return mBirds.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBirds.get(position).getName();
    }
}
