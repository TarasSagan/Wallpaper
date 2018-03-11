package com.example.taras.wallpapers.fragments.listRandomPhotos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;


public class RandomPhotosFragment extends BaseListFragment {
    @Override
    public BaseListPresenter createPresenter() {
        return new RandomPhotosPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random_photos, container, false);
        initRecyclerView(view, R.id.random_photos_list);

        getPresenter().getNextData();
        return view;
    }
}
