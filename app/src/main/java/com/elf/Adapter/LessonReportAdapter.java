package com.elf.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elf.R;
import com.elf.model.Lessoninfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 3/8/16.
 */
public class LessonReportAdapter extends RecyclerView.Adapter<LessonReportAdapter.Lessonholder>{


    public Context mContext;
    private static final String TAG="LESSON ADAPTER";
    public LayoutInflater inflater;
    private List<Lessoninfo> mlist;

    public LessonReportAdapter(Context mContext, List<Lessoninfo> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
        Log.d(TAG, "LessonReportAdapter: ");
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public Lessonholder onCreateViewHolder(ViewGroup parent, int viewType) {
       if (inflater==null) {
           inflater = LayoutInflater.from(parent.getContext());
       }
        View view =inflater.inflate(R.layout.lesson_report_item,parent,false);
        return new Lessonholder(view);
    }

    @Override
    public void onBindViewHolder(Lessonholder holder, int position) {
        holder.mLessonname.setText(mlist.get(position).getmLessonName());
        holder.mpercent.setText(mlist.get(position).getmPercentage());
        holder.mRightAnswer.setText(mlist.get(position).getmCorrectQuestions());
        holder.qasked.setText(mlist.get(position).getmQuestionsAksed());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public static class Lessonholder extends  RecyclerView.ViewHolder {
        @BindView(R.id.lesson_report_lesson_name)
        TextView mLessonname;
        @BindView(R.id.lesson_report_percent)
        TextView mpercent;
        @BindView(R.id.lesson_report_q_asked)
        TextView qasked;
        @BindView(R.id.lesson_report_right_answer)
        TextView mRightAnswer;





        public Lessonholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
