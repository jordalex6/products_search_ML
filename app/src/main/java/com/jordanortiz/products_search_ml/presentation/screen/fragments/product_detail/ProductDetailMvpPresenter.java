package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail;

import com.jordanortiz.products_search_ml.core.presentation.mvp.presenter.MvpBasePresenter;

public interface ProductDetailMvpPresenter<V extends ProductDetailMvpView>
        extends MvpBasePresenter<V> {

    void onViewPrepared(String productId);
}
