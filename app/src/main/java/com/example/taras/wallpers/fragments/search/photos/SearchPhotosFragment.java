package com.example.taras.wallpers.fragments.search.photos;


import com.example.taras.wallpers.activity.search.ISearchActivity;
import com.example.taras.wallpers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpers.fragments.baseListFragment.BaseListPresenter;

public class SearchPhotosFragment extends BaseListFragment implements ISearchActivity {
    private ISearchPhotosPresenter presenter;
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
