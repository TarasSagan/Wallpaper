package com.example.taras.wallpers.activity.loginActivity;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.example.taras.wallpers.R;
import com.example.taras.wallpers.api.authorization.AuthorizationManager;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class LoginPresenter extends MvpBasePresenter<LoginView>{
    private Context context;
    private AuthorizationManager authorizationManager;

    public LoginPresenter(Context context) {
        this.context = context;
        authorizationManager = new AuthorizationManager(context);
    }

    void loginOAuth(){
        if (isNetworkConnected()){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://unsplash.com/oauth/authorize"
                    + "?client_id=" + context.getString(R.string.AppID)
                    + "&scope=" + context.getString(R.string.App_access_scope)
                    + "&redirect_uri=" + context.getString(R.string.AppRedirectURI)
                    + "&response_type=code"));
            context.startActivity(intent);
        }else ifViewAttached(view -> view.onShowMessage(context.getString(R.string.no_internet)));
    }

    void publicAccess(){
        if (isNetworkConnected()){

        }else ifViewAttached(view -> view.onShowMessage(context.getString(R.string.no_internet)));
    }

    void getToken(Uri uri){
        String code;
        if(uri != null && uri.toString().startsWith(context.getString(R.string.AppRedirectURI))) {
            code = uri.getQueryParameter("code");
            authorizationManager.getToken(code);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }

}
