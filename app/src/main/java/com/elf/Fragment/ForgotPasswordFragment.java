package com.elf.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.elf.Network.CustomRetryPolicy;
import com.elf.Network.ElfRequestQueue;
import com.elf.R;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 9/8/16.
 */
public class ForgotPasswordFragment extends Fragment {



    private static final String TAG = "FORGOT";
    private static final String CHECK_URL = "";
    private static final String SUBMIT_URL = " ";


    @BindView(R.id.fp_email_ted)
    TextInputEditText mEamilBox;
    @BindView(R.id.fp_phone_ted) TextInputEditText mPhoneBox;
    @BindView(R.id.fp_check_button)
    Button mCheckButton;
    private View mView;
    @BindView(R.id.fp_pass_ted) TextInputEditText mPasswordBox;
    @BindView(R.id.fp_rpass_ted) TextInputEditText mRePassBox;
    @BindView(R.id.fp_new_password_button) Button mSubmitButton;

    ElfRequestQueue mRequestQue;

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
        mRequestQue = ElfRequestQueue.getInstance(getContext());





    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     mView=inflater.inflate(R.layout.forgot_password,container,false);
        ButterKnife.bind(this,mView);
        mView.findViewById(R.id.forgot_password).setVisibility(View.VISIBLE);
        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mEmail = mEamilBox.getText().toString();
                final String mPhone = mPhoneBox.getText().toString();

                //validate user name and password for this user
                validateUser(mEmail,mPhone);
            }
        });

        return mView;
    }

    private void validateUser(String mEmail, String mPhone) {
        //make objects get Repsonse , if success show reset password page
        final JSONObject mObject = new JSONObject();
        try {

            mObject.put("Email", mEmail);
            mObject.put("phone", mPhone);
        } catch (Exception e) {
            Log.d(TAG, "validateUser: ");
        }

        JsonObjectRequest mRequest = new JsonObjectRequest(Request.Method.POST, CHECK_URL, mObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getLocalizedMessage());
            }
        });

        mRequest.setRetryPolicy(new CustomRetryPolicy());
        mRequestQue.addToElfREquestQue(mRequest);
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
}
