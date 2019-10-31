package com.jordanortiz.products_search_ml.data.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.jordanortiz.products_search_ml.data.network.mercado_libre.MercadoLibreApiRest;
import com.jordanortiz.products_search_ml.data.network.mercado_libre.mapper.ProductEntityDataMapper;
import com.jordanortiz.products_search_ml.data.preferences.PreferencesHelper;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;

import javax.inject.Inject;
import javax.inject.Singleton;


import io.reactivex.Single;


@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = AppDataManager.class.getSimpleName();

    private final PreferencesHelper mPreferencesHelper;
    private final MercadoLibreApiRest mercadoLibreApiRest;
    private final ProductEntityDataMapper mapper;

    @Inject
    public AppDataManager(PreferencesHelper mPreferencesHelper,
                          MercadoLibreApiRest mercadoLibreApiRest,
                          ProductEntityDataMapper mapper) {
        this.mPreferencesHelper = mPreferencesHelper;
        this.mercadoLibreApiRest = mercadoLibreApiRest;
        this.mapper = mapper;
    }

    /**
     * get a list of products and paging information for the query passed by parameters
     *
     * @param query String.
     * @return {@link Single<ProductsPagingEntity>}.
     */
    @Override
    public Single<ProductsPagingEntity> getProductsDataWithQuery(String query) {
        return mercadoLibreApiRest.getProductsDataByQuery(query)
                .map(mapper::transform)
                .doOnSuccess(this::updateLocalStoreWithProductsObtained);
    }

    private void updateLocalStoreWithProductsObtained(ProductsPagingEntity productsPagingEntity) {
        mPreferencesHelper.setCurrentProductsResponse(new Gson().toJson(productsPagingEntity));
    }

    private void getCurrentProductsObtainedFromLocalStore(){
        String json = mPreferencesHelper.getCurrentProductsResponse();
    }
}
