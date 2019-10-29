package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import com.jordanortiz.products_search_ml.core.presentation.mvp.presenter.BasePresenter;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;

import javax.inject.Inject;

@PerActivity
public class ProductsListPresenter<V extends ProductsListMvpView>
        extends BasePresenter<V> implements ProductsListMvpPresenter<V> {

    @Inject
    public ProductsListPresenter() {
    }
}
