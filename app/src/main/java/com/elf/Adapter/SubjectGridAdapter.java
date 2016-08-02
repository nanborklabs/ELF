package com.elf.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.elf.R;

/**
 * Created by nandhu on 2/8/16.
 */
public class SubjectGridAdapter extends BaseAdapter {

    private Context mContext;
    private int[] DrwableRes={R.drawable.physics,R.drawable.chemistry,R.drawable.maths,R.drawable.dna};
    public SubjectGridAdapter(Context context) {
        this.mContext=context;
    }
        public View mView;
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return (Object)mView;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView==null){
           convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_home,parent,false);
           mView=convertView;

       }
        String sub_text;
        String percent;
        int bg;


        switch (position){
            case 0:
                sub_text="Physics";
                percent="72%";
                bg= ContextCompat.getColor(mContext,R.color.phsycis_bg);
                break;
            case 1:
                sub_text="Chemistry";
                percent="72%";
                bg= ContextCompat.getColor(mContext,R.color.chem_bg);
                break;
            case 2:
                sub_text="Maths";
                percent="72%";
                bg= ContextCompat.getColor(mContext,R.color.maths_bg);
                break;
            case 3:
                sub_text="Biology";
                percent="72%";
                bg= ContextCompat.getColor(mContext,R.color.bio_bg);
                break;
            default:
                sub_text="sdfd";
                percent="dsfdf";
                bg=123;
        }
        int bg_tv=ContextCompat.getColor(mContext,R.color.cardview_light_background);
        TextView tv = (TextView) convertView.findViewById(R.id.subject_title);
        tv.setText(sub_text);
        tv.setTextColor(bg_tv);
        convertView.setBackgroundColor(bg);

        ImageView img=(ImageView)convertView.findViewById(R.id.subject_logo);
        img.setImageDrawable(ContextCompat.getDrawable(mContext,DrwableRes[position]));

        TextView percet=(TextView)convertView.findViewById(R.id.percent);
        percet.setText(percent);
        percet.setTextColor(bg_tv);
        return convertView;



    }
}
