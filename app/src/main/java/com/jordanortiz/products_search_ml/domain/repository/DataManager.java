package com.jordanortiz.products_search_ml.domain.repository;



import com.jordanortiz.products_search_ml.domain.model.product.ProductEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;

import io.reactivex.Single;

public interface DataManager{

    Single<ProductsPagingEntity> getProductsDataWithQuery(String query);
    Single<ProductsPagingEntity> getProductsDataWithCategoryId(String categoryId);
    Single<ProductEntity> getProductsDetailById(String id);


}
