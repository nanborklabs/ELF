package com.elf.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.elf.Fragment.TesViewholder.TestChildViewHolder;
import com.elf.Fragment.TesViewholder.TestParentViewHolder;
import com.elf.R;
import com.elf.model.TopicInfo;

import java.util.List;

/**
 * Created by nandhu on 3/8/16.
 */
public class TopicReportAdapter extends ExpandableRecyclerAdapter<TestParentViewHolder,TestChildViewHolder> {


    LayoutInflater inflater;

    /**
     * Primary constructor. Sets up {@link #mParentItemList} and {@link #mItemList}.
     * <p/>
     * Changes to {@link #mParentItemList} should be made through add/remove methods in
     * {@link ExpandableRecyclerAdapter}
     *
     * @param parentItemList List of all {@link ParentListItem} objects to be
     *                       displayed in the RecyclerView that this
     *                       adapter is linked to
     */
    public TopicReportAdapter(Context mContext,List<TopicInfo> parentItemList) {
        super(parentItemList);
        inflater=LayoutInflater.from(mContext);

    }

    @Override
    public TestParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View mView=inflater.inflate(R.layout.test_parent,parentViewGroup,false);
        return new TestParentViewHolder(mView);
    }

    @Override
    public TestChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        return null;
    }

    @Override
    public void onBindParentViewHolder(TestParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {

    }

    @Override
    public void onBindChildViewHolder(TestChildViewHolder childViewHolder, int position, Object childListItem) {

    }
}
