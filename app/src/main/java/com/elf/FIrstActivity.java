package com.elf;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.Fragment.ForgotPasswordFragment;
import com.elf.Fragment.LoginFragment;
import com.elf.Fragment.RegisterFragment;
import com.elf.Fragment.RelationshipFragment;
import com.elf.Network.ElfRequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import butterknife.ButterKnife;

public class FIrstActivity extends AppCompatActivity implements LoginFragment.Buttonclicked , RegisterFragment.RegistrationSuccess
, ForgotPasswordFragment.ChangePassword ,RelationshipFragment.RelationshipPagehandler{


    private static final String TAG = "Login";
    private static final String PREFS = "ELF_PARENT";


    private static final String NET_TAG = "Login_page";

    private static final String FORGOT_URL = "www.googel.com";


//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);




        getSupportFragmentManager().beginTransaction().replace(R.id.first_frag_holder,new LoginFragment())
                .commit();


    }


    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    public void ChangeFragment(int id) {
        Fragment mFragment = null;
        if (id == 0) {
            mFragment = new RegisterFragment().newInstance();
        } else if (id == 1) {
            mFragment = new ForgotPasswordFragment().newInstance();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.first_frag_holder, mFragment)
                .commit();
    }

    @Override
    public void registeredUser(boolean ok) {
        //suuccess Registraion show realationship page
        if (ok){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.first_frag_holder,new RelationshipFragment())
                    .commit();
        }
        else {
            //no registered , show same page
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.first_frag_holder,new RelationshipFragment())
                    .commit();
        }

    }

    @Override
    public void PassworChanged(boolean changed) {


        if (changed){
            //passowrd have been changed , show login fragment

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.first_frag_holder, new LoginFragment())
                    .commit();


        }
    }

    @Override
    public void RelationshipAdded(boolean ok) {
        //show Home page // student id should have heve been saved
        final Intent i = new Intent(this,ElfMainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
