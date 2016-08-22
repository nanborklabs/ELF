package com.elf.Fragment.ReportPagerFragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.Adapter.LessonReportAdapter;
import com.elf.EventBus.ReportRadioButtonEvent;
import com.elf.Network.ElfRequestQueue;
import com.elf.R;
import com.elf.RecyclerviewItemDecorator;
import com.elf.model.Lessoninfo;
import com.elf.model.Testinfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 29/7/16.
 */
public class LessonReportFragment extends Fragment{

    //    the view container
    public View mView;
    JSONObject object=null;
    JsonArrayRequest mRequest=null;
    List<Lessoninfo> mLessonlist;

    private static final String TAG="REPORT_LESSON_REPORT";

    //    the recyceler view tha display
    @BindView(R.id.lesson_rv_list)
    RecyclerView lesson_list;

    @BindView(R.id.lesson_progress)
    ProgressBar mbar;

    private String subjectId="11";
    LessonReportAdapter mAdapter;


    //    String to get test reports from
    private static final String LESSON_REPORT_URL="http://www.hijazboutique.com/elf_ws.svc/GetLessionWiseReport";
    RecyclerView.ItemDecoration mDecoration;

    //    the request queue
    private ElfRequestQueue mRequestQueue;
    private boolean mAdpaterRequestMade =false;

    //    todo:fire up  the request everytime user navagiates this page
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReportRadioButtonEvent event) {
        Log.d(TAG, "onMessageEvent: ");


//        get the name of subject clicked from event
        String Subject=event.getmSubjectClicked();
        switch (Subject){
//            populate Subject Id here
//
            case "P":

                subjectId="11";

                break;
            case "M":
                subjectId="11";
                break;
            case "C":
                subjectId="11";
                break;
            case "CS":
                subjectId="11";
                break;
            case "B":
                subjectId="11";
                break;
            default:
                subjectId="11";
                break;
        }
//          mkae request and get back response
//        nake objects from  response & show it in { @link mList }
      makeRequest_AddToAdapter();
    }

    private void makeRequest_AddToAdapter() {
        Log.d(TAG, "makeRequest_AddToAdapter: Lesson Page");
        try {
            object =new JSONObject();
            object.put("studentId","1");
            object.put("subjectId","11");

        }
        catch (Exception e ){
            Log.d(TAG, "Exception in Json");
        }

//        Request asking for Test reports( common fro all objects)
//        returns Testno,status percent, topic

        mRequest=new JsonArrayRequest(Request.Method.POST, LESSON_REPORT_URL, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "got Response");
                Log.d(TAG, "onResponse: Lesson page"+response.toString());
//                  an Array of test reports  i.e test wise for each subject
              mLessonlist=new ArrayList<>();
                JSONObject mObject;
                for (int i=0;i<response.length();i++){

//                    getting individual objects by index
                    try {
                        mObject=(JSONObject) response.getJSONObject(i);

                        mLessonlist.add(new Lessoninfo(mObject.getString("LessionName"),
                                mObject.getString("Percentage"),
                                mObject.getString("QustionAsked"),
                                mObject.getString("CorrectAnswer")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                mAdapter=new LessonReportAdapter(getContext(),mLessonlist);
                mbar.setVisibility(View.INVISIBLE);
                lesson_list.setAdapter(mAdapter);
                
//                set the object to Adapter

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Erorr Resposne in Requestt");
            }
        });
        mRequestQueue.addToElfREquestQue(mRequest);

            }

    @Subscribe
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        EventBus.getDefault().register(this);

        mRequestQueue=ElfRequestQueue.getInstance(getContext());







    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.lesson_notification,container,false);
        ButterKnife.bind(this,mView);

        mbar.setVisibility(View.VISIBLE);
        mbar.setIndeterminate(true);


        lesson_list.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mAdapter==null ) {

                makeRequest_AddToAdapter();
            }


        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        
        if (mAdapter!=null){
            lesson_list.setAdapter(mAdapter);
        }
        else{
            Log.d(TAG, "onResume: Adapter null");
        }


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
