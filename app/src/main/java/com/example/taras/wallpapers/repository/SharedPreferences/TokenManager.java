package com.example.taras.wallpapers.repository.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.taras.wallpapers.api.ModelsOfResponse.token.AuthorizationToken;
import static android.content.Context.MODE_PRIVATE;

public class TokenManager {
    private String KEY_AccessToken = "accessToken";
    private String KEY_TokenType = "tokenType";
    private String KEY_Scope = "scope";
    private String KEY_CreatedAt = "createdAt";
    private String EmptyValue = "empty value";
    private String NAME = "Token";
    private SharedPreferences sharedPreferences;


    public TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE_PRIVATE);
    }

    public boolean containsToken(){
       return sharedPreferences.contains(KEY_AccessToken);
    }
    public AuthorizationToken getToken(){
        return new AuthorizationToken(
                sharedPreferences.getString(KEY_AccessToken, EmptyValue),
                sharedPreferences.getString(KEY_TokenType, EmptyValue),
                sharedPreferences.getString(KEY_Scope, EmptyValue),
                sharedPreferences.getLong(KEY_CreatedAt, 0)
                );
    }

    public void setToken(AuthorizationToken authorizationToken){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_AccessToken, authorizationToken.getAccessToken());
        editor.putString(KEY_TokenType, authorizationToken.getTokenType());
        editor.putString(KEY_Scope, authorizationToken.getScope());
        editor.putLong(KEY_CreatedAt, authorizationToken.getCreatedAt());
        editor.apply();
    }

    public void removeToken(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
