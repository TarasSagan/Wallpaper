package com.example.taras.wallpapers.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.activity.search.SearchActivity;
import com.example.taras.wallpapers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpapers.api.ModelsOfResponse.profile.ProfileResponse;
import com.example.taras.wallpapers.api.UnsplashService;
import com.example.taras.wallpapers.fragments.listNewPhotos.NewPhotosFragment;
import com.example.taras.wallpapers.fragments.listTrendingPhotos.TrendingPhotosFragment;
import com.example.taras.wallpapers.repository.RepositoryController;
import com.example.taras.wallpapers.repository.SharedPreferences.TokenManager;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;

public class MainActivity extends MvpActivity<MainActivityContract.View, MainActivityPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityContract.View{
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private NewPhotosFragment newNewPhotosFragment;
    private TrendingPhotosFragment trendingPhotosFragment;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View header;
    private TextView textLogin, textNavHeaderUsername;
    private ImageView imageProfilePhoto;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();

        try {this.getSupportActionBar().hide();
        } catch (NullPointerException e){}

        bindView();
        setPublicUser();
        getPresenter().initPresenter();
    }

    private void bindView(){
        header = navigationView.getHeaderView(0);
        textLogin = header.findViewById(R.id.textLogin);
        textNavHeaderUsername = header.findViewById(R.id.textNavHeaderUsername);
        imageProfilePhoto = header.findViewById(R.id.imageProfilePhoto);
        btnLogin = header.findViewById(R.id.btnLogin);
        textNavHeaderUsername.setOnClickListener(v -> getPresenter().onShowCurrentUserProfile());
        imageProfilePhoto.setOnClickListener(v -> getPresenter().onShowCurrentUserProfile());
        btnLogin.setOnClickListener(v -> getPresenter().onLoginLogout());
        textLogin.setOnClickListener(v -> getPresenter().onLoginLogout());
    }

    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter(this);
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

    @Override
    public void showMessage(String message) {
        Snackbar.make(drawer, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setAuthUser(ProfileResponse user) {
        btnLogin.setBackground(getDrawable(R.drawable.ic_log_out));
        textLogin.setVisibility(View.INVISIBLE);
        textNavHeaderUsername.setVisibility(View.VISIBLE);
        textNavHeaderUsername.setText(TextUtils.isEmpty(user.getLastName())
                ? user.getFirstName()
                : user.getFirstName()
                + " "
                + user.getLastName());
        imageProfilePhoto.setVisibility(View.VISIBLE);
        Picasso.with(this).load(user.getProfileImage().getMedium())
                .placeholder(getDrawable(R.drawable.ic_user_black_24dp))
                .error(getDrawable(R.drawable.ic_error))
                .into(imageProfilePhoto);
    }

    @Override
    public void setPublicUser() {
        textNavHeaderUsername.setVisibility(View.INVISIBLE);
        imageProfilePhoto.setVisibility(View.INVISIBLE);
        textLogin.setText(getString(R.string.login));
        btnLogin.setBackground(getDrawable(R.drawable.ic_login));
    }
}
