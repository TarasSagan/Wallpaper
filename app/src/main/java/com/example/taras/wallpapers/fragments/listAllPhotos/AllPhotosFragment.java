package com.example.taras.wallpapers.fragments.listAllPhotos;


import com.example.taras.wallpapers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;

public class AllPhotosFragment extends BaseListFragment{
    @Override
    public BaseListPresenter createPresenter() {
        return new AllPhotosPresenter();
    }
}