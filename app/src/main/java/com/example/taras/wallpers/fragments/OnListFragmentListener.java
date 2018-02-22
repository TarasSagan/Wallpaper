package com.example.taras.wallpers.fragments;


import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;

public interface OnListFragmentListener {
    void onLike(PhotoItem item, int position);
    void onShowUser(PhotoItem item);
    void onDownload(PhotoItem item);
    void onAddToCollection(PhotoItem item);
    void onShowPhotoDetails(PhotoItem item);
    void onLoadNewData(int position);
    void onLoadPreviousData(int position);
}
