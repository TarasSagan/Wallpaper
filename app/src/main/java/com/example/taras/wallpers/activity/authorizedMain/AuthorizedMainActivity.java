package com.example.taras.wallpers.activity.authorizedMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.taras.wallpers.R;
import com.example.taras.wallpers.activity.search.SearchActivity;
import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpers.api.UnsplashService;
import com.example.taras.wallpers.fragments.listNewPhotos.NewPhotosFragment;
import com.example.taras.wallpers.fragments.listTrendingPhotos.TrendingPhotosFragment;
import com.example.taras.wallpers.repository.RepositoryController;
import com.example.taras.wallpers.repository.SharedPreferences.TokenManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class AuthorizedMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private RepositoryController repositoryController;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TokenManager tokenManager;
    private NewPhotosFragment newNewPhotosFragment;
    private TrendingPhotosFragment trendingPhotosFragment;
    @Inject Flowable<List<PhotoItem>> flowableResponce;
    @Inject UnsplashService unsplashService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
//
//        repositoryController = new RepositoryController();
//        repositoryController.getResponse(getApplicationContext());

//        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//        startActivity(intent);
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
        if (true) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation_profile view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_trending) {
            if(trendingPhotosFragment == null){
                trendingPhotosFragment = new TrendingPhotosFragment();
            }
            showFragment(trendingPhotosFragment);
        } else if (id == R.id.nav_new) {
            if(newNewPhotosFragment == null){
                newNewPhotosFragment = new NewPhotosFragment();
            }
            showFragment(newNewPhotosFragment);
        } else if (id == R.id.nav_following) {

        } else if (id == R.id.nav_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showFragment(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
