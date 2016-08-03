package com.elf.Fragment.TopicViewholder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.elf.R;
import com.elf.model.Testinfo;
import com.elf.model.TopicInfo;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 3/8/16.
 */
public class TopicParentViewholder extends ParentViewHolder {
    public  @BindView(R.id.test_no)
    TextView tNo_tv;
    public @BindView(R.id.test_status) TextView status_tv;
    public @BindView(R.id.test_percent) TextView percent_tv;
    public @BindView(R.id.test_dropdown_icon)
    ImageView dropdown;
    @BindDrawable(R.drawable.ic_arrow_drop_up_black_48dp)
    Drawable up_icon;
    @BindDrawable(R.drawable.ic_arrow_drop_down_black_48dp) Drawable  down_icon;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public TopicParentViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        dropdown.setImageDrawable(down_icon);
        dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded()){
                    collapseView();
                    dropdown.setImageDrawable(down_icon);

                }
                else {
                    expandView();
                    dropdown.setImageDrawable(up_icon);
                }
            }
        });

    }


    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return  false;
    }

    public void bind(TopicInfo tv){
        tNo_tv.setText(tv.getTopic_name());
        status_tv.setText(tv.getPercent_achieved());




    }
}
