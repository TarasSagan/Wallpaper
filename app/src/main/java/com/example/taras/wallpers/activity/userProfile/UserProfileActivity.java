package com.example.taras.wallpers.activity.userProfile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.taras.wallpers.R;
import com.example.taras.wallpers.fragments.profile.ProfileFragment;

public class UserProfileActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ProfileFragment profileFragment;
    public static final String USERNAME_KEY = "UERNAME";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_user_profile:
                    if(profileFragment == null){
                        profileFragment = new ProfileFragment();
                    }
                    showFragment(profileFragment);
                    return true;
                case R.id.navigation_user_photos:

                    return true;
                case R.id.navigation_user_liked:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        fragmentManager = getSupportFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_user_profile);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(profileFragment == null){
            profileFragment = new ProfileFragment();
        }
        showFragment(profileFragment);    }
    private void showFragment(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_user_profile_container, fragment);
        fragmentTransaction.commit();
    }
}
