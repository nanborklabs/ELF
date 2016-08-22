package com.elf.Fragment.ReportPagerFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.elf.Adapter.TestReportAdapter;
import com.elf.EventBus.ReportRadioButtonEvent;
import com.elf.Fragment.ReportsFragment;
import com.elf.Network.ElfRequestQueue;
import com.elf.R;
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
 * this ca
 *
 *
 */
public class TestReportFragment extends Fragment {
    public View mview;

    private static String subjectId="4";
    private static String StudentId= "12";
    private final static  String TAG="TEST FRAGMENT";
    @BindView(R.id.test_noti_recycler_view) RecyclerView mList;

    @BindView(R.id.test_page_progress)
    ProgressBar mbar;


    private final static String TEST_URL="http://www.hijazboutique.com/elf_ws.svc/GetTestReport";

    ElfRequestQueue mRequestQueue;


    List<Testinfo> mTestObjectsList;





    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /*this method is called when radio button
    is clicked in parent framgent with  String id (inside Event) */

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReportRadioButtonEvent event) {
        Log.d(TAG, "onMessageEvent: ");
        JSONObject object=null;
        JsonArrayRequest mRequest=null;

        //        get the name of subject clicked from event
        String Subject=event.getmSubjectClicked();
        switch (Subject){
        //            populate Subject Id here
            case "P":

                subjectId="1";

                break;
            case "M":
                subjectId="1";
                break;
            case "C":
                subjectId="1";
                break;
            case "CS":
                subjectId="1";
                break;
            case "B":
                subjectId="1";
                break;
           default:
               subjectId="1";
               break;
        }
        //          mkae request and get back response
        //        nake objects from  response & show it in { @link mList }
        try {
           object =new JSONObject();
            object.put("StudentId","1");
            object.put("SubjectId","11");

        }
        catch (Exception e ){
            Log.d(TAG, "Exception in Json");
        }

        //        Request asking for Test reports( common fro all objects)
        //        returns Testno,status percent, topic

            mRequest=new JsonArrayRequest(Request.Method.POST, TEST_URL, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "got Response");
                Log.d(TAG, "onResponse: "+response.toString());
                     //                  an Array of test reports  i.e test wise for each subject
                List<Testinfo> mTestObjectsList=new ArrayList<>();
                JSONObject mObject;
                for (int i=0;i<response.length();i++){
                    Log.d(TAG, "for loop");
                    //                    getting individual objects by index
                    try {
                        mObject=(JSONObject) response.getJSONObject(i);
                        Log.d(TAG, "onResponse: "+mObject.toString());
                        mTestObjectsList.add(new Testinfo(mObject.getString("TestId"),
                                mObject.getString("Percentage")
                                ,mObject.getString("SubjectName")
                                ,mObject.getString("TestStatus")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

//                set the object to Adapter
                mList.setAdapter(new TestReportAdapter(getContext(),mTestObjectsList));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Erorr Resposne in Requestt");
            }
        });

        mRequestQueue.addToElfREquestQue(mRequest);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        //ge the Register Queue
        mRequestQueue =ElfRequestQueue.getInstance(getContext());

        //send Request with Student ID and Subject ID
        
        prepareRequest();


        
        
        
            //todo:what happens if fragment is created for first time?
       
    }

    private void prepareRequest() {
        
        //Request body
        final JSONObject mObject = new JSONObject();
        try {
            mObject.put("StudentId","1");
            mObject.put("SubjectId","11");

        }
        catch (Exception e) {
            Log.d(TAG, "");
        }
        
        //Request Building
        
        final JsonArrayRequest mSubjectReq = new JsonArrayRequest(Request.Method.POST, TEST_URL, mObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject mRsp = null;
                try {
                mRsp  = response.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "onResponse: "+mRsp.toString());
                mbar.setVisibility(View.INVISIBLE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: ");
            }
        });

        mRequestQueue.addToElfREquestQue(mSubjectReq);
        
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview=inflater.inflate(R.layout.test_notifcaton,container,false);
        ButterKnife.bind(this,mview);

        mbar.setVisibility(View.VISIBLE);
        mbar.setIndeterminate(true);
        mList.setLayoutManager(new LinearLayoutManager(getContext()));

        return mview;
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
