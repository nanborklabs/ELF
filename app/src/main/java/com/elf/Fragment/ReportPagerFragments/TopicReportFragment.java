package com.elf.Fragment.ReportPagerFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elf.Adapter.TopicReportAdapter;
import com.elf.R;
import com.elf.model.TopicDetail;
import com.elf.model.TopicInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 29/7/16.
 */
public class TopicReportFragment extends Fragment {

    public View mView;
    @BindView(R.id.topic_list)
    RecyclerView mList;
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
       mView=inflater.inflate(R.layout.topicnotification,container,false);
        ButterKnife.bind(this,mView);
        mList.setLayoutManager(new LinearLayoutManager(getContext()));


//        get list of topics from uRL
//        make it inti Appropriate object
//        and set to to adapter
//        todo: notify adapter set changed


        mList.setAdapter(new TopicReportAdapter(getContext(),generateTopicData()));
        return mView;
    }

    private List<TopicInfo> generateTopicData() {
        List<TopicInfo> infoList=new ArrayList<>();
        List<TopicDetail> detail_list=new ArrayList<>();
        detail_list.add(new TopicDetail("Eddy current",28));
        infoList.add(new TopicInfo("Topic 1",""+72,detail_list));
        infoList.add(new TopicInfo("Topic 2",""+72,detail_list));
        infoList.add(new TopicInfo("Topic 3",""+72,detail_list));
        infoList.add(new TopicInfo("Topic 4",""+72,detail_list));
        return infoList;

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
}
