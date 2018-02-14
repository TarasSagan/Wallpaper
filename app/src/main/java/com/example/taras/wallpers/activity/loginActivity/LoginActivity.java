package com.example.taras.wallpers.activity.loginActivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.widget.Button;

import com.example.taras.wallpers.R;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView{
    @BindView(R.id.btnLogin) Button btnLogin;
    @BindView(R.id.btnPublicAccess) Button btnPublicAccess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void onLogin(){
    getPresenter().loginOAuth();

    }

    @OnClick(R.id.btnPublicAccess)
    public void onPublicAccess(){
        getPresenter().publicAccess();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getToken(getIntent().getData());
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    public void onShowMessage(String message) {
            Snackbar.make(btnLogin, message, Snackbar.LENGTH_LONG).show();
    }
}
