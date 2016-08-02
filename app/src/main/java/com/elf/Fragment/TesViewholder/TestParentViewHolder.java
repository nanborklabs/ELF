package com.elf.Fragment.TesViewholder;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.elf.R;
import com.elf.model.Testinfo;

import butterknife.BindDrawable;
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
@BindDrawable(R.drawable.ic_arrow_drop_up_black_48dp)
    Drawable up_icon;
    @BindDrawable(R.drawable.ic_arrow_drop_down_black_48dp) Drawable  down_icon;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public TestParentViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this,itemView);
        dropdown.setImageDrawable(down_icon);
        final TransitionDrawable mTransitionDrawable=(TransitionDrawable) dropdown.getBackground();
        mTransitionDrawable.setCrossFadeEnabled(true);
        dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click", "onClick: ");
                if (isExpanded()){
                    collapseView();
                    mTransitionDrawable.startTransition(300);
                }
                else {
                    expandView();
                    mTransitionDrawable.startTransition(300);
                }
            }
        });


    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        if (expanded){
            dropdown.setImageDrawable(up_icon);
        }
        else {
            dropdown.setImageDrawable(down_icon);
        }
    }

    public void bind(Testinfo tv){
        tNo_tv.setText(tv.getTestno());
        status_tv.setText(tv.getStatus());
        percent_tv.setText(tv.getPercent());



    }

    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return true;
    }
}
