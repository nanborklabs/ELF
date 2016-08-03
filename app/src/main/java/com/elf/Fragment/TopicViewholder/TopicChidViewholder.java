package com.elf.Fragment.TopicViewholder;

import android.support.v4.view.GravityCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.elf.R;
import com.elf.model.Testdetail;
import com.elf.model.TopicDetail;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 3/8/16.
 */
public class TopicChidViewholder extends ChildViewHolder {

    @BindView(R.id.topic_tv)
    TextView topic;
    @BindView(R.id.topic_names) TextView topics;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public TopicChidViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(TopicDetail mDetail){

        topics.setText(mDetail.getLesson());
        topics.setText("Questions Asked: "+mDetail.getQuestion_asked());

    }
}
