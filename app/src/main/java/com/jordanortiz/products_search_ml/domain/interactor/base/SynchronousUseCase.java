package com.jordanortiz.products_search_ml.domain.interactor.base;

public interface SynchronousUseCase<T, Params> {

    T execute(Params params);

    }
