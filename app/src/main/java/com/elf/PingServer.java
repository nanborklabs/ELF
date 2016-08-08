package com.elf;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by nandhu on 5/8/16.
 */
public class PingServer extends AsyncTask<URL,Void,Void> {

    String input;
    public PingServer() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(URL... params) {
        URL url=params[0];
        try {
            URLConnection urlConnection=url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();
            InputStream in=urlConnection.getInputStream();
            input=in.toString();
        }
        catch (Exception e){
            Log.d("Parent", "doInBackground: exception");
        }
        Log.d("PArent", "String values returned "+input);


        return null;
    }
}
