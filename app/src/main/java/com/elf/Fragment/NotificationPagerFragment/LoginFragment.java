package com.elf.Fragment.NotificationPagerFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.elf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 29/7/16.
 */
public class LoginFragment extends Fragment {


    public  View mview;
    @BindView(R.id.uname_edittext_login) EditText username_login;
    @BindView(R.id.uname_tl_login) TextInputLayout uname;
    @BindView(R.id.password_edittext_login) EditText mPassword;
    @BindView(R.id.pasword_tl_login) TextInputLayout mPass;
    @BindView(R.id.elf_logo_login) ImageView elf_logo;
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
        StartAnimations();
        return mview;
    }

    private void StartAnimations() {

//       Animatiosn for Imagview
        elf_logo.setAlpha(0.f);
        elf_logo.animate().alpha(1.f).scaleX(1.1f).scaleY(1.1f)
                .setStartDelay(100)
                .setInterpolator(new LinearInterpolator())
                .setDuration(400)
                .start();

//        animations for Edite text componenets
        uname.setTranslationY(-uname.getHeight());
        mPass.setTranslationY(-mPass.getHeight());
        uname.animate().translationY(0)
                .setDuration(600)
                .setStartDelay(300)
                .setInterpolator(new OvershootInterpolator(3.f))
                .start();
        mPass.animate().translationY(0)
                .setDuration(600)
                .setStartDelay(300)
                .setInterpolator(new OvershootInterpolator(3.f))
                .start();

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
