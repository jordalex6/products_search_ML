package com.jordanortiz.products_search_ml.data.repository;

import com.jordanortiz.products_search_ml.data.network.mercado_libre.MercadoLibreApiRest;
import com.jordanortiz.products_search_ml.data.network.mercado_libre.mapper.ProductEntityDataMapper;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;

import javax.inject.Inject;
import javax.inject.Singleton;


import io.reactivex.Single;


@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = AppDataManager.class.getSimpleName();

    private final MercadoLibreApiRest mercadoLibreApiRest;
    private final ProductEntityDataMapper mapper;

    @Inject
    public AppDataManager(MercadoLibreApiRest mercadoLibreApiRest, ProductEntityDataMapper mapper) {
        this.mercadoLibreApiRest = mercadoLibreApiRest;
        this.mapper = mapper;
    }

    @Override
    public Single<ProductsPagingEntity> getProductsDataWithQuery(String query) {
        return mercadoLibreApiRest.getProductsDataByQuery(query).map(mapper::transform);
    }
}
