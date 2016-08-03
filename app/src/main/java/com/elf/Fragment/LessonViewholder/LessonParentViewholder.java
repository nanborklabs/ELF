package com.elf.Fragment.LessonViewholder;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.elf.R;
import com.elf.model.LessonDeatils;
import com.elf.model.Lessoninfo;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 3/8/16.
 */
public class LessonParentViewholder extends ParentViewHolder {



    public  @BindView(R.id.test_no)
    TextView lesson_no;
    public @BindView(R.id.test_status) TextView weighatage_mark;
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
    public LessonParentViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
       dropdown.setImageDrawable(down_icon);

    }

    public void bind(Lessoninfo info){
        lesson_no.setText(info.getLesson_no());
        weighatage_mark.setText(info.getWeightage());
        percent_tv.setText(info.getStatus());
    }
}
