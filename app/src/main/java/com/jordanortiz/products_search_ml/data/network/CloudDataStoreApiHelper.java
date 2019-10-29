package com.jordanortiz.products_search_ml.data.network;


import java.util.List;

import io.reactivex.Single;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface CloudDataStoreApiHelper {

    Single<Response> pushScorpionData();
}
