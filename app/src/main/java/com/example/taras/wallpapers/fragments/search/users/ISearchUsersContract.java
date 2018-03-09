package com.example.taras.wallpapers.fragments.search.users;



import com.example.taras.wallpapers.api.ModelsOfResponse.search.users.User;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import java.util.List;

public interface ISearchUsersContract {
    public interface View extends MvpView{
        void showMessage(String message);
        void showContent(List<User> list);
        void removeContent();

    }
    public interface Presenter{
        void getNextData();
        void onShowUser(User item);
    }
}
