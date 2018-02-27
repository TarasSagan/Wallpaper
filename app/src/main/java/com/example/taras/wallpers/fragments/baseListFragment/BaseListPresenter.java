package com.example.taras.wallpers.fragments.baseListFragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.taras.wallpers.App;
import com.example.taras.wallpers.R;
import com.example.taras.wallpers.api.ModelsOfResponse.likeResponce.LikeResponse;
import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpers.api.UnsplashService;
import com.example.taras.wallpers.fragments.ListFragmentContract;
import com.example.taras.wallpers.repository.SharedPreferences.TokenManager;
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
    @Inject Context context;
    @Inject TokenManager tokenManager;
    @Inject
    public UnsplashService unsplashService;

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

    }

    @Override
    public void onDownload(PhotoItem item) {

    }

    @Override
    public void onShowPhotoDetails(PhotoItem item) {

    }

    @Override
    public void getNextData() {
        if(isNetworkConnected()) {
            onLoadNextPhotos(currentPage, perPage);
            currentPage++;
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
    }
}
