package com.elf.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elf.Adapter.NotificationPagerAdapter;
import com.elf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 28/7/16.
 */
public class ReportsFragment extends android.support.v4.app.Fragment {


    private View mView;
    @BindView(R.id.report_pager) ViewPager mPager;
    @BindView(R.id.report_tab) TabLayout mTabLayout;
     FragmentManager fm;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm=getChildFragmentManager();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      mView=inflater.inflate(R.layout.report,container,false);
       ButterKnife.bind(this,mView);

        if (fm!=null){
            mPager.setAdapter(new NotificationPagerAdapter(fm));

        }
        else {
            fm=getChildFragmentManager();
            mPager.setAdapter(new NotificationPagerAdapter(fm));
        }
        mTabLayout.setupWithViewPager(mPager);
        return mView;


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
