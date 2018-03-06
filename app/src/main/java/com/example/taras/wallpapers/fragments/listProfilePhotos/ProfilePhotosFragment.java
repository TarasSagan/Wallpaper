package com.example.taras.wallpapers.fragments.listProfilePhotos;

import com.example.taras.wallpapers.activity.userProfile.UserProfileActivity;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;


public class ProfilePhotosFragment extends BaseListFragment {
    @Override
    public BaseListPresenter createPresenter() {
        return new ProfilePhotosPresenter(getActivity().getIntent().getStringExtra(UserProfileActivity.USERNAME_KEY));
    }
}
