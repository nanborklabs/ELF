package com.elf.Fragment.ReportPagerFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.elf.PingServer;
import com.elf.R;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 29/7/16.
 */
public class LoginFragment extends Fragment {


    public  View mview;
    @BindView(R.id.uname_edittext_login) EditText username_login;
    @BindView(R.id.uname_tl_login) TextInputLayout uname;
    @BindView(R.id.sign_in)
    TextView sign_in;
    @BindView(R.id.fun_text)
    TextView funTextView;

    @BindView(R.id.password_edittext_login) EditText mPassword;
    @BindView(R.id.pasword_tl_login) TextInputLayout mPass;

    @BindView(R.id.submit_button_login)
    Button mLogin_button;

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
       mview=inflater.inflate(R.layout.login_frag,container,false);
        ButterKnife.bind(this,mview);
        uname.setTranslationY(-uname.getHeight());
        mPass.setTranslationY(-mPass.getHeight());
        StartAnimations();
        mLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=uname.getEditText().getText().toString();
                String password=mPass.getEditText().getText().toString();
                sendToserver(username,password);
            }
        });


        return mview;
    }

    private void sendToserver(String username, String password) {
        String url_string = "https://example.com/login?user=" + username + "&password=" + password;

//        creating URL
        URL url=null;
        try {
             url = new URL(url_string);
        } catch (MalformedURLException e) {

            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle("Webstite down").show();
        }


//        making request
        new PingServer().execute(url);
    }

    private void StartAnimations() {

//       Animatiosn for Imagview
        sign_in.setAlpha(0.f);
        funTextView.setAlpha(0.0f);
        sign_in.animate().alpha(1.f).scaleX(1.1f).scaleY(1.1f)
                .setStartDelay(100)
                .setInterpolator(new LinearInterpolator())
                .setDuration(400)
                .start();
        funTextView.animate().alpha(1.f).setStartDelay(300)
                .setDuration(200)
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
}
