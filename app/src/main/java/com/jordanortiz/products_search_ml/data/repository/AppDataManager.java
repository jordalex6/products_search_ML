package com.jordanortiz.products_search_ml.data.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.jordanortiz.products_search_ml.data.exception.ProductNotFoundException;
import com.jordanortiz.products_search_ml.data.network.mercado_libre.MercadoLibreApiRest;
import com.jordanortiz.products_search_ml.data.network.mercado_libre.mapper.ProductEntityDataMapper;
import com.jordanortiz.products_search_ml.data.preferences.PreferencesHelper;
import com.jordanortiz.products_search_ml.domain.model.product.ProductEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;

import java.util.List;
import java.util.concurrent.Callable;

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
     * from ML server
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

    @Override
    public Single<ProductsPagingEntity> getProductsDataWithCategoryId(String categoryId) {
        return mercadoLibreApiRest.getProductsDataByCategoryId(categoryId)
                .map(mapper::transform)
                .doOnSuccess(this::updateLocalStoreWithProductsObtained);
    }

    /**
     * get product detail for id passed by parameters
     * from local storage
     *
     * @param productId String.
     * @return {@link Single<ProductEntity>}.
     */
    @Override
    public Single<ProductEntity> getProductsDetailById(String productId) {
        return Single.fromCallable(new Callable<ProductEntity>() {
            @Override
            public ProductEntity call() throws Exception {
//                searching product
                ProductEntity productEntity = searchProductById(productId);
                if(productEntity == null)
                    throw new ProductNotFoundException("No se pudo obtener detalle del producto");
                return productEntity;
            }
        });
    }

    private ProductEntity searchProductById(String id) {
        List<ProductEntity> productEntityList =
                getCurrentProductsObtainedFromLocalStore().getProductList();

        ProductEntity productEntity = null;

        for (ProductEntity product : productEntityList) {
            if(product.getId().equals(id)){
                productEntity = product;
                break;
            }
        }
        return productEntity;
    }

    private void updateLocalStoreWithProductsObtained(ProductsPagingEntity productsPagingEntity) {
        mPreferencesHelper.setCurrentProductsResponse(new Gson().toJson(productsPagingEntity));
    }

    private ProductsPagingEntity getCurrentProductsObtainedFromLocalStore(){
        String json = mPreferencesHelper.getCurrentProductsResponse();
        return new Gson().fromJson(json, ProductsPagingEntity.class);
    }
}
