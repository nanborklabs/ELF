package com.elf.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.elf.Fragment.TopicViewholder.TopicChidViewholder;
import com.elf.Fragment.TopicViewholder.TopicParentViewholder;
import com.elf.R;
import com.elf.model.Testinfo;
import com.elf.model.TopicDetail;
import com.elf.model.TopicInfo;

import java.util.List;

/**
 * Created by nandhu on 3/8/16.
 */
public class TopicReportAdapter extends ExpandableRecyclerAdapter<TopicParentViewholder, TopicChidViewholder> {


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
    public TopicParentViewholder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View mView=inflater.inflate(R.layout.test_parent,parentViewGroup,false);
        return new TopicParentViewholder(mView);
    }

    @Override
    public TopicChidViewholder onCreateChildViewHolder(ViewGroup childViewGroup){
        View mView=inflater.inflate(R.layout.test_child,childViewGroup,false);
        return new TopicChidViewholder(mView);
    }

    @Override
    public void onBindParentViewHolder(TopicParentViewholder parentViewHolder, int position, ParentListItem parentListItem) {
        TopicInfo info=(TopicInfo) parentListItem;
        parentViewHolder.bind(info);
    }

    @Override
    public void onBindChildViewHolder(TopicChidViewholder childViewHolder, int position, Object childListItem) {
        TopicDetail mDetail=(TopicDetail)childListItem;
        childViewHolder.bind(mDetail);
    }
}
