package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail;

import com.jordanortiz.products_search_ml.core.presentation.mvp.view.MvpBaseView;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAttributeModel;

import java.util.List;

public interface ProductDetailMvpView extends MvpBaseView {

    void setUpHeaderInfoView(String info);
    void setUpThumbnailView(String thumbnailUrl);
    void setUpTitleView(String title);
    void setUpPriceView(String price);
    void setUpInstallmentsView(String installment, boolean hasInterest);
    void hideInstallmentsView();
    void setUpShippingView(boolean hasFreeShipping);
    void setUpAddressView(String fullAddress);
    void setUpAttributeListView(List<ProdDetailAttributeModel> attributeList);
}
