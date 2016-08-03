package com.elf.Fragment;

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

import com.elf.Adapter.ReportPagerAdapter;
import com.elf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 28/7/16.
 */
public class ReportsFragment extends android.support.v4.app.Fragment implements RadioGroup.OnCheckedChangeListener {


    private View mView;
    @BindView(R.id.report_pager) ViewPager mPager;
    @BindView(R.id.report_tab) TabLayout mTabLayout;
    public @BindView(R.id.radio_gro) RadioGroup mRadioGroup;
    private static  int sub_showing=0;
   public SubjectChanged listerner;
     FragmentManager fm;


    public SubjectChanged getListerner() {
        return listerner;
    }

    public void setListerner(SubjectChanged listerner) {
        this.listerner = listerner;
    }

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
            mPager.setAdapter(new ReportPagerAdapter(fm,mRadioGroup,listerner));

        }
        else {
            fm=getChildFragmentManager();
            mPager.setAdapter(new ReportPagerAdapter(fm, mRadioGroup, listerner));
        }
        RadioGroup button=(RadioGroup) mView.findViewById(R.id.radio_gro);
        button.setOnCheckedChangeListener(this);

//        button.setOnCheckedChangeListener(this);

//        button.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);
        mTabLayout.setupWithViewPager(mPager);
        return mView;


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_p:
                if (checked){
                    sub_showing=0;
                }
                    break;
            case R.id.radio_m:
                if (checked){
                    sub_showing=1;
                }
                    break;
            case R.id.radio_b:
                if (checked){
                    sub_showing=3;
                }
                    break;
            case R.id.radio_c:
                if (checked){
                    sub_showing=2;
                }
                    break;
            case R.id.radio_cs:
                if (checked){
                    sub_showing=4;
                }
                    break;
        }

        Log.d("Radio button lcicked", "button "+sub_showing);
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
        Log.d("Ichecked changed", "Firing up interface: ");
//        listerner.subjectChanged(0);
    }

    public interface SubjectChanged{
        void subjectChanged(int sub);
    }



}
