package com.example.taras.wallpapers.activity.userProfile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.fragments.listProfileLikesPhotos.ProfileLikesPhotosFragment;
import com.example.taras.wallpapers.fragments.listProfilePhotos.ProfilePhotosFragment;
import com.example.taras.wallpapers.fragments.profile.ProfileFragment;

public class UserProfileActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ProfileFragment profileFragment;
    private ProfilePhotosFragment profilePhotosFragment;
    private ProfileLikesPhotosFragment profileLikesPhotosFragment;
    public static final String USERNAME_KEY = "UERNAME";
    public static final String CURRENT_USER_KEY = "CURRENT_USER_KEY";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_user_profile:
                    showFragment(profileFragment);
                    return true;
                case R.id.navigation_user_photos:
                    showFragment(profilePhotosFragment);
                    return true;
                case R.id.navigation_user_liked:
                    showFragment(profileLikesPhotosFragment);
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_user_profile);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        
        fragmentManager = getSupportFragmentManager();
        profileFragment = new ProfileFragment();
        profilePhotosFragment = new ProfilePhotosFragment();
        profileLikesPhotosFragment = new ProfileLikesPhotosFragment();

        showFragment(profileFragment);
    }

    private void showFragment(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_user_profile_container, fragment);
        fragmentTransaction.commit();
    }
}
