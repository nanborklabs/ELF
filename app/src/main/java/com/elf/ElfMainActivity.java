package com.elf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.elf.Fragment.ContactUsFragment;
import com.elf.Fragment.HomeFragment;
import com.elf.Fragment.NotificationFragment;
import com.elf.Fragment.PaymentsFragment;
import com.elf.Fragment.ReportsFragment;

import butterknife.ButterKnife;

public class ElfMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String PREFS = "ELF_PARENT";
    Fragment mainFragment;
    private static FragmentManager fManager;
    private static FragmentTransaction mTrasaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstime()){
            final Intent i = new Intent(this, FIrstActivity.class);
            startActivity(i);

        }



        //
        setContentView(R.layout.activity_elf_main);
        ButterKnife.bind(this);
        mainFragment = new HomeFragment().newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_holder,mainFragment)
                .commit();

        final  SharedPreferences sf = getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sf.edit();

        editor.commit();





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private boolean isFirstime() {
        final SharedPreferences sf  = this.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return sf.getBoolean("isFirstTime",true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       switch (id){
           case R.id.home:
               mainFragment=new HomeFragment();
               break;
           case  R.id.report :
               mainFragment=new ReportsFragment();
               break;
           case  R.id.noti :
               mainFragment=new NotificationFragment();
               break;
           case  R.id.payments :
               mainFragment=new PaymentsFragment();
               break;
           case  R.id.contacts:
               mainFragment=new ContactUsFragment();
               break;
       }
//
        //Replace Fragment Transaction and close the drawer
      getSupportFragmentManager().beginTransaction().replace(R.id.frag_holder,mainFragment)

              .commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
