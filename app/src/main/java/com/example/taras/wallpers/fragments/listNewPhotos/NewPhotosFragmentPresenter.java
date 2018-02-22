package com.example.taras.wallpers.fragments.listNewPhotos;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.taras.wallpers.App;
import com.example.taras.wallpers.R;
import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpers.api.ModelsOfResponse.likeResponce.LikeResponse;
import com.example.taras.wallpers.api.UnsplashService;
import com.example.taras.wallpers.fragments.OnListFragmentListener;
import com.example.taras.wallpers.repository.SharedPreferences.TokenManager;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import java.util.LinkedList;
import javax.inject.Inject;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewPhotosFragmentPresenter extends MvpBasePresenter<NewPhotosFragmentView>
implements OnListFragmentListener{
    private LinkedList<PhotoItem> photoItemList;
    public String orderBy = "latest";
    private int currentPage = 2;
    private int perPage = 30;
    @Inject Context context;
    @Inject TokenManager tokenManager;
    @Inject UnsplashService unsplashService;

    public NewPhotosFragmentPresenter() {
        App.getComponent().inject(this);
    }
    public void getNewPhotos(){
        if (isNetworkConnected()) {
            Flowable<LinkedList<PhotoItem>> flowable = unsplashService.getPhotosCurated(currentPage, perPage, orderBy);
            flowable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(photos -> {
                        photoItemList = photos;
                        ifViewAttached(view -> view.showContent(photoItemList));
                    });
        }ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
    }

    @Override
    public void onLike(PhotoItem item, int position) {
        if(isNetworkConnected() && tokenManager.containsToken()){
            if (item.isLikedByUser()){
                Flowable<LikeResponse> flowable = unsplashService.postUnlike(item.getId());
                flowable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response ->
                                photoItemList.get(position).setLikedByUser(response.getPhoto().isLikedByUser()));
            }else {
                Flowable<LikeResponse> flowable = unsplashService.postLike(item.getId());
                flowable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response ->
                                photoItemList.get(position).setLikedByUser(response.getPhoto().isLikedByUser()));
            }
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));

    }

    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }

    @Override
    public void onShowUser(PhotoItem item) {

    }

    @Override
    public void onDownload(PhotoItem item) {

    }

    @Override
    public void onAddToCollection(PhotoItem item) {

    }

    @Override
    public void onShowPhotoDetails(PhotoItem item) {

    }

    @Override
    public void onLoadNewData(int position) {
      if(isNetworkConnected()) {
          getNewPhotos();
          currentPage++;
      }
    }

    @Override
    public void onLoadPreviousData(int position) {
//        if (currentPage != 1) {
//            if (isNetworkConnected()) {
//                Flowable<LinkedList<PhotoItem>> flowable = unsplashService.getPhotosCurated(currentPage - 1, perPage, orderBy);
//                flowable
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(photos -> {
//                            Log.d("TAG", "WORK!");
//                            photoItemList = photos;
//                            ifViewAttached(view -> view.showContent(photoItemList));
//                            currentPage = currentPage -1;
//                        });
//            }
//            ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
//        }
    }
}
