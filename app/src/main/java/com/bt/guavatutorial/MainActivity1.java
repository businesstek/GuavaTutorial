package com.bt.guavatutorial;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final String TAG = "GuavaTutorial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
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


        if (id == R.id.nav_basic) {
            // need viewpager with 5 fragments to handle basic

            getSupportFragmentManager().beginTransaction() .addToBackStack(null)
                    .replace(R.id.fragment, PlaceholderFragment.newInstance("Basic"))
                    .commit();


        } else if (id == R.id.nav_caching) {
            //put fragment in main content

        } else if (id == R.id.nav_collections) {

        } else if (id == R.id.nav_concurrency) {

        } else if (id == R.id.nav_eventbus) {

        } else if (id == R.id.nav_functional) {

        } else if (id == R.id.nav_graphs) {

        } else if (id == R.id.nav_hashing) {

        } else if (id == R.id.nav_math) {

        } else if (id == R.id.nav_networking) {

        } else if (id == R.id.nav_primitives) {

        } else if (id == R.id.nav_ranges) {

        } else if (id == R.id.nav_reflection) {

        } else if (id == R.id.nav_strings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements
            View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_LABEL = "section_label";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(String sectionLabel) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(ARG_SECTION_LABEL, sectionLabel);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            String feature = getArguments().getString(ARG_SECTION_LABEL);
            //Label
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, feature));
            //Description
            TextView description = (TextView) rootView.findViewById(R.id.section_description);
            description.setText(getDescription(feature));

            //Execute code for Guava feature
            Button btRun = (Button) rootView.findViewById(R.id.run);
            btRun.setOnClickListener(this);
            btRun.setTag((Object) feature);

            return rootView;
        }

        @Override
        public void onClick(View view) {

            String feature = (String) view.getTag();
            //run method based upon tag
            GuavaClass method = (GuavaClass) getMethod(feature);

            String results = method.run();

            //output of method will be put into result textview
            TextView tv = (TextView)view.findViewById(R.id.section_results);
            tv.setText(results);

        }
    }

    public static GuavaClass getMethod(String feature){
        Log.i(TAG,"found method");
        GuavaClass gc = null;
        switch (feature) {
            default:
            case "Basic":
                gc = new GuavaBasic();
                break;
            case "Caching":{
                gc = new GuavaCaching();
            }

        }

        return gc;
    }

    public static String getDescription(String feature){

        return "description";
    }

}
