package com.example.taras.wallpapers.activity.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.fragments.search.photos.SearchPhotosFragment;
import com.example.taras.wallpapers.fragments.search.users.SearchUserFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity{
    public static final String SearchQueryKEY = "SearchQueryKEY";
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SearchPhotosFragment searchPhotosFragment;
    private SearchUserFragment searchUserFragment;
    private IOnSearch iOnSearch;
    @BindView(R.id.navigation_search) BottomNavigationView navigation;
    @BindView(R.id.editTextSearch) EditText editTextSearch;
    @BindView(R.id.buttonSearch) Button buttonSearch;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search_photos:
                    showFragment(searchPhotosFragment);
                    iOnSearch = searchPhotosFragment;
                    return true;
                case R.id.navigation_search_users:
                    showFragment(searchUserFragment);
                    iOnSearch = searchUserFragment;
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
        searchPhotosFragment = new SearchPhotosFragment();
        searchUserFragment = new SearchUserFragment();
        fragmentManager = getSupportFragmentManager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @OnClick(R.id.buttonSearch)
    public void onClick(){
        if(iOnSearch == null){
            showFragment(searchPhotosFragment);
            iOnSearch = searchPhotosFragment;
        }else{iOnSearch.onSearch(editTextSearch.getText().toString());}
    }

    @OnClick(R.id.editTextSearch)
    public void onClickEditText(){
        onClick();
    }

    private void showFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString(SearchQueryKEY, editTextSearch.getText().toString());
        fragment.setArguments(bundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.replace(R.id.searchActivityContainer, fragment);
        fragmentTransaction.commit();
    }


}
