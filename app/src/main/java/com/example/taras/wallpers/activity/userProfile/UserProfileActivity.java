package com.example.taras.wallpers.activity.userProfile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.taras.wallpers.R;

public class UserProfileActivity extends AppCompatActivity {
    public static final String USERNAME_KEY = "UERNAME";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_user_profile:

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


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_user_profile);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
