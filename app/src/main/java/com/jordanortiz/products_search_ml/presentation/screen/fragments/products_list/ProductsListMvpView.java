package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import com.jordanortiz.products_search_ml.core.presentation.mvp.view.MvpBaseView;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel;

import java.util.List;

public interface ProductsListMvpView extends MvpBaseView {
    void showMsgQueryIsEmpty();

    void showProductList(List<ProductModel> productList);
}
