package com.example.taras.wallpers.fragments.listProfileLikesPhotos;

import com.example.taras.wallpers.activity.userProfile.UserProfileActivity;
import com.example.taras.wallpers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpers.fragments.baseListFragment.BaseListPresenter;


public class ProfileLikesPhotosFragment extends BaseListFragment {
    @Override
    public BaseListPresenter createPresenter() {
        return new ProfileLikesPhotosPresenter(getActivity().getIntent().getStringExtra(UserProfileActivity.USERNAME_KEY));
    }
}
