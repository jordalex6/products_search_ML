package com.jordanortiz.products_search_ml.data.repository;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.jordanortiz.products_search_ml.data.network.CloudDataStoreApiHelper;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;

import javax.inject.Inject;
import javax.inject.Singleton;


import io.reactivex.Completable;


@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = AppDataManager.class.getSimpleName();

    private final CloudDataStoreApiHelper cloudApiHelper;

    @Inject
    public AppDataManager(CloudDataStoreApiHelper cloudApiHelper) {
        this.cloudApiHelper = cloudApiHelper;
    }

    @Override
    public Completable pushData() {
        return null;
    }
}
