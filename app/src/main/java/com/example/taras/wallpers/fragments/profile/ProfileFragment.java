package com.example.taras.wallpers.fragments.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taras.wallpers.R;
import com.example.taras.wallpers.api.ModelsOfResponse.profile.ProfileResponse;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends MvpFragment<ProfileFragmentView, ProfileFragmentPresenter> implements ProfileFragmentView{


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public ProfileFragmentPresenter createPresenter() {
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void showContent(ProfileResponse profile) {

    }

    @Override
    public void showInfo(String info) {

    }
}
