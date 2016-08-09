package com.elf.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.elf.Adapter.SubjectGridAdapter;

import com.elf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 28/7/16.
 */
public class HomeFragment extends Fragment {


    private View mView;
    private static final String PREFS="LOGIN";
    @BindView(R.id.school_name) TextView mSchoolName;
    @BindView(R.id.location) TextView mLocation;
    @BindView(R.id.std) TextView mStandard;
    @BindView(R.id.username_home_frag) TextView mUsername;
    @BindView(R.id.cv_home_frag) CardView mDash;
    @BindView(R.id.home_grid)
    GridView home_grid;
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_frag, container, false);
        ButterKnife.bind(this, mView);

        SharedPreferences prefs=getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        mLocation.setText(prefs.getString("district","no name"));
        mUsername.setText(prefs.getString("firstname","no school"));

        home_grid.setAdapter(new SubjectGridAdapter(getContext()));
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
