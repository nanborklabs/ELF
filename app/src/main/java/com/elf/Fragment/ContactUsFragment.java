package com.elf.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

/**
 * Created by nandhu on 18/8/16.
 */
public class ContactUsFragment extends Fragment {
    private View mView;


    private static final String TAG = "About";
    private static final String  URL = " ";

    @BindView(R.id.feed_editText)
    EditText mEditext;


    ElfRequestQueue mRequestQueue;



    @BindView(R.id.submit_feedback)
    Button mSubmit;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = ElfRequestQueue.getInstance(getContext());
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
        mView=inflater.inflate(R.layout.contact_us,container,false);
        ButterKnife.bind(this.mView);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mfeed = mEditext.getText().toString();
                sendFeed(mfeed);
            }
        });
        return mView;
    }

    private void sendFeed(String mfeed) {
        JSONObject object = null;
        try {
            object= new JSONObject();

            //todo: get student id
            object.put("StudentId","1");
            object.put("feed",mfeed);
        }
        catch (Exception e ){
            Log.d(TAG, "sendFeed: ");
        }


        //make request
        JsonArrayRequest mRequest = new JsonArrayRequest(Request.Method.POST, URL, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "onResponse: ");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: ");
            }
        });

        mRequest.setRetryPolicy(new CustomRetryPolicy());
        mRequestQueue.addToElfREquestQue(mRequest);

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
}
