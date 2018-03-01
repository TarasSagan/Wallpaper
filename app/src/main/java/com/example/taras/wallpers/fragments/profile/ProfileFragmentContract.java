package com.example.taras.wallpers.fragments.profile;


import com.example.taras.wallpers.api.ModelsOfResponse.profile.ProfileResponse;
import com.hannesdorfmann.mosby3.mvp.MvpView;
public interface ProfileFragmentContract  {
    public interface FragmentView extends MvpView{
        void showContent(ProfileResponse profile);
        void showInfo(String info);
    }
    public interface FragmentPresenter{
        void getProfile(String username);
    }

}
