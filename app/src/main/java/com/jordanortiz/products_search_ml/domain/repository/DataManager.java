package com.jordanortiz.products_search_ml.domain.repository;



import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface DataManager{

    Completable pushData();


}
