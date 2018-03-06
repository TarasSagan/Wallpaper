package com.example.taras.wallpapers.fragments.listProfileLikesPhotos;

import com.example.taras.wallpapers.activity.userProfile.UserProfileActivity;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;


public class ProfileLikesPhotosFragment extends BaseListFragment {
    @Override
    public BaseListPresenter createPresenter() {
        return new ProfileLikesPhotosPresenter(getActivity().getIntent().getStringExtra(UserProfileActivity.USERNAME_KEY));
    }
}
