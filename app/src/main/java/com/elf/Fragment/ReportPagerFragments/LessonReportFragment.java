package com.elf.Fragment.ReportPagerFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.elf.Adapter.LessonReportAdapter;
import com.elf.Fragment.TesViewholder.TestParentViewHolder;
import com.elf.R;
import com.elf.model.LessonDeatils;
import com.elf.model.Lessoninfo;
import com.elf.model.Testdetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 29/7/16.
 */
public class LessonReportFragment extends Fragment{
    public View mView;
    @BindView(R.id.lesson_rv_list)
    RecyclerView lesson_list;
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
        mView=inflater.inflate(R.layout.lesson_notification,container,false);
        ButterKnife.bind(this,mView);
        LessonDeatils deatils =new LessonDeatils(1,2,3);
        List<LessonDeatils> testdetailList=new ArrayList<>();
        testdetailList.add(deatils);
        Lessoninfo lessoninfo=new Lessoninfo("Lesson 1", 35,"Passed",testdetailList);
        List<Lessoninfo> infoList=new ArrayList<>();
        infoList.add(lessoninfo);
        ExpandableRecyclerAdapter mAdapter=new LessonReportAdapter(getContext(),infoList);
        lesson_list.setAdapter(mAdapter);

//        lesson_list.setAdapter();
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
}
