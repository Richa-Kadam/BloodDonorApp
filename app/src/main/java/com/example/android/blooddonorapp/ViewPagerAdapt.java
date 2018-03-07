package com.example.android.blooddonorapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by user on 28-12-2017.
 */

public class ViewPagerAdapt extends FragmentPagerAdapter {

    ArrayList<Fragment> frag = new ArrayList<Fragment>();
    ArrayList<String> fragTitle = new ArrayList<String>();

    public ViewPagerAdapt(FragmentManager fm) {
        super(fm);
    }


    public void addFrag(Fragment fragment, String title) {
        frag.add(fragment);
        fragTitle.add(title);
    }


    @Override
    public Fragment getItem(int position) {
        return frag.get(position);
    }

    @Override
    public int getCount() {
        return frag.size();
    }

    public CharSequence getPageTitle(int position) {
        return fragTitle.get(position);
    }

}
