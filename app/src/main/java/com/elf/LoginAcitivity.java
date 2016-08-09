package com.elf;

import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginAcitivity extends AppCompatActivity {



    private static final String TAG="Login";
    public View mview;
    @BindView(R.id.uname_edittext_login)
    EditText username_login;
    private ProgressDialog progress;

    @BindView(R.id.uname_tl_login)
    TextInputLayout uname;
    @BindView(R.id.password_edittext_login) EditText mPassword;
    @BindView(R.id.pasword_tl_login) TextInputLayout mPass;
    @BindView(R.id.sign_in)
    TextView sign_in;
    @BindView(R.id.fun_text)
    TextView funTextView;
    @BindView(R.id.submit_button_login)
    Button mLogin_button;
    RequestQueue Queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_frag);
        ButterKnife.bind(this);
        Queue= Volley.newRequestQueue(this);
        progress=new ProgressDialog(this);

        uname.setTranslationY(-uname.getHeight());
        mPass.setTranslationY(-mPass.getHeight());
        StartAnimations();


        mLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress.show();
                URL url=null;
                String username=uname.getEditText().getText().toString();
                String password=mPass.getEditText().getText().toString();


                sendServer(username,password);
            progress.setTitle("Logging in");
                progress.setMessage("Loggin in ..!!");
                progress.setIndeterminate(true);

            }
        });

    }

    private void sendServer(String username, String password) {
        String url_string="http://www.hijazboutique.com/elf_ws.svc/CheckParentLogin";
        JSONObject object=new JSONObject();
        try {
            object.put("username",username);
            object.put("password",password);
        }
        catch (Exception e){
            Log.d(TAG, "sendServer: "+e.getLocalizedMessage());
        }

        JsonArrayRequest request=new JsonArrayRequest(Request.Method.POST, url_string, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    progress.hide();
                    JSONObject object1 = (JSONObject) response.get(0);
                    String LoginStatus=object1.getString("LoginStatus");
                    String Cityname=object1.getString("CityName");
                    String District=object1.getString("DistrictName");
                    String Firstname=object1.getString("FirstName");
                    String Lastname=object1.getString("LastName");
                    String Email=object1.getString("EmailAddress");
                    String PArent=object1.getString("ParentId");
                    String phone=object1.getString("PhoneNumber");
                    Log.d(TAG, "onResponse: Status "+LoginStatus);
                    Log.d(TAG, "onResponse: District "+District);
                    Log.d(TAG, "onResponse: Firstname "+Firstname);
                    Log.d(TAG, "onResponse: Lastname "+Lastname);
                    Log.d(TAG, "onResponse: Email "+Email);
                    Log.d(TAG, "onResponse: PArent "+PArent);
                    Log.d(TAG, "onResponse: Phone "+phone);
                    Log.d(TAG, "onResponse: cityname "+Cityname);


                } catch (Exception e) {

                    Log.d(TAG, "Exception in JSon object getting " +e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Log.d(TAG, "Response is Error"+error.getMessage());
            }
        });

        Queue.add(request);
    }

    private void startAct() {
        Intent i=new Intent(this,ElfMainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void StartAnimations() {

//       Animatiosn for Imagview
        sign_in.setAlpha(0.f);
        funTextView.setAlpha(0.0f);
        sign_in.animate().alpha(1.f).scaleX(1.2f).scaleY(1.3f)
                .setStartDelay(100)
                .setInterpolator(new LinearInterpolator())
                .setDuration(400).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                funTextView.animate().alpha(1.f).setDuration(300)
                        .setDuration(200)
                        .start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        })
                .start();


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
}
