package com.elf;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by nandhu on 5/8/16.
 */
public class PingServer extends AsyncTask<URL,Void,Void> {

    private static final String TAG="NETWORK";
    String input;
    public String username;
    public String password;

    public PingServer(String username, String password) {
        super();
        this.username=username;
        this.password=password;
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


        return null;
    }

    private void readInput(InputStream in) throws XmlPullParserException, IOException {
         XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
         XmlPullParser myparser = xmlFactoryObject.newPullParser();
        myparser.setInput(in, null);
        Log.d("Parsed"," " +myparser.toString());
        int event = myparser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT)
        {
            String name=myparser.getName();
            Log.d(TAG, "naem parsed::       "+name);
            switch (event){
                case XmlPullParser.START_TAG:
                    break;

                case XmlPullParser.END_TAG:
                    if(name.equals("ParentId")){
                        String parent = myparser.getAttributeValue(null,"value");
                        Log.d(TAG, "readInput: "+parent);
                    }
                    break;
            }
            event = myparser.next();
        }
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
