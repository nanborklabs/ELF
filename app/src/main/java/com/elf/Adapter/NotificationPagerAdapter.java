package com.elf.Adapter;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.elf.Fragment.NotificationPagerFragment.LessonNotifFragment;
import com.elf.Fragment.NotificationPagerFragment.TestNotifactionFragment;
import com.elf.Fragment.NotificationPagerFragment.TopicNotiFragment;

/**
 * Created by nandhu on 29/7/16.
 */
public class NotificationPagerAdapter extends FragmentStatePagerAdapter {

    public NotificationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 1:
                return "Test";
            case 2:
                return "Topic";
            case 3:
                return "Lesson";

        }
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new TestNotifactionFragment();
            case 2:
                return new TopicNotiFragment();
            case 3:
                return new LessonNotifFragment();
        }

        return null;
    }
}
