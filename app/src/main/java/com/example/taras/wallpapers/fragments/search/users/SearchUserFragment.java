package com.example.taras.wallpapers.fragments.search.users;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.activity.search.IOnSearch;
import com.example.taras.wallpapers.activity.search.SearchActivity;
import com.example.taras.wallpapers.api.ModelsOfResponse.search.users.User;
import com.example.taras.wallpapers.fragments.EndlessRecyclerOnScrollListener;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;


public class SearchUserFragment extends MvpFragment<ISearchUsersContract.View, SearchUserPresenter>
        implements ISearchUsersContract.View, IOnSearch {
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private ISearchUsersPresenter iSearchUsersPresenter;
    private SearchUserRecyclerAdapter searchUserRecyclerAdapter;
    private RecyclerView recyclerView;

    @Override
    public SearchUserPresenter createPresenter() {
        SearchUserPresenter searchUsersPresenter = new SearchUserPresenter();
        iSearchUsersPresenter = searchUsersPresenter;
        return searchUsersPresenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_list, container, false);
        recyclerView = (RecyclerView) view;
        searchUserRecyclerAdapter = new SearchUserRecyclerAdapter(new ArrayList<>(), getPresenter(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                getPresenter().getNextData();
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
        recyclerView.setAdapter(searchUserRecyclerAdapter);

        iSearchUsersPresenter.onSetQuery(getArguments().getString(SearchActivity.SearchQueryKEY));
        return view;
    }


    @Override
    public void showContent(List<User> list) {
        searchUserRecyclerAdapter.addNewData(list);
    }

    @Override
    public void removeContent() {
        searchUserRecyclerAdapter.removeAll();
    }


    @Override
    public void showMessage(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onSearch(String query) {
        iSearchUsersPresenter.onSetQuery(query);
    }
}
