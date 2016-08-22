package com.elf.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by nandhu on 3/8/16.
 */
public class TopicReportAdapter extends RecyclerView.Adapter <TopicReportAdapter.TopicHolder> {



    LayoutInflater inflater;
//    private List<TopicInfo> Mlist;



    @Override
    public TopicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TopicHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class TopicHolder extends RecyclerView.ViewHolder{
        public TopicHolder(View itemView) {
            super(itemView);
        }
    }
}
