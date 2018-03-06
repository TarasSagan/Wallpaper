package com.example.taras.wallpapers.api.authorization;


import com.example.taras.wallpapers.api.ModelsOfResponse.token.AuthorizationToken;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthorizationService {
    @POST("oauth/token")
    @FormUrlEncoded
    Flowable<AuthorizationToken> getToken(@Field("client_id") String clientID,
                                          @Field("client_secret") String clientSecret,
                                          @Field("redirect_uri") String redirectURI,
                                          @Field("code") String code,
                                          @Field("grant_type") String grantType);
}
