package com.example.taras.wallpers.fragments;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;

import java.util.List;

public class PhotoItemDiffCallback extends DiffUtil.Callback {
    private List<PhotoItem> mOldList;
    private List<PhotoItem> mNewList;

    public PhotoItemDiffCallback(List<PhotoItem> mOldList, List<PhotoItem> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        // add a unique ID property on Contact and expose a getId() method
        return mOldList.get(oldItemPosition).getId() == mNewList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        PhotoItem oldPhotoItem = mOldList.get(oldItemPosition);
        PhotoItem newPhotoItem = mNewList.get(newItemPosition);

        if (TextUtils.equals(oldPhotoItem.getId(), newPhotoItem.getId())){
            return true;
        }else return false;
    }
}
