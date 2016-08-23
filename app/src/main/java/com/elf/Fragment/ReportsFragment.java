package com.elf.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.elf.Adapter.ReportPagerAdapter;
import com.elf.EventBus.ReportRadioButtonEvent;
import com.elf.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 28/7/16.
 */
public class ReportsFragment extends android.support.v4.app.Fragment implements RadioGroup.OnCheckedChangeListener {


    private View mView;

    private static final String PREFS="ELF_PARENT";


    @BindView(R.id.report_username) TextView mUserName;
    @BindView(R.id.report_class) TextView mClassname;


    //the vie view pager for each subject
    @BindView(R.id.report_pager) ViewPager mPager;
    @BindView(R.id.report_tab) TabLayout mTabLayout;

    //the radio group of subjects
    public @BindView(R.id.radio_gro) RadioGroup mRadioGroup;


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

        populateInfo();
        RadioButton rb = (RadioButton)mRadioGroup.findViewById(R.id.radio_p);
        rb.setChecked(true);

        if (fm!=null){
            mPager.setAdapter(new ReportPagerAdapter(fm));

        }
        else {
            fm=getChildFragmentManager();
            mPager.setAdapter(new ReportPagerAdapter(fm));
        }
        RadioGroup button=(RadioGroup) mView.findViewById(R.id.radio_gro);
        button.setOnCheckedChangeListener(this);

//        button.setOnCheckedChangeListener(this);

//        button.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);
        mTabLayout.setupWithViewPager(mPager);
        return mView;


    }

    private void populateInfo() {
        final SharedPreferences sf = getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        mUserName.setText(sf.getString("firstname","null"));
        mClassname.setText(sf.getString("classname","null"));
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_p:
                EventBus.getDefault().post(new ReportRadioButtonEvent("P"));
            case R.id.radio_m:
                if (checked){
                    EventBus.getDefault().post(new ReportRadioButtonEvent("M"));
                }
                    break;
            case R.id.radio_b:
                if (checked) {
                    EventBus.getDefault().post(new ReportRadioButtonEvent("B"));
                }
                    break;
            case R.id.radio_c:
                if (checked){
                    EventBus.getDefault().post(new ReportRadioButtonEvent("C"));
                }
                    break;
            case R.id.radio_cs:
                if (checked){
                    EventBus.getDefault().post(new ReportRadioButtonEvent("CS"));
                }
                    break;
        }


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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        //each fragment must register itself with this evetn and should change subject id and make request
        //according to that
      int id=  mRadioGroup.getCheckedRadioButtonId();
        Log.d("repsort", "onCheckedChanged: "+id);
        switch(id) {
            case R.id.radio_p:
                EventBus.getDefault().post(new ReportRadioButtonEvent("P"));
                break;
            case R.id.radio_m:
                    EventBus.getDefault().post(new ReportRadioButtonEvent("M"));
                break;
            case R.id.radio_b:
                    EventBus.getDefault().post(new ReportRadioButtonEvent("B"));
                break;
            case R.id.radio_c:
                    EventBus.getDefault().post(new ReportRadioButtonEvent("C"));
                break;
            case R.id.radio_cs:
                    EventBus.getDefault().post(new ReportRadioButtonEvent("CS"));
                  break;
        }


    }




}
