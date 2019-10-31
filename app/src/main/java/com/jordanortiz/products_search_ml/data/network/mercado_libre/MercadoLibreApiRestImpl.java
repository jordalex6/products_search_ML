package com.jordanortiz.products_search_ml.data.network.mercado_libre;

import com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw.ProductsResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class MercadoLibreApiRestImpl implements MercadoLibreApiRest {

    private static final String TAG = MercadoLibreApiRestImpl.class.getSimpleName();

    /**
     * Construct a {@link MercadoLibreApiRest} based on connections to the api (Cloud).
     */
    @Inject
    public MercadoLibreApiRestImpl() {

    }

    @Override
    public Single<ProductsResponse> getProductsDataByQuery(String query) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_PRODUCTS_BY_QUERY)
                .addQueryParameter("q",query)
                .build()
                .getObjectSingle(ProductsResponse.class);
    }
}
