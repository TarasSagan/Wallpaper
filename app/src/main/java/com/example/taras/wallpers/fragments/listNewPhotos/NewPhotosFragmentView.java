package com.example.taras.wallpers.fragments.listNewPhotos;


import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

public interface NewPhotosFragmentView extends MvpView{
    void showMessage(String message);
    void showContent(List<PhotoItem> list);
}
