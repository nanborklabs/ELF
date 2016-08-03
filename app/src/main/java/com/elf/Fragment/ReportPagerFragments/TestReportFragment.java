package com.elf.Fragment.ReportPagerFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.elf.Adapter.TestReportAdapter;
import com.elf.Fragment.ReportsFragment;
import com.elf.R;
import com.elf.model.Testdetail;
import com.elf.model.Testinfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 29/7/16.
 */
public class TestReportFragment extends Fragment implements ExpandableRecyclerAdapter.ExpandCollapseListener,ReportsFragment.SubjectChanged {
    public View mview;
    @BindView(R.id.test_noti_recycler_view) RecyclerView mList;
    ReportsFragment.SubjectChanged mSubjectChanged;

    public void setmSubjectChanged(ReportsFragment.SubjectChanged mSubjectChanged) {
        this.mSubjectChanged = mSubjectChanged;
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

        if (getArguments()==null){
            throw new NullPointerException("Argumetns are null");
        }
        else {
            int Sub_to_Show=getArguments().getInt("SUB");
            Log.d("Sub to Show", ""+Sub_to_Show);


//            once It is found out that which subject is clicked and url obtained
//            hit the url get input stream
//            get JSON keys, Make Objects
//            display objects
//            you need Asyncs or third party library for syncing
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview=inflater.inflate(R.layout.test_notifcaton,container,false);
        ButterKnife.bind(this,mview);
        mList.setLayoutManager(new LinearLayoutManager(getContext()));


        Testdetail deatil=new Testdetail("Eddy current");
        List<Testdetail> deaTestdetails=new ArrayList<>();
        deaTestdetails.add(deatil);
        Testdetail dea=new Testdetail("heelo thereeee");

        Testinfo info=new Testinfo("Test no","passed","72%",deaTestdetails);
        Testinfo info2=new Testinfo("Test no 20","passed","755%",deaTestdetails);

        List<Testinfo> testinfos=new ArrayList<>(2);
        testinfos.add(info);
        testinfos.add(info2);


        ExpandableRecyclerAdapter mAdapter=new TestReportAdapter(getContext(),testinfos);

        mAdapter.setExpandCollapseListener(this);

        mList.setAdapter(mAdapter);
        return mview;
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
    public void onListItemExpanded(int position) {


        Log.d("Expanaded", "onListItemExpanded: " + position);
    }

    @Override
    public void onListItemCollapsed(int position) {
        Log.d("Expanaded", "onListItemCollapsed: ");
    }

    @Override
    public void subjectChanged(int sub) {
        Log.d("interface got", "subjectChanged: "+sub);
    }
}
