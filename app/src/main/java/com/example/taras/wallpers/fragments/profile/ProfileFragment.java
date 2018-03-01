package com.example.taras.wallpers.fragments.profile;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taras.wallpers.R;
import com.example.taras.wallpers.activity.userProfile.UserProfileActivity;
import com.example.taras.wallpers.api.ModelsOfResponse.profile.ProfileResponse;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends MvpFragment<ProfileFragmentContract.FragmentView, ProfileFragmentPresenter>
        implements ProfileFragmentContract.FragmentView{
    @BindView(R.id.textViewName) TextView textViewName;
    @BindView(R.id.textViewTwitterName) TextView textViewTwitterName;
    @BindView(R.id.textViewFollowingCount) TextView textViewFollowingCount;
    @BindView(R.id.textViewFollowersCount) TextView textViewFollowersCount;
    @BindView(R.id.textViewTotalPhotos) TextView textViewTotalPhotos;
    @BindView(R.id.textViewLocation) TextView textViewLocation;
    @BindView(R.id.textViewBio) TextView textViewBio;
    @BindView(R.id.imageView_profile) ImageView imageView_profile;
    @BindView(R.id.buttonLocation) Button buttonLocation;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public ProfileFragmentPresenter createPresenter() {
        return new ProfileFragmentPresenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        buttonLocation.setVisibility(View.INVISIBLE);
        getPresenter().getProfile(getActivity().getIntent().getStringExtra(UserProfileActivity.USERNAME_KEY));
        return view;
    }

    @Override
    public void showContent(ProfileResponse profile) {
        Picasso.with(getContext())
                .load(profile.getProfileImage().getLarge())
                .placeholder(getContext().getDrawable(R.drawable.ic_user_black_24dp))
                .error(getContext().getDrawable(R.drawable.ic_error))
                .into(imageView_profile);
        textViewName.setText(TextUtils.isEmpty(profile.getLastName())
                ? profile.getFirstName()
                : profile.getFirstName() + " " + profile.getLastName());

        if(!TextUtils.isEmpty(profile.getTwitterUsername())) {
            textViewTwitterName.setText("@" + profile.getTwitterUsername());
        }
        if(!TextUtils.isEmpty(profile.getLocation())) {
            textViewLocation.setText(profile.getLocation());
            buttonLocation.setVisibility(View.VISIBLE);
        }
        if(!TextUtils.isEmpty(profile.getBio())) {
            textViewBio.setText(profile.getBio());
        }
        textViewFollowingCount.setText(getString(R.string.profileFragment_following)
                + " " + profile.getFollowingCount());
        textViewFollowersCount.setText(getString(R.string.profileFragment_followers)
                + " " + profile.getFollowersCount());
        textViewTotalPhotos.setText(getString(R.string.profileFragment_photos)
                + " " + profile.getTotalPhotos());
    }

    @Override
    public void showInfo(String info) {
        Snackbar.make(imageView_profile, info, Snackbar.LENGTH_LONG).show();
    }
}
