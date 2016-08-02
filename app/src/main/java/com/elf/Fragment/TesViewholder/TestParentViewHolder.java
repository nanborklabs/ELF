package com.elf.Fragment.TesViewholder;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.elf.R;
import com.elf.model.Testinfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 2/8/16.
 */
public class TestParentViewHolder extends ParentViewHolder   {


   public  @BindView(R.id.test_no) TextView tNo_tv;
    public @BindView(R.id.test_status) TextView status_tv;
    public @BindView(R.id.test_percent) TextView percent_tv;
    public @BindView(R.id.test_dropdown_icon) ImageView dropdown;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public TestParentViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this,itemView);


    }

    public void bind(Testinfo tv){
        tNo_tv.setText(tv.getTestno());
        status_tv.setText(tv.getStatus());
        percent_tv.setText(tv.getPercent());

    }
}
