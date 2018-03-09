package com.example.taras.wallpapers.fragments.search.photos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taras.wallpapers.activity.search.IOnSearch;
import com.example.taras.wallpapers.activity.search.SearchActivity;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;

public class SearchPhotosFragment extends BaseListFragment implements IOnSearch{
    private ISearchPhotosPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter.onSetQuery(getArguments().getString(SearchActivity.SearchQueryKEY));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public BaseListPresenter createPresenter() {
        SearchPhotosPresenter searchPhotosPresenter = new SearchPhotosPresenter();
        presenter = searchPhotosPresenter;
        return searchPhotosPresenter;
    }

    @Override
    public void onSearch(String query) {
        presenter.onSetQuery(query);
    }

}
