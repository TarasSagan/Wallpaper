package com.example.taras.wallpapers.fragments.baseListFragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpapers.fragments.EndlessRecyclerOnScrollListener;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListFragment extends MvpFragment<ListFragmentContract.FragmentView, BaseListPresenter>
        implements ListFragmentContract.FragmentView {
    private RecyclerView recyclerView;
    private PhotosRecyclerAdapter photosRecyclerAdapter;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    @Override
    public abstract BaseListPresenter createPresenter();
    private final String latest = "latest";
    private final String oldest = "oldest";
    private final String popular = "popular";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_list, container, false);
        initToolbar(view);
        initRecyclerView(view, R.id.list);

        getPresenter().getNextData();
        return view;
    }
    protected void initToolbar(View view){
        Toolbar baseListToolbar = view.findViewById(R.id.base_list_toolbar);
        setHasOptionsMenu(true);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(baseListToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar != null){actionBar.setTitle("");}
    }

    protected void initRecyclerView(View view, int recyclerID){
        recyclerView = view.findViewById(recyclerID);
        photosRecyclerAdapter = new PhotosRecyclerAdapter(new ArrayList<>(), getPresenter(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                getPresenter().getNextData();
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
        recyclerView.setAdapter(photosRecyclerAdapter);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void removeContent() {
        photosRecyclerAdapter.removeAllData();

    }

    @Override
    public void showContent(List<PhotoItem> list) {
        photosRecyclerAdapter.addNewData(list);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_sort) {

        } else if (id == R.id.menu_sort_latest) {
            getPresenter().setOrderBy(latest);
        } else if (id == R.id.menu_sort_oldest) {
            getPresenter().setOrderBy(oldest);
        } else if (id == R.id.menu_sort_popular) {
            getPresenter().setOrderBy(popular);
        }
        return true;
    }
}


