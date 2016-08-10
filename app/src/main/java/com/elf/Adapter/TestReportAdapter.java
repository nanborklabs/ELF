package com.elf.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.elf.Fragment.TesViewholder.TestChildViewHolder;
import com.elf.Fragment.TesViewholder.TestParentViewHolder;
import com.elf.R;
import com.elf.model.Testdetail;
import com.elf.model.Testinfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 2/8/16.
 */
public class TestReportAdapter extends RecyclerView.Adapter<TestReportAdapter.TestHolder>{

String TAG="Adapter";

    private LayoutInflater inflater;
    private Context mContext;
    private List<Testinfo> mList;

    public TestReportAdapter(Context context, List<Testinfo> info) {
       this.mContext=context;
        inflater=LayoutInflater.from(context);
        Log.d(TAG, "TestReportAdapter: ");
        this.mList=info;
    }

    @Override
    public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.test_report_item,parent,false);
        return new TestHolder(v);
    }

    @Override
    public void onBindViewHolder(TestHolder holder, int position) {
        holder.topic.setText(mList.get(position).getTopic());
        holder.percent.setText(mList.get(position).getPercent());
        holder.percent.setText(mList.get(position).getPercent());
        holder.percent.setText(mList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class TestHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.test_report_item_topic)
        TextView topic;
        @BindView(R.id.test_report_item_testno)
        TextView testno;
        @BindView(R.id.test_report_item_status)
        TextView status;
        @BindView(R.id.test_report_item_percent)
        TextView percent;

        public TestHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

