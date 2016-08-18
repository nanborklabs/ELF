package com.elf.Fragment;

import android.animation.Animator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.Network.CustomRetryPolicy;
import com.elf.Network.ElfRequestQueue;
import com.elf.R;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by nandhu on 9/8/16.
 */
public class RegisterFragment  extends Fragment{

    private View mView;
    //Log Tag
    private static final String TAG="REGISTER";

    //URL
    private static final String URL="http://www.hijazboutique.com/elf_ws.svc/ParentRegistration";

    //view binding
    @BindView(R.id.re_pass_register)
    TextInputEditText mRePasswordBox;
    @BindView(R.id.pas_register) TextInputEditText mPasswordBox;
    @BindView(R.id.name_text) TextInputEditText  mNametextbox;
    //    state textview
    @BindView(R.id.register_state)
    AutoCompleteTextView mStateBox;
    //    school
    @BindView(R.id.register_school)
    AutoCompleteTextView mSchoolBox;
    //    city
    @BindView(R.id.register_city)
    AutoCompleteTextView mCityBox;
    //    final button
    @BindView(R.id.submit_register)
    Button mSubmitButton;
    //    first button
    @BindView(R.id.next_button)
    Button mNextButton;
    //    board
    @BindView(R.id.board_register)
    AutoCompleteTextView  mBoardBox;

    @BindView(R.id.register_email_text) TextInputEditText mEmailbox;
    @BindView(R.id.register_phone_number ) TextInputEditText mPhoneBox;

    //    Array adapter for State
    ArrayAdapter<String> mStateAdapter;

    //    Array adapter for city
    ArrayAdapter<String> mCityAdapter;

    //    Array adapter for board
    ArrayAdapter<String> mBoardAdapter;
    String State[]={"Tamil Nadu","Karnataka","Kerala","Andhra Pradesh","Delhi"};
    String city[] = {"Delhi","Chennai","Mumbail","Coimbatore","Trichy","Salem"};
    String Board[] = { "CBSE","Samacheer","ICSE"};

    ArrayAdapter<String> mBoardSpinnerAdapter=null;


    //The request Queue for this page
    ElfRequestQueue mRequestQueue;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = ElfRequestQueue.getInstance(getContext());
        mStateAdapter =new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,State);
        mCityAdapter =new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,city);
        mBoardAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,Board);

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
        mView = inflater.inflate(R.layout.register,container,false);
        ButterKnife.bind(this,mView);



            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String mPass =  mRePasswordBox.getText().toString();
                    final String mReneter_pass=mRePasswordBox.getText().toString();
                    //checking if password is same
                    if (mReneter_pass.equals(mPass)){
                        //same Password crate animation whatever etc


                        final View mV=mView.findViewById(R.id.ll_secondpage);

                        createCiruclarReveal(mV);
                    }
                    else {
                        Toast.makeText(getContext(),"Please Enter same Password",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            mSubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //get All Textfields value
                    final String  mName = mNametextbox.getText().toString();
                    final String mPass =  mRePasswordBox.getText().toString();
                    final String mState =  mStateBox.getText().toString();
                    final String schoolname=mSchoolBox.getText().toString();
                    final String city=mCityBox.getText().toString();
                    final String board=mBoardBox.getText().toString();
                    final String mPhone = mPhoneBox.getText().toString();
                    final String email = mEmailbox.getText().toString();


                    //register the user

                    register(mName,mPass,mState,board,city,schoolname,mPhone,email);
                }
            });
            return mView;
        }

    private void createCiruclarReveal(View myView) {

        if (myView==null){
            Log.d(TAG, "my view is null");
        }

        // get the center for the clipping circle
        int cx = myView.getMeasuredWidth() / 2;
        int cy = myView.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight()) / 2;

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

        // make the view visible and start the animation

        if (myView.getId() == R.id.ll_secondpage){
            //second view is revealed , hide first view
            mView.findViewById(R.id.rl_first_page).setVisibility(View.INVISIBLE);
            if (!myView.isShown()){
                myView.setVisibility(View.VISIBLE);
            }

        }
        else{

            //first LAyout is reveled, hide second layout
            myView.findViewById(R.id.ll_secondpage).setVisibility(View.INVISIBLE);
            if (!myView.isShown()){
                myView.setVisibility(View.VISIBLE);
            }


        }

        anim.setDuration(600);
        anim.setInterpolator(new AccelerateInterpolator(0.5f));
        anim.start();





    }

    private void register(String mName, String mPass, String state, String board, String city, String schoolname,String phone,String email) {

        //Json object to be sent in Request body
        final JSONObject mObject=new JSONObject();


        try{

            //todo:add appropriate id use getID,getBoardID,getStateID
            mObject.put("FirstName",mName);
            mObject.put("LastName","Null");
            mObject.put("EmailAddress",email);
            mObject.put("Password",mPass);
            mObject.put("InstitutionId","1");
            mObject.put("BoardId","1");
            mObject.put("ClassId","1");

            mObject.put("CityId","1");
            mObject.put("DistrictId","1");
            mObject.put("StateId","1");
            mObject.put("PhoneNumber",phone);
//          todo:spinner
//            mObject.put("")
        }
        catch (Exception e) {
            Log.d(TAG, "register: JSOn object Exception");
        }

        //prepare Request object
        final JsonArrayRequest mReq = new JsonArrayRequest(Request.Method.POST,
                URL,
                mObject,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        processResponse(response);

                    }
                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        CustomRetryPolicy cm = new CustomRetryPolicy();
        mReq.setRetryPolicy(cm);
        mRequestQueue.addToElfREquestQue(mReq);
    }

    private void processResponse(JSONArray response) {
        try{


            if ( isRegistrationSuccess (response) ) {


                Log.d(TAG, "onResponse: succes Registraion");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_holder,new HomeFragment())
                        .commit();
                //user is Registed ,get studentid and Store it for Permanent user
//                        String mStudId = response.getJSONObject(0).getString("StudentId");
//                        saveStudentId(mStudId);

            }
            else {
                //not Registerd , show login Page
                Toast.makeText(getContext(),"EMail Already Exits",Toast.LENGTH_SHORT).show();
                    mView.findViewById(R.id.rl_first_page).setVisibility(View.VISIBLE);
                    mView.findViewById(R.id.ll_secondpage).setVisibility(View.INVISIBLE);
                clearAllFields();




            }
        }
        catch (Exception e ){
            Log.d(TAG, "Exception in On Response "+e.getLocalizedMessage());
        }
    }

    private void clearAllFields() {
       mNametextbox.setText("");
        mBoardBox.setText("");
        mCityBox.setText("");
        mEmailbox.setText("");
        mPhoneBox.setText("");
        mStateBox.setText("");
        mPasswordBox.setText("");
        mRePasswordBox.setText("");




    }

    private void saveStudentId(String mStudId) {
        final SharedPreferences mPrefrences = getContext()
                .getSharedPreferences("ELF", Context.MODE_PRIVATE);

        final SharedPreferences.Editor mEditor = mPrefrences.edit();

        synchronized (mEditor){
            mEditor.putString("STUDENT_ID",mStudId);
            mEditor.apply();

        }

    }

    private boolean isRegistrationSuccess(JSONArray response) {
        try {

            final JSONObject mResponse = response.getJSONObject(0);
            final String mStatuscode = mResponse.getString("StatusCode");
            if (mStatuscode.equals("9999")){
                return false;
            }
            else {
                return true;
            }
        }
        catch (Exception e){
            Log.d(TAG, "isRegistered: Error in getting object");
            return false;
        }

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
    }}
