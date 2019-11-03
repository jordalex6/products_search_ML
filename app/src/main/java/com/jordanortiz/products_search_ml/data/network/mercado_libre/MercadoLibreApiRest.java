package com.jordanortiz.products_search_ml.data.network.mercado_libre;


import com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw.ProductsResponse;

import io.reactivex.Single;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface MercadoLibreApiRest {

    Single<ProductsResponse> getProductsDataByQuery(String query);

    Single<ProductsResponse> getProductsDataByCategoryId(String categoryId);
}
