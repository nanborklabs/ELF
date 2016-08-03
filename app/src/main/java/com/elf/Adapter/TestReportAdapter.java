package com.elf.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.elf.Fragment.TesViewholder.TestChildViewHolder;
import com.elf.Fragment.TesViewholder.TestParentViewHolder;
import com.elf.R;
import com.elf.model.Testdetail;
import com.elf.model.Testinfo;

import java.util.List;

/**
 * Created by nandhu on 2/8/16.
 */
public class TestReportAdapter extends ExpandableRecyclerAdapter<TestParentViewHolder,TestChildViewHolder> {



    private LayoutInflater inflater;

    public TestReportAdapter(Context context, List<Testinfo> info) {
        super(info);
        inflater=LayoutInflater.from(context);
    }

    @Override
    public TestParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View mView=inflater.inflate(R.layout.test_parent,parentViewGroup,false);
        return new TestParentViewHolder(mView);
    }

    @Override
    public TestChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
       View mView=inflater.inflate(R.layout.test_child,childViewGroup,false);
        return new TestChildViewHolder(mView);
    }

    @Override
    public void onBindParentViewHolder(TestParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {

        Testinfo testinfo=(Testinfo)parentListItem;
      parentViewHolder.bind(testinfo);



    }

    @Override
    public void onBindChildViewHolder(TestChildViewHolder childViewHolder, int position, Object childListItem) {
        Testdetail detail=(Testdetail)childListItem;
        childViewHolder.bind(detail);
    }
}

