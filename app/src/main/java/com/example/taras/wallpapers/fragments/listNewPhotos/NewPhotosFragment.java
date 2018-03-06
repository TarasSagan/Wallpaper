package com.example.taras.wallpapers.fragments.listNewPhotos;


import com.example.taras.wallpapers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;

public class NewPhotosFragment extends BaseListFragment{
    @Override
    public BaseListPresenter createPresenter() {
        return new NewPhotosFragmentPresenter();
    }
}