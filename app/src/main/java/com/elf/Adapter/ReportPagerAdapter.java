package com.elf.Adapter;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.RadioGroup;

import com.elf.Fragment.ReportPagerFragments.LessonReportFragment;
import com.elf.Fragment.ReportPagerFragments.TestReportFragment;
import com.elf.Fragment.ReportsFragment;

/**
 * Created by nandhu on 29/7/16.
 */
public class ReportPagerAdapter extends FragmentStatePagerAdapter {




    public ReportPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Test";

            case 1:
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
        Fragment mFragment=null;
        switch (position){
            case 0:
              TestReportFragment testReportFragment=new TestReportFragment();

                mFragment=testReportFragment;
                break;
            case 1:
                LessonReportFragment lessonReportFragment=new LessonReportFragment();
                mFragment=  lessonReportFragment;
                break;

        }

        //pass the urls' here for the three fragmetns
        // each Fragment has Different URl for each topic,lesson,Test pull accordingly






        return mFragment;
    }

/*
* if radio utton is changed, change sub_showing value and pas the required url to Fragments to pull and show the value
*
* */


}
