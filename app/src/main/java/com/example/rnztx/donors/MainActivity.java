package com.example.rnztx.donors;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rnztx.donors.feeds.AboutActivity;
import com.example.rnztx.donors.feeds.RequirementForm;
import com.example.rnztx.donors.feeds.accepted.FeedAccepted;
import com.example.rnztx.donors.feeds.chat.MessageList;
import com.example.rnztx.donors.feeds.intro.auth.SigninActivity;
import com.example.rnztx.donors.feeds.pending.FeedPending;
import com.example.rnztx.donors.models.utils.Utilities;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient mGoogleApiClient;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will r the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int[] tabIcons = {R.drawable.tab_pending,
            R.drawable.tab_accepted
            ,R.drawable.tab_add_request,
            R.drawable.tab_messages};
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Add Tab bar Navigation
        mTabLayout = (TabLayout) findViewById(R.id.tabbar);
        mTabLayout.setupWithViewPager(mViewPager);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mGoogleApiClient.connect();
        getSupportActionBar().setElevation(0);

        for (int i=0; i<4;i++){
            mTabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            private int colorSelected = ContextCompat.getColor(mContext,R.color.tabSelected);
            private int colorUnselected = ContextCompat.getColor(mContext,R.color.tabUnselected);
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Log.e(LOG_TAG,"Selected: "+tab.getPosition());
                int tabPosition = tab.getPosition();
                Drawable icon = ContextCompat.getDrawable(mContext,tabIcons[tabPosition]);
                icon.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN);
                tab.setIcon(icon);
                mViewPager.setCurrentItem(tabPosition,true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(colorUnselected,PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            Utilities.signOut(this,mGoogleApiClient);
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.action_refresh){
            recreate();
        }
        else if(id == R.id.action_about){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return FeedPending.newInstance();
                case 1:
                    return FeedAccepted.newInstance();
                case 2:
                    return RequirementForm.newInstance(position + 1);
                case 3:
                    return MessageList.newInstance();
                default:
                    return new Fragment();
            }
//            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "Pending";
//                case 1:
//                    return "Accepted";
//                case 2:
//                    return "New";
//                case 3:
//                    return "Messages";
//            }
            return null;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onBackPressed() {
        //Exit On Back Button Pressed
        super.onBackPressed();
        finish();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeIntent);
    }
}
