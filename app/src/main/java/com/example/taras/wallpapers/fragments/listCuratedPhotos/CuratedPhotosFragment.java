package com.example.taras.wallpapers.fragments.listCuratedPhotos;

import com.example.taras.wallpapers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;

public class CuratedPhotosFragment extends BaseListFragment{

    @Override
    public BaseListPresenter createPresenter() {
        return new CuratedPhotosPresenter();
    }
}