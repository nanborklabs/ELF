package com.elf.Fragment.TesViewholder;

import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
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
        final Drawable backgrounds[] = new Drawable[2];
        backgrounds[0]=up_icon;
        backgrounds[1]=down_icon;
        final TransitionDrawable arrowSwitch=new TransitionDrawable(backgrounds);
       arrowSwitch.setCrossFadeEnabled(true);
       /*
       *
       *  final RotateAnimation mAnimation=new RotateAnimation(180,360,0.0f,0.0f);
        mAnimation.setFillAfter(true);
        mAnimation.setDuration(300);
        final RotateAnimation mAnimation2=new RotateAnimation(0,180,0.0f,0.0f);
        mAnimation2.setDuration(300);
         mAnimation2.setFillAfter(true);
         */
//        dropdown.setImageDrawable(down_icon);

//        mTransitionDrawable.setCrossFadeEnabled(true);
        dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click", "onClick: ");

                if (isExpanded()){
                    Log.d("if", "onClick:0 ");
//                    arrowSwitch.startTransition(300);

//                    dropdown.setImageDrawable(arrowSwitch);

                   dropdown.setImageDrawable(down_icon);


                    collapseView();
//                    mTransitionDrawable.startTransition(300);

                }
                else {
                    expandView();
                    Log.d("if", "onClick:1 ");

//                    dropdown.setImageDrawable(arrowSwitch);
//                    arrowSwitch.reverseTransition(300);
                    dropdown.setImageDrawable(up_icon);


//                    mTransitionDrawable.startTransition(300);
                }
//                dropdown.setImageDrawable(arrowSwitch);
            }
        });


    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        if (expanded) {
//            dropdown.animate().rotation(-180).setDuration(300).setInterpolator(new OvershootInterpolator(3.f)).start();
        } else {
//            dropdown.animate().rotation(180).setDuration(300).
//                    setInterpolator(new DecelerateInterpolator()).start();
        }
    }

    public void bind(Testinfo tv){
        tNo_tv.setText(tv.getTestno());
        status_tv.setText(tv.getStatus());
        percent_tv.setText(tv.getPercent());



    }

    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return false;
    }
}
