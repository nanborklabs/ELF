package com.elf.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import com.elf.model.SubjectHomeModel;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 28/7/16.
 */
public class HomeFragment extends Fragment {

private static final String TAG="HOMEFRAGMENT";
    private View mView;
    ElfRequestQueue mRequestQueue;
    private static final String PREFS="LOGIN";
    @BindView(R.id.school_name) TextView mSchoolName;
    @BindView(R.id.location) TextView mLocation;
    @BindView(R.id.std) TextView mStandard;
    @BindView(R.id.user_board) TextView muserBoad;
    @BindView(R.id.username_home_frag) TextView mUsername;
    @BindView(R.id.home_progress_bar)
    ProgressBar mbar;
    @BindView(R.id.cv_home_frag) CardView mDash;
    @BindView(R.id.home_grid)
    GridView home_grid;
    List<SubjectHomeModel> subjectList;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    SubjectGridAdapter mAdapter;




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
        mRequestQueue=ElfRequestQueue.getInstance(getContext());

        subjectList=new ArrayList<>();
        prefs = getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        editor=prefs.edit();
        String studentID=prefs.getString("studentid","no name");
        Log.d(TAG, "student id:"+studentID);

        JSONObject object=new JSONObject();
        try {
            object.put("studentId",studentID);
        }
        catch (Exception e){
            Log.d(TAG, "exception putting object"+e.getLocalizedMessage());

        }
        String url="http://www.hijazboutique.com/elf_ws.svc/GetSubjects";


        JsonArrayRequest request =new JsonArrayRequest(Request.Method.POST, url, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = (JSONObject) response.getJSONObject(i);
                        String subjectname = object.getString("SubjectName");
                        String percentage = object.getString("Percentage");

                        Log.d(TAG, "response subject name " + subjectname);

                        Log.d(TAG, "response percentage " + percentage);
                        subjectList.add(new SubjectHomeModel(subjectname,percentage));

                    }

                    mAdapter=new SubjectGridAdapter(getContext(),subjectList);
                    mbar.setVisibility(View.INVISIBLE);
                    home_grid.setAdapter(mAdapter);


                }
                    catch(Exception e){
                        Log.d(TAG, "Exception in Response" + e.getLocalizedMessage());
                    }


                }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Errrod in Response "+error.getLocalizedMessage());

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




        mLocation.setText(prefs.getString("district","no name"));
        mUsername.setText(prefs.getString("firstname","no school"));
        mSchoolName.setText(prefs.getString("schoolname","no name"));
        mStandard.setText(prefs.getString("standard","no name"));
        muserBoad.setText(prefs.getString("boardname","no board"));
        if (mAdapter==null){
            Log.d(TAG, "Adapter not prepared");
        }


        return mView;
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

    public class AdaperReady{
        public boolean ready;

        public boolean isReady() {
            return ready;
        }

        public void setReady(boolean ready) {
            this.ready = ready;
        }
    }
}
