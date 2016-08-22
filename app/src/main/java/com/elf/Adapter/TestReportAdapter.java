package com.elf.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elf.R;
import com.elf.model.Testinfo;

import org.w3c.dom.Text;

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



        this.mList=info;
    }

    @Override
    public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater==null){
            inflater=LayoutInflater.from(parent.getContext());
        }
        View v=inflater.inflate(R.layout.test_report_rv_item,parent,false);
        return new TestHolder(v);
    }

    @Override
    public void onBindViewHolder(TestHolder holder, int position) {
        holder.mSubjectName.setText(mList.get(position).getTopic());
        holder.mTopicName.setText("Trignomentry");
//        holder..setText(mList.get(position).getTestno());
        holder.mPercent.setText(mList.get(position).getPercent());
        holder.mStatus.setText(mList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class TestHolder extends RecyclerView.ViewHolder{


        TextView mStatus;
        TextView mPercent;
        TextView mTopicName;
        TextView mSubjectName;



        public TestHolder(View itemView) {
            super(itemView);
            mSubjectName =  (TextView)itemView.findViewById(R.id.test_report_item_subject_name);
            mStatus =  (TextView)itemView.findViewById(R.id.test_report_item_status);
            mTopicName =  (TextView)itemView.findViewById(R.id.test_report_item_topic_name);
            mPercent =  (TextView)itemView.findViewById(R.id.test_report_item_percent);
        }
    }
}

