package com.elf.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.elf.Fragment.LessonViewholder.LessonChildViewholder;
import com.elf.Fragment.LessonViewholder.LessonParentViewholder;
import com.elf.R;
import com.elf.model.LessonDeatils;
import com.elf.model.Lessoninfo;

import java.util.List;

/**
 * Created by nandhu on 3/8/16.
 */
public class LessonReportAdapter extends ExpandableRecyclerAdapter<LessonParentViewholder,LessonChildViewholder>{

    /**
     * Primary constructor. Sets up {@link #mParentItemList} and {@link #mItemList}.
     * <p>
     * Changes to {@link #mParentItemList} should be made through add/remove methods in
     * {@link ExpandableRecyclerAdapter}
     *
     * @param parentItemList List of all {@link ParentListItem} objects to be
     *                       displayed in the RecyclerView that this
     *                       adapter is linked to
     */

    public Context mContext;
    public LayoutInflater inflater;
    public LessonReportAdapter(Context mContext,@NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        this.mContext=mContext;
        inflater=LayoutInflater.from(mContext);
    }


    @Override
    public LessonParentViewholder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View mView=inflater.inflate(R.layout.test_parent,parentViewGroup,false);
        return new LessonParentViewholder(mView);
    }

    @Override
    public LessonChildViewholder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View mView=inflater.inflate(R.layout.test_child,childViewGroup,false);
        return new LessonChildViewholder(mView);
    }

    @Override
    public void onBindParentViewHolder(LessonParentViewholder parentViewHolder, int position, ParentListItem parentListItem) {
        Lessoninfo lessoninfo=(Lessoninfo)parentListItem;
        parentViewHolder.bind(lessoninfo);
    }

    @Override
    public void onBindChildViewHolder(LessonChildViewholder childViewHolder, int position, Object childListItem) {
        LessonDeatils deatils=(LessonDeatils)childListItem;
        childViewHolder.bind(deatils);
    }
}
