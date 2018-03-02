package com.example.taras.wallpers.fragments.listProfilePhotos;

import com.example.taras.wallpers.activity.userProfile.UserProfileActivity;
import com.example.taras.wallpers.fragments.baseListFragment.BaseListFragment;
import com.example.taras.wallpers.fragments.baseListFragment.BaseListPresenter;


public class ProfilePhotosFragment extends BaseListFragment {
    @Override
    public BaseListPresenter createPresenter() {
        return new ProfilePhotosPresenter(getActivity().getIntent().getStringExtra(UserProfileActivity.USERNAME_KEY));
    }
}
