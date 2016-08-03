package com.elf.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elf.Adapter.NotificationAdapter;
import com.elf.R;
import com.elf.model.Lessoninfo;
import com.elf.model.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 28/7/16.
 */
public class NotificationFragment extends android.support.v4.app.Fragment {

    private View mView;
    @BindView(R.id.noti_list_view)
    RecyclerView mRecyclerView;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       mView=inflater.inflate(R.layout.noti,container,false);
        ButterKnife.bind(this,mView);
//get list of notifications from server
//        make it an object and pass it the adapter
//        and dont forget to call notify data item inserted inserted
//        Todo:notify data item inserted
//        generateNotification()
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new NotificationAdapter(getContext(),generateNotification()));
        return mView;
    }

    private List<NotificationModel> generateNotification() {
        List<NotificationModel> model_list=new ArrayList<>();
        model_list.add(new NotificationModel(1,"Test completed"));
        model_list.add(new NotificationModel(2,"Started Writing Test"));
        model_list.add(new NotificationModel(1,"Reports have been Generated"));
        return model_list;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
