package com.example.taras.wallpers.fragments.listTrendingPhotos;

import com.example.taras.wallpers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpers.fragments.baseListFragment.BaseListPresenter;

public class TrendingPhotosFragment extends BaseListFragment{

    @Override
    public BaseListPresenter createPresenter() {
        return new TrendingPhotosPresenter();
    }
}