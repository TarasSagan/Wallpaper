package com.example.taras.wallpapers.fragments.baseListFragment;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.example.taras.wallpapers.App;
import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.activity.photoDetails.PhotoDetailsActivity;
import com.example.taras.wallpapers.activity.userProfile.UserProfileActivity;
import com.example.taras.wallpapers.api.ModelsOfResponse.likeResponse.LikeResponse;
import com.example.taras.wallpapers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpapers.api.UnsplashService;
import com.example.taras.wallpapers.repository.SharedPreferences.TokenManager;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseListPresenter extends MvpBasePresenter<ListFragmentContract.FragmentView>
        implements ListFragmentContract.FragmentPresenter {
    private List<PhotoItem> photoItemList;
    public String orderBy = "popular";
    private int currentPage = 1;
    private int perPage = 10;
    @Inject DownloadManager downloadManager;
    @Inject Context context;
    @Inject TokenManager tokenManager;
    @Inject public UnsplashService unsplashService;

    public BaseListPresenter() {
        App.getComponent().inject(this);
    }
    public abstract void onLoadNextPhotos(int currentPage, int perPage);

    public void onShowNextPhotos(List<PhotoItem> list){
//        if(photoItemList == null){
//            photoItemList = new ArrayList<>();
//        }
//        photoItemList.addAll(list);
        ifViewAttached(view -> view.showContent(list));
    }

    @Override
    public void onLike(PhotoItem item, int position) {
        if ( tokenManager.containsToken()) {
            if (isNetworkConnected()) {
                if (item.isLikedByUser()) {
                    Flowable<LikeResponse> flowable = unsplashService.postUnlike(item.getId());
                    flowable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response ->
                                    photoItemList.get(position).setLikedByUser(response.getPhoto().isLikedByUser()));
                } else {
                    Flowable<LikeResponse> flowable = unsplashService.postLike(item.getId());
                    flowable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response ->
                                    photoItemList.get(position).setLikedByUser(response.getPhoto().isLikedByUser()));
                }
            } else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }

    @Override
    public void onShowUser(PhotoItem item) {
        if (isNetworkConnected()) {
            String username = item.getUser().getUsername();
            Intent intent = new Intent(context, UserProfileActivity.class);
            intent.putExtra(UserProfileActivity.USERNAME_KEY, username);
            context.startActivity(intent);
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
    }

    @Override
    public void onDownload(PhotoItem item) {
        if (isNetworkConnected()) {
            Uri uri = Uri.parse(item.getUrls().getRaw());
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            Long reference = downloadManager.enqueue(request);
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
}

    @Override
    public void onShowPhotoDetails(PhotoItem item) {
        if (isNetworkConnected()) {
            String photoID = item.getId();
            Intent intent = new Intent(context, PhotoDetailsActivity.class);
            intent.putExtra(PhotoDetailsActivity.DETAIL_KEY, photoID);
            context.startActivity(intent);
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
    }

    @Override
    public void getNextData() {
        if(isNetworkConnected()) {
            onLoadNextPhotos(currentPage, perPage);
            currentPage++;
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
    }
    public void resetByDefault(){
        orderBy = "popular";
        currentPage = 1;
        perPage = 10;
    }
}
