package com.elf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.Fragment.ContactUsFragment;
import com.elf.Fragment.HomeFragment;
import com.elf.Fragment.NoStudentFragment;
import com.elf.Fragment.NotificationFragment;
import com.elf.Fragment.PaymentsFragment;
import com.elf.Fragment.RelationshipFragment;
import com.elf.Fragment.ReportsFragment;
import com.elf.Network.ElfRequestQueue;
import com.elf.UserPrefs.MyPrefs;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.ButterKnife;

public class ElfMainActivity extends AppCompatActivity
        implements  NoStudentFragment.AddStudent, Drawer.OnDrawerItemClickListener {

    private static final String PREFS = "ELF_PARENT";


    private static  final String ACCPET_REQ = "";
    private static final String TAG = "MAIN ACTIVITY";

    Fragment mainFragment;
    private static FragmentManager fManager;
    private static FragmentTransaction mTrasaction;
    Drawer mDrawer =null;

    ElfRequestQueue mRequestQueue;
    MyPrefs myPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mRequestQueue = ElfRequestQueue.getInstance(this);
        myPrefs = new MyPrefs(this);
        //check whether activity is first time ,show login activity
      /*  if (isFirstime()){
            final Intent i = new Intent(this, FIrstActivity.class);
            startActivity(i);
            finish();

        }
        */


        setContentView(R.layout.app_bar_elf_main);
        // if student accepted the Request show home fragmetn with student id ,else no student page
//        if(RequestAccepted()){
            //a student  has accepted show dash

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_holder,new HomeFragment())
                    .commit();


//        else{
//            getSupportFragmentManager().beginTransaction().
//                    replace(R.id.frag_holder,new NoStudentFragment())
//                    .commit();
//        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header_img)
                .addProfiles(
                        new ProfileDrawerItem().withName("Student Name").withEmail("Standard").withIcon(R.drawable.ic_account_circle_white_48dp)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(0).withName("Home").withIcon(R.drawable.ic_home_black_48dp)
                .withIconTintingEnabled(true);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(1).withName("Reports").withIcon(R.drawable.ic_assessment_black_24dp).withIconTintingEnabled(true);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(2).withName("Tests").withIcon(R.drawable.ic_assignment_black_48dp) .withIconTintingEnabled(true);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(3).withName("Notifications").withIcon(R.drawable.ic_message_black_48dp) .withIconTintingEnabled(true);

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1, new DividerDrawerItem(),
                        item2, new DividerDrawerItem(),
                        item3, new DividerDrawerItem(),
                        item4, new DividerDrawerItem()
                )
                .withHasStableIds(true)
                .withDrawerLayout(R.layout.material_drawer)
                .withActionBarDrawerToggle(false)
                .withOnDrawerItemClickListener(this)
                .withDrawerLayout(R.layout.material_drawer)
                .withAccountHeader(headerResult)
                .build();



        //

        ButterKnife.bind(this);
        mainFragment = HomeFragment.newInstance();


        final  SharedPreferences sf = getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sf.edit();

        editor.commit();










    }


    //check shared prefrences for student exists flag
      // if student exists  ,return true

    private boolean RequestAccepted() {

       if (myPrefs.isStudentAcceptedRequested()){
           return true;
       }else {

           //student is not accepted check for student's acceptance
            // if studetn accepted update in the shared prefs

                   checkStudentAcceptance();


          return false;
       }

    }

    private void checkStudentAcceptance() {
        JSONObject mObject  = new JSONObject();
        try{

            mObject.put("ParentId","4");

        }
        catch (Exception e ){
            Log.d(TAG, "checkStudentAcceptance: ");
        }
      JsonArrayRequest mRequest = new JsonArrayRequest(Request.Method.POST, ACCPET_REQ, mObject, new Response.Listener<JSONArray>() {
          @Override
          public void onResponse(JSONArray response) {
            //if accepted , save status in prrefreneces
//         todo: save prefreeces
//    myPrefs.setRequestAccepted(true);
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {

          }
      });
        mRequestQueue.addToElfREquestQue(mRequest);

    }

    private boolean processResponse(JSONArray response) {

        try{

            JSONObject mObject  = response.getJSONObject(0);
            String status = mObject.getString("StatusCode");
            return status.equals("1000");

        }
        catch (Exception e ){
            Log.d(TAG, "processResponse: ");
        }
        return false;
    }

    private boolean isFirstime() {
        final SharedPreferences sf  = this.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return sf.getBoolean("isFirstTime",true);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.elf_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    /*this method is called from { @link NoStudentFragment  }
    * which takes to Relation ship page to add student
    * */
    @Override
    public void AddStudent() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_holder, new RelationshipFragment())
                .commit();
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        Log.d("Home", "onItemClick: " + position);
        if (drawerItem != null) {
            if (drawerItem.getIdentifier() == 0) {
                mainFragment = HomeFragment.newInstance();
            }
            if (drawerItem.getIdentifier() == 1) {
                mainFragment = ReportsFragment.newInstance();
            }
            if (drawerItem.getIdentifier() == 2) {
                mainFragment = NotificationFragment.newInstnace();
            }
            if (drawerItem.getIdentifier() == 3) {
                mainFragment = PaymentsFragment.newInstance();
            }
        if (mainFragment != null) {




            FragmentManager fm = getSupportFragmentManager();
            if (fm != null) {
                FragmentTransaction ft = fm.beginTransaction();
                if (ft != null) {
                    ft.replace(R.id.frag_holder, mainFragment, "NAV")
                            .addToBackStack(null)
                            .commit();
                }
            }
        }

            mDrawer.closeDrawer();



        }
        return true;
    }

}

