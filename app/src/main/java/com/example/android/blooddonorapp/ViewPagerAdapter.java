package com.example.android.blooddonorapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by user on 01-12-2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> frag = new ArrayList<Fragment>();
    ArrayList<String> fragTitle = new ArrayList<String>();

    public void addFrag(Fragment fragment, String title) {
        frag.add(fragment);
        fragTitle.add(title);


    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
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
