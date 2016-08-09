package com.elf;

import android.animation.Animator;
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

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginAcitivity extends AppCompatActivity {


    public View mview;
    @BindView(R.id.uname_edittext_login)
    EditText username_login;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_frag);
        ButterKnife.bind(this);
        uname.setTranslationY(-uname.getHeight());
        mPass.setTranslationY(-mPass.getHeight());
        StartAnimations();


        mLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL url=null;
                String username=uname.getEditText().getText().toString();
                String password=mPass.getEditText().getText().toString();
                String url_string="http://www.hijazboutique.com/elf_ws.svc/CheckParentLogin";
                try{
                   url= new URL(url_string);
                }
                catch (Exception e){
                    Log.d("URL", "exception");
                }
                new PingServer(username,password).execute(url);

            }
        });

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
