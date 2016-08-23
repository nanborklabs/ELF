package com.elf.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.elf.Adapter.SubjectGridAdapter;

import com.elf.Network.ElfRequestQueue;
import com.elf.R;
import com.elf.UserPrefs.MyPrefs;
import com.elf.model.SubjectHomeModel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 28/7/16.
 */
public class HomeFragment extends Fragment {

private static final String TAG="HOMEFRAGMENT";

    //the content root for this fragment
    private View mView;
    //the single reuqeust queue for this app
    ElfRequestQueue mRequestQueue;

    //prefes file
    private static final String PREFS="ELF_PARENT";

    //the webservice for DashBoard

    private static final String url="http://www.hijazboutique.com/elf_ws.svc/GetSubjects";

    private MyPrefs mPrefs;

    // info views

    @BindView(R.id.home_class) TextView mClass;
    @BindView(R.id.home_location) TextView mLocation;
    @BindView(R.id.home_user_name) TextView mStudentName;
    @BindView(R.id.home_school_name) TextView mSchoolName;

    @BindView(R.id.home_user_board) TextView mUserBoard;


    @BindView(R.id.home_progress_bar)
    ProgressBar mbar;


    private static  HomeFragment mFragment;

    //the cardview which holds all
    @BindView(R.id.cv_home_frag) CardView mDash;
    @BindView(R.id.home_grid)
    GridView home_grid;
    List<SubjectHomeModel> subjectList;

    SubjectGridAdapter mAdapter;

    //the student ID which the parent listens
    private String  mStudentId;




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


        mPrefs =new MyPrefs(getContext());
        //instatniate Reuquest queue
        mRequestQueue=ElfRequestQueue.getInstance(getContext());

        //get the subjects list , a student can be subscribed to any subjects
        subjectList=new ArrayList<>();




        mStudentId = mPrefs.getStudentId();

        //get dash board Deatils of Student , that is show overall status

        prepareDashBoardFor(mStudentId);



    }


    private void prepareDashBoardFor(String mStudentId) {

        getResponse(mStudentId);

    }

    private void getResponse(String mStudentId) {
        //preparing Request Body
        final  JSONObject object = new JSONObject();
        try {
//            // TODO: 23/8/16  studnet id
            object.put("studentId","1");
        }
        catch (Exception e){
            Log.d(TAG, "exception putting object"+e.getLocalizedMessage());

        }
        //preparing Request and Handling Response
        JsonArrayRequest request =new JsonArrayRequest(Request.Method.POST, url, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d(TAG, "REsponse is " + response.toString());

                try {
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject object = (JSONObject) response.getJSONObject(i);
                        String subjectname = object.getString("SubjectName");
                        String percentage = object.getString("Percentage");

                        Log.d(TAG, "response subject name " + subjectname);

                        Log.d(TAG, "response percentage " + percentage);
                        subjectList.add(new SubjectHomeModel(subjectname, percentage));

                    }

                    mAdapter = new SubjectGridAdapter(getContext(), subjectList);
                    mbar.setVisibility(View.INVISIBLE);
                    home_grid.setAdapter(mAdapter);


                } catch (Exception e) {
                    Log.d(TAG, "Exception in Response" + e.getLocalizedMessage());

                }
            }




        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "First Requset Error  "+error.getLocalizedMessage());

            }
        });

        mRequestQueue.addToElfREquestQue(request);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_frag, container, false);
        ButterKnife.bind(this, mView);
        mbar.setIndeterminate(true);
        mbar.setVisibility(View.VISIBLE);

        populateInfodetails();
        if (mAdapter==null){
            Log.d(TAG, "Adapter not prepared");
        }


        return mView;
    }

    private void populateInfodetails() {

        mSchoolName.setText(mPrefs.getSchoolName());
        mLocation.setText(mPrefs.getCityName());
        mStudentName.setText(mPrefs.getStudentName());
        mClass.setText(mPrefs.getStandard());
        mUserBoard.setText(mPrefs.getBoardName());

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


    public  Fragment newInstance() {
       if (mFragment == null){
           mFragment = new HomeFragment();
           return mFragment;
       }
        return mFragment ;
    }
}
