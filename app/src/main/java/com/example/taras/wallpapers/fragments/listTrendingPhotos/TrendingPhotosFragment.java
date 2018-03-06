package com.example.taras.wallpapers.fragments.listTrendingPhotos;

import com.example.taras.wallpapers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;

public class TrendingPhotosFragment extends BaseListFragment{

    @Override
    public BaseListPresenter createPresenter() {
        return new TrendingPhotosPresenter();
    }
}