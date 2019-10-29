package com.jordanortiz.products_search_ml.data.network;

import com.jordanortiz.products_search_ml.data.network.Response;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class CloudDataStoreApiHelperImpl implements CloudDataStoreApiHelper {

    private static final String TAG = CloudDataStoreApiHelperImpl.class.getSimpleName();

    //private final ApiHeader mApiHeader;

    /**
     * Construct a {@link CloudDataStoreApiHelper} based on connections to the api (Cloud).
     *
     * @param mApiHeader The {@link ApiHeader} implementation to use.
     */
    @Inject
    public CloudDataStoreApiHelperImpl(ApiHeader mApiHeader) {
        //this.mApiHeader = mApiHeader;
    }


    @Override
    public Single<Response> pushScorpionData() {
        return null;
    }
}
