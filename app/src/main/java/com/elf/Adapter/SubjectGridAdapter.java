package com.elf.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.elf.R;
import com.elf.Utild;
import com.elf.model.SubjectHomeModel;

import java.util.List;

/**
 * Created by nandhu on 2/8/16.
 */
public class SubjectGridAdapter extends BaseAdapter {

    private Context mContext;
    public int Animated_item_count;
    public int last_pos=-1;
    private List<SubjectHomeModel>  mlist;
    private int[] DrwableRes={R.drawable.physics,R.drawable.chemistry,R.drawable.maths,R.drawable.dna};
    public SubjectGridAdapter(Context context, List<SubjectHomeModel> subjectList) {
        this.mContext=context;
        this.mlist=subjectList;
        Animated_item_count=subjectList.size();
    }
        public View mView;
    @Override
    public int getCount() {
        return mlist.size();
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

                bg= ContextCompat.getColor(mContext,R.color.phsycis_bg);
                break;
            case 1:

                bg= ContextCompat.getColor(mContext,R.color.chem_bg);
                break;
            case 2:

                bg= ContextCompat.getColor(mContext,R.color.maths_bg);
                break;
            case 3:

                bg= ContextCompat.getColor(mContext,R.color.bio_bg);
                break;
            default:
                sub_text="sdfd";
                percent="dsfdf";
                bg=ContextCompat.getColor(mContext,R.color.bio_bg);
        }
        sub_text=mlist.get(position).getSubject_name();
        percent=mlist.get(position).getPercentage();
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
        runEnteranim(convertView,position);
        return convertView;



    }

    private void runEnteranim(View convertView, int position) {
        Log.d("Animation",""+position);

        if (position>=Animated_item_count){
            Log.d("Animation","postion one");
            return;
        }
        if (position>last_pos){

            Log.d("Animation","inside if");
            last_pos=position;
            convertView.setTranslationY(Utild.getScreenHeight(mContext));
            convertView.animate().translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
        }
}

