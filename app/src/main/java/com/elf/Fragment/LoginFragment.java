package com.elf.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.ElfMainActivity;
import com.elf.FIrstActivity;
import com.elf.Network.ElfRequestQueue;
import com.elf.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 23/8/16.
 */
public class LoginFragment extends Fragment {


    private View mView;

    private final static String LOGIN_URL="http://www.hijazboutique.com/elf_ws.svc/CheckParentLogin";


    private static final String PREFS="ELF_PARENT";

    private static final String TAG = "LOGIN";
   //username
    @BindView(R.id.login_user_name)
    TextInputLayout mUserNameBox;

    // password Box
    @BindView(R.id.login_password_box) TextInputLayout mPAssBox;

    //login Button
    @BindView(R.id.login_login_button)
    Button mLoginButton;

//    register button

    @BindView(R.id.login_register_button) Button mRegsiterButton;

    // FOrgot Password Button

    @BindView(R.id.login_forgot_password_button) Button mForgotButton;


    private ProgressDialog progress;



    //interface callback to notify activity  { @Buttonclicked }


    //instantiate in onAttach
    Buttonclicked mCallback;


    private ElfRequestQueue mRequestQueue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {

        mCallback = (Buttonclicked)context;
        super.onAttach(context);
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
      mView = inflater.inflate(R.layout.login_frag,container,false);
        ButterKnife.bind(this,mView);

        mRequestQueue  = ElfRequestQueue.getInstance(getContext());


        progress=new ProgressDialog(getContext());


        mUserNameBox.setTranslationY(-mUserNameBox.getHeight());
        mPAssBox.setTranslationY(-mPAssBox.getHeight());
//        StartAnimations();



        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress.show();
                URL url=null;
                final  String mUserName=mUserNameBox.getEditText().getText().toString();
                final  String mPassword=mPAssBox.getEditText().getText().toString();


//                sendServer(mUserName,mPassword);

//                startActivity(new Intent(this,ElfMainActivity.class));
                progress.setTitle("Logging in");
                progress.setMessage("Loggin in ..!!");
                progress.setIndeterminate(true);

            }
        });

        //set click listenere for Register Fragment
        mRegsiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (mCallback != null){
                   mCallback.ChangeFragment(0);
               }

            }
        });


        //set click action for Forgot Password
        mForgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.ChangeFragment(1);
                }

            }
        });

        return mView;


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }









    private void sendServer(String username, final String password) {

        final JSONObject object=new JSONObject();
        try {
            object.put("username",username);
            object.put("password",password);
        }
        catch (Exception e){
            Log.d(TAG, "sendServer: "+e.getLocalizedMessage());
        }

        JsonArrayRequest request=new JsonArrayRequest(Request.Method.POST, LOGIN_URL, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {


                    Log.d(TAG, "Login Response "+ response.toString());

                    JSONObject object1 = (JSONObject) response.get(0);
                    String LoginStatus=object1.getString("StatusCode");



                    if (LoginStatus.equals("1000") ){


//                        save details to shared Prefs
                        String Cityname=object1.getString("CityName");
                        String District=object1.getString("DistrictName");
                        String Firstname=object1.getString("FirstName");
                        String Lastname=object1.getString("LastName");
                        String Email=object1.getString("EmailAddress");
                        String PArent=object1.getString("ParentId");
                        String phone=object1.getString("PhoneNumber");
                        String BoardName=object1.getString("BoardName");
                        String BoardId=object1.getString("BoardId");
                        String StudentId=object1.getString("StudentId");
                        String schoolName=object1.getString("InstitutionName");
                        String Standard=object1.getString("ClassName");

                        final SharedPreferences preferences= getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("firstname",Firstname);
                        editor.putString("lastname",Lastname);
                        editor.putString("email",Email);
                        editor.putString("parentid",PArent);
                        editor.putString("phone",phone);
                        editor.putString("district",District);
                        editor.putString("cityname",Cityname);
                        editor.putString("boardid",BoardId);
                        editor.putString("boardname",BoardName);
                        editor.putString("studentid",StudentId);
                        editor.putString("standard",Standard);
                        editor.putString("schoolname",schoolName);
                        editor.putBoolean("isFirstTime",false);
                        editor.apply();
                        progress.hide();

                        final Intent mIntent = new Intent(getContext(),ElfMainActivity.class);
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(mIntent);


                    }
                    else {
                        progress.hide();
                        showError();
                    }


                } catch (Exception e) {

                    Log.d(TAG, "Exception in JSon object getting " +e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.hide();
                ShowNetorkError();


                Log.d(TAG, "Response is Error"+error.getLocalizedMessage());
            }
        });


        mRequestQueue.addToElfREquestQue(request);
    }

    private void ShowNetorkError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Could Not connect");
        builder.setMessage("Please make sure you are connected to Internet");

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic

                        startActivity(new Intent(Settings.ACTION_SETTINGS));

                    }
                });


        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();


    }



/*
*
* method which dispalaye Error dialog*/

    private void showError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Wrong Details");
        builder.setMessage("Please Enter Correct details");

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        mUserNameBox.getEditText().setText("");
                        mPAssBox.getEditText().setText("");
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();


    }


    /*
    *
    * a interface which tells the parent acivity to change fragments
    *
    * pass along an id
    *  0 - Register
    *  1 - forgot password
    *
    * */
    public interface Buttonclicked{
        public void ChangeFragment(int id);
    }




    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
