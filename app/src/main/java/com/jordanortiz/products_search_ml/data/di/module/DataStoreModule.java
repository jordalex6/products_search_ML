package com.jordanortiz.products_search_ml.data.di.module;

import com.jordanortiz.products_search_ml.data.network.ApiHeader;
import com.jordanortiz.products_search_ml.data.network.mercado_libre.MercadoLibreApiRest;
import com.jordanortiz.products_search_ml.data.network.mercado_libre.MercadoLibreApiRestImpl;
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
    MercadoLibreApiRest provideMercadoLibreApiRest(MercadoLibreApiRestImpl mercadoLibreApiRest){
        return mercadoLibreApiRest;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader() {
        return new ApiHeader.ProtectedApiHeader(
                "",
                "");
    }
}
