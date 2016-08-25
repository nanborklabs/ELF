package com.elf.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.transition.Scene;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.Network.ElfRequestQueue;
import com.elf.R;
import com.elf.UserPrefs.MyPrefs;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 25/8/16.
 */
public class RelationshipFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String RELATION_URL = "http://www.hijazboutique.com/elf_ws.svc/ParentStudentRelation";

    private static final String TAG = "RELATIONSHIP";

    private static final String ADD_REQUEST = "";


    //fist page views
    @BindView(R.id.relation_find_student_button)
    Button mFindButton;
    @BindView(R.id.relation_parent_ted)
    TextInputLayout mStudentId;

    @BindView(R.id.relation_spinner)
    Spinner mSpinner;


    //second page views



    @BindView(R.id.result_std_name)
    TextView mStdname;
    @BindView(R.id.relation_result_class) TextView mClass;
    @BindView(R.id.relation_result_school_name) TextView mSchoolname;

    @BindView(R.id.realtion_result_add_button) Button mAddStudentButton;

    MyPrefs myPrefs;
    String mParentId;

    String mStdId;
    private View mView;
    ArrayAdapter<String> mSpinerAdapter;



    RelationshipPagehandler mCallback;


    Scene mStdResultScene;
    Scene mRootScene;
    Scene mStdSearchScene;
    ElfRequestQueue mRequestQueue;
    String[] relationship = {"Father", "Mother", "Sibling", "Guardian"};

    @Override


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPrefs = new MyPrefs(getContext());
        mParentId = myPrefs.getParentId();

        mRequestQueue = ElfRequestQueue.getInstance(getContext());

        mSpinerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_selectable_list_item, relationship);




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.relationship_frag, container, false);
        ButterKnife.bind(this, mView);



        //set up find Button , hide this m if sucess hid this view and show other view
        mFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mSpinner
                mStdId = mStudentId.getEditText().getText().toString();
                if (mStdId.length()<2 || mStdId.equals("") ){
                    Toast.makeText(getContext(),"Enter Correct Student Id",Toast.LENGTH_SHORT).show();

                }else {

                    findStudent(mStdId);
                }

            }

        });


        mAddStudentButton.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     // this is the student , add this student , show
                                                     sendRequestto(mStdId);

                                                 }
                                             });

                mSpinner.setAdapter(mSpinerAdapter);
        mSpinner.setOnItemClickListener(this);

        return mView;
    }

    private void sendRequestto(String mStdId) {
        final JSONObject mReq = new JSONObject();
        try {
            mReq.put("studentId",mStdId);
            mReq.put("parentId",mParentId);
            mReq.put("RelationshipId","1");

        }
        catch (Exception e ){



        }

        JsonArrayRequest mRequest = new JsonArrayRequest(Request.Method.POST, ADD_REQUEST, mReq, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(processAcceptResponse(response)){
                    //Accept request sent
                    //
                    myPrefs.setRequsetAccepted(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }





    private boolean processAcceptResponse(JSONArray mResponse){
        try {

            JSONObject object = mResponse.getJSONObject(0);
            String mStatus = object.getString("StatusCode");
            return mStatus.equals("1000");
        }

        catch (Exception e){
            Log.d(TAG , "Excepion");
        }
        return false;
    }

    private void findStudent(String mStdId) {

        //Requet objects to add page
        JSONObject mObject  = new JSONObject();

        try {

            mObject.put("studentId",mStdId);
            mObject.put("ParentId",mParentId);
            mObject.put("RelationshipId","1");
        }
        catch (Exception  e ){
            Log.d(TAG, "addStudentRequest: ");
        }

        //Make Request
        JsonArrayRequest  mReq = new JsonArrayRequest(Request.Method.POST, RELATION_URL, mObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (processResposne(response)){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.addToElfREquestQue(mReq);



    }

    private boolean processResposne(JSONArray response) {
        try {

            JSONObject object = response.getJSONObject(0);
            String Status = object.getString("StatusCode");
            return Status.equals("1000");
        }
        catch (Exception e ){
            Log.d(TAG, "processResposne: ");
        }
        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        mCallback = (RelationshipPagehandler)context;
        super.onAttach(context);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String mParent = parent.getItemAtPosition(position).toString();

    }

    public interface RelationshipPagehandler{
        void RelationshipAdded(boolean ok );
    }

}