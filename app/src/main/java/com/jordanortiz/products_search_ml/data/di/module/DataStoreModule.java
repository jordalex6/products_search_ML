package com.jordanortiz.products_search_ml.data.di.module;

import com.jordanortiz.products_search_ml.data.network.ApiHeader;
import com.jordanortiz.products_search_ml.data.network.CloudDataStoreApiHelper;
import com.jordanortiz.products_search_ml.data.network.CloudDataStoreApiHelperImpl;
import com.jordanortiz.products_search_ml.data.repository.AppDataManager;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataStoreModule {

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager){
        return appDataManager;
    }


    @Provides
    @Singleton
    CloudDataStoreApiHelper provideCloudDataStoreHelper(CloudDataStoreApiHelperImpl cloudDataStoreApiHelper){
        return cloudDataStoreApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader() {
        return new ApiHeader.ProtectedApiHeader(
                "",
                "");
    }
}
