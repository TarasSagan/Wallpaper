package com.example.taras.wallpapers.fragments.listRandomPhotos;

import com.example.taras.wallpapers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;


public class RandomPhotosFragment extends BaseListFragment {
    @Override
    public BaseListPresenter createPresenter() {
        return new RandomPhotosPresenter();
    }
}
