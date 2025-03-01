package com.jordanortiz.products_search_ml.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;
    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class PublicApiHeader {

       /* @Expose
        @SerializedName("api_key")
        private String mApiKey;*/

        @Inject
        public PublicApiHeader(/*@ApiInfo String apiKey*/) {
           /* mApiKey = apiKey;*/
        }

       /* public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }*/
    }

    public static final class ProtectedApiHeader {

       /* @Expose
        @SerializedName("api_key")
        private String mApiKey;*/

        @Expose
        @SerializedName("usuario")
        private String mUserId;

        @Expose
        @SerializedName("access_token")
        private String mAccessToken;

        public ProtectedApiHeader(/*String mApiKey, */String mUserId, String mAccessToken) {
           /* this.mApiKey = mApiKey;*/
            this.mUserId = mUserId;
            this.mAccessToken = mAccessToken;
        }

       /* public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }*/

        public String getUserId() {
            return mUserId;
        }

        public void setUserId(String mUserId) {
            this.mUserId = mUserId;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String accessToken) {
            mAccessToken = accessToken;
        }
    }
}
