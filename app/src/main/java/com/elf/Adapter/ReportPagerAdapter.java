package com.elf.Adapter;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.RadioGroup;

import com.elf.Fragment.ReportPagerFragments.LessonReportFragment;
import com.elf.Fragment.ReportPagerFragments.TestReportFragment;
import com.elf.Fragment.ReportPagerFragments.TopicReportFragment;
import com.elf.Fragment.ReportsFragment;
import com.elf.R;

/**
 * Created by nandhu on 29/7/16.
 */
public class ReportPagerAdapter extends FragmentStatePagerAdapter {


//    variable to show which radio button is clicked
    private static int sub_showing=0;
    public RadioGroup mRadioGroup;
    ReportsFragment.SubjectChanged listener;
    public ReportPagerAdapter(FragmentManager fm, RadioGroup mRadioGroup, ReportsFragment.SubjectChanged listerner) {
        super(fm);
        this.mRadioGroup=mRadioGroup;
        this.listener=listerner;
//        this.mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Test";
            case 1:
                return "Topic";
            case 2:
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
                testReportFragment.setmSubjectChanged(listener);
                mFragment=testReportFragment;
                break;
            case 1:
                mFragment=  new TopicReportFragment();
                break;
            case 2:
                mFragment= new LessonReportFragment();
                break;
        }

        //pass the urls' here for the three fragmetns
        // each Fragment has Different URl for each topic,lesson,Test pull accordingly
        Bundle b=new Bundle();
        b.putInt("SUB",sub_showing);

        mFragment.setArguments(b);



        return mFragment;
    }

/*
* if radio utton is changed, change sub_showing value and pas the required url to Fragments to pull and show the value
*
* */


}
