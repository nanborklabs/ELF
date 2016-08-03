package com.elf.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elf.R;
import com.elf.model.NotificationModel;

import java.util.List;

/**
 * Created by nandhu on 3/8/16.
 */
public class NotificationAdapter  extends RecyclerView.Adapter<NotificationAdapter.NotifiactionItemHolder>{
    public View mView;
    private Context mContext;
    private List<NotificationModel> mNotificationModels;
    private int[] noti_icons={R.drawable.result_noti_icon,R.drawable.finished_icon,R.drawable.started_icon};
    public NotificationAdapter(Context context, List<NotificationModel> notificationModels) {
        this.mContext=context;
        this.mNotificationModels=notificationModels;
    }

    @Override
    public NotifiactionItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView=LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_box,parent,false);
        return new NotifiactionItemHolder(mView);
    }

    @Override
    public void onBindViewHolder(NotifiactionItemHolder holder, int position) {
       holder.textView.setText(mNotificationModels.get(position).getNotification_data());
        holder.noti_logo.setImageDrawable(ContextCompat.getDrawable(mContext,noti_icons[position]));
    }

    @Override
    public int getItemCount() {
        return mNotificationModels.size();
    }


    public static  class NotifiactionItemHolder extends RecyclerView.ViewHolder{
        public ImageView noti_logo;
        public TextView textView;

        public NotifiactionItemHolder(View itemView) {
            super(itemView);
            noti_logo=(ImageView)itemView.findViewById(R.id.noti_logo);
            textView=(TextView)itemView.findViewById(R.id.noti_text);

        }
    }
}
