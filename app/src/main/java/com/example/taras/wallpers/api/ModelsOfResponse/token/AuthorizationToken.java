
package com.example.taras.wallpers.api.ModelsOfResponse.token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizationToken {

    public AuthorizationToken() {
    }

    public AuthorizationToken(String accessToken, String tokenType, String scope, long createdAt) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.scope = scope;
        this.createdAt = createdAt;
    }

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("created_at")
    @Expose
    private long createdAt;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

}
