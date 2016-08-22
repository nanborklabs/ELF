package com.elf;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.Fragment.ForgotPasswordFragment;
import com.elf.Fragment.RegisterFragment;
import com.elf.Network.CustomRetryPolicy;
import com.elf.Network.ElfRequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginAcitivity extends AppCompatActivity {



    private static final String TAG="Login";
    private static final String PREFS="ELF_PARENT";

    private final static String LOGIN_URL="http://www.hijazboutique.com/elf_ws.svc/CheckParentLogin";

    private static final String NET_TAG="Login_page";

    private static final String FORGOT_URL ="www.googel.com";
    public View mview;

    //user name


    //dialog box
    private ProgressDialog progress;




    private ElfRequestQueue queue;



//    user name box
    @BindView(R.id.login_user_name) TextInputLayout mUserNameBox;

    // password Box
    @BindView(R.id.login_password_box) TextInputLayout mPAssBox;

    //login Button
    @BindView(R.id.login_login_button)
    Button mLoginButton;

//    register button

    @BindView(R.id.login_register_button) Button mRegsiterButton;

    // FOrgot Password Button

    @BindView(R.id.login_forgot_password_button) Button mForgotButton;




    ElfRequestQueue mRequestQueue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_frag);
        ButterKnife.bind(this);
        mRequestQueue  = ElfRequestQueue.getInstance(this);


        progress=new ProgressDialog(this);

        queue=ElfRequestQueue.getInstance(this);

        mUserNameBox.setTranslationY(-mUserNameBox.getHeight());
        mPAssBox.setTranslationY(-mPAssBox.getHeight());
        StartAnimations();



        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress.show();
                URL url=null;
               final  String mUserName=mUserNameBox.getEditText().getText().toString();
               final  String mPassword=mPAssBox.getEditText().getText().toString();


                sendServer(mUserName,mPassword);
            progress.setTitle("Logging in");
                progress.setMessage("Loggin in ..!!");
                progress.setIndeterminate(true);

            }
        });

        //set click listenere for Register Fragment
        mRegsiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RegisterFragment mFragment=new RegisterFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_holder,mFragment)
                        .commit();
            }
        });


        //set click action for Forgot Password
        mForgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getSupportFragmentManager().beginTransaction()
                      .replace(R.id.frag_holder,new ForgotPasswordFragment())
                      .commit();

            }
        });





    }

    private void sendServer(String username, final String password) {

        final JSONObject object=new JSONObject();
        try {
            object.put("username",username);
            object.put("password",password);
            Log.d(TAG, "sendServer: ");
        }
        catch (Exception e){
            Log.d(TAG, "sendServer: "+e.getLocalizedMessage());
        }

        JsonArrayRequest request=new JsonArrayRequest(Request.Method.POST, LOGIN_URL, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {


                    JSONObject object1 = (JSONObject) response.get(0);
                    String LoginStatus=object1.getString("LoginStatus");
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

                    Log.d(TAG, "onResponse: Standard "+Standard);
                    Log.d(TAG, "onResponse: Schoolname "+schoolName);
                    Log.d(TAG, "onResponse: Boardid  "+BoardId);
                    Log.d(TAG, "onResponse: BoardName "+BoardName);
                    Log.d(TAG, "onResponse: Status "+LoginStatus);
                    Log.d(TAG, "onResponse: StudentId "+StudentId);

                    if (LoginStatus.equals("success") ){


//                        save details to shared Prefs
                      final   SharedPreferences preferences= getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
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

                        final  Intent mIntent = new Intent(LoginAcitivity.this,ElfMainActivity.class);
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(mIntent);


                    }
                    else {
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



                Log.d(TAG, "Response is Error "+error.getLocalizedMessage());
            }
        });
        request.setTag(NET_TAG);

        queue.addToElfREquestQue(request);
    }

    private void ShowNetorkError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginAcitivity.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginAcitivity.this);
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



    private void StartAnimations() {

//       Animatiosn for Imagview


//        animations for Edite text componenets

       /* username_login.animate().translationY(0)
                .setDuration(1200)
                .setStartDelay(800)
                .setInterpolator(new AccelerateInterpolator())
                .start();
        mPassword.animate().translationY(0)
                .setDuration(1200)
                .setStartDelay(800)

                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
                */


    }



    @Override
    protected void onStop() {
        super.onStop();
        queue.cancelElfReques(NET_TAG);
    }
}
