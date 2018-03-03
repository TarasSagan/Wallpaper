package com.example.taras.wallpers.activity.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taras.wallpers.R;
import com.example.taras.wallpers.fragments.search.photos.SearchPhotosFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity{
    private ISearchActivity search;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SearchPhotosFragment searchPhotosFragment;
    @BindView(R.id.editTextSearch) EditText editTextSearch;
    @BindView(R.id.buttonSearch) Button buttonSearch;
    @BindView(R.id.navigation_search) BottomNavigationView navigation;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search_photos:
                    search = searchPhotosFragment;
                    showFragment(searchPhotosFragment);
                    return true;
                case R.id.navigation_search_users:
                    search = searchPhotosFragment;
                    showFragment(searchPhotosFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        getSupportActionBar().setTitle("");
        searchPhotosFragment = new SearchPhotosFragment();
        fragmentManager = getSupportFragmentManager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        search = searchPhotosFragment;
        showFragment(searchPhotosFragment);
    }

    @OnClick(R.id.buttonSearch)
    public void onClick(){
        String editable;
        if(!TextUtils.isEmpty(editTextSearch.getText().toString())){

        }
        editable = editTextSearch.getText().toString();
        search.onSearch(editable);

        switch (navigation.getSelectedItemId()){
            case R.id.navigation_search_photos:
                search.onSearch(editable);
                break;
            case R.id.navigation_search_users:
                search.onSearch(editable);
                break;
        }
    }

    private void showFragment(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.searchActivityContainer, fragment);
        fragmentTransaction.commit();
    }

}
