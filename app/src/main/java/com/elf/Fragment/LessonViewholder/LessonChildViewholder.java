package com.elf.Fragment.LessonViewholder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.elf.R;
import com.elf.model.LessonDeatils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 3/8/16.
 */
public class LessonChildViewholder extends ChildViewHolder{


    public     @BindView(R.id.topic_tv)
    TextView weightage;


    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public LessonChildViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(LessonDeatils mdetails){
       String text="One Mark : "+mdetails.getOne_mark()
               +"    Six Mark:  "+mdetails.getSix_mark()+
               "    Two mark:  "+mdetails.getTwo_mark()+
               "    Ten Mark:  "+mdetails.getTen_mark();
        weightage.setText(text);
    }
}
