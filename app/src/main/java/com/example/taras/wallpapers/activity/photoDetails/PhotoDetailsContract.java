package com.example.taras.wallpapers.activity.photoDetails;


import com.example.taras.wallpapers.api.ModelsOfResponse.photoDetails.PhotoDetailsResponse;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface PhotoDetailsContract {
    public interface View extends MvpView{
        void showDetails(PhotoDetailsResponse details);
        void showMessage(String info);
    }
    public interface Presenter{
        void getDetails();
    }
}
