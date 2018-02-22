package com.example.taras.wallpers.fragments.listNewPhotos;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.taras.wallpers.R;
import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpers.fragments.OnListFragmentListener;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;


import java.util.List;

import butterknife.BindView;


public class NewPhotosFragment extends MvpFragment<NewPhotosFragmentView, NewPhotosFragmentPresenter> implements NewPhotosFragmentView{
    private RecyclerView recyclerView;
    private PhotosRecyclerAdapter photosRecyclerAdapter;
    @Override
    public NewPhotosFragmentPresenter createPresenter() {
        return new NewPhotosFragmentPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random_photos, container, false);
        recyclerView = view.findViewById(R.id.list);

        getPresenter().getNewPhotos();
        return view;
    }

    void initRecycler(List<PhotoItem> listPhotos){
        if(photosRecyclerAdapter == null) {
            photosRecyclerAdapter = new PhotosRecyclerAdapter(listPhotos, getPresenter(), getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(photosRecyclerAdapter);
        }else {
            photosRecyclerAdapter.setValues(listPhotos);
            int scrollToPosition = recyclerView.getScrollState() - 10;
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scrollToPosition(scrollToPosition);
        }
    }





    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showContent(List<PhotoItem> list) {
        Log.d("TAG", "WORK!");
        initRecycler(list);
    }

}
