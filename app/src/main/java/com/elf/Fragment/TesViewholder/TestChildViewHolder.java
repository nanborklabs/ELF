package com.elf.Fragment.TesViewholder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.elf.R;
import com.elf.model.Testdetail;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 2/8/16.
 */
public class TestChildViewHolder extends ChildViewHolder {

    @BindView(R.id.topic_tv)
    TextView topic;
    @BindView(R.id.topic_names) TextView topics;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public TestChildViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Testdetail mDTestdetail){
        topics.setText(mDTestdetail.getTopics());
    }
}
