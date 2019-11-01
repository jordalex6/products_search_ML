package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail;

import android.util.Log;

import com.jordanortiz.products_search_ml.core.presentation.mvp.presenter.BasePresenter;
import com.jordanortiz.products_search_ml.domain.interactor.product_detail.GetProductDetailByIdUseCase;
import com.jordanortiz.products_search_ml.domain.model.product.ProductAttributeEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductEntity;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;
import com.jordanortiz.products_search_ml.presentation.mapper.ProductDetailMapper;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAddressModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAttributeModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailInstallmentsModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

@PerActivity
public class ProductDetailPresenter<V extends ProductDetailMvpView>
        extends BasePresenter<V> implements ProductDetailMvpPresenter<V> {

    private static final String TAG = ProductDetailPresenter.class.getSimpleName();

    private final GetProductDetailByIdUseCase getProductDetailByIdUseCase;
    private final ProductDetailMapper productDetailMapper;

    @Inject
    public ProductDetailPresenter(
            GetProductDetailByIdUseCase getProductDetailByIdUseCase,
            ProductDetailMapper productDetailMapper) {
        this.getProductDetailByIdUseCase = getProductDetailByIdUseCase;
        this.productDetailMapper = productDetailMapper;
    }

    @Override
    public void onViewPrepared(String productId) {
        this.getProductDetailByIdUseCase.execute(
                new GetProductsDetailSingleObserver(),
                GetProductDetailByIdUseCase.Params.forProductsDetail(productId));
    }

    private void setUpViewWithDataObtained(ProdDetailModel productDetail) {
//        set up condition | sold quantity | available quantity
        String headerInfo = buildHeaderInfo(
                productDetail.getConditionType(),
                productDetail.getSoldQuantity(),
                productDetail.getAvailableQuantity()
                );
        getMvpView().setUpHeaderInfoView(headerInfo);
//        set up thumbnail url
        String thumbnailUrl = productDetail.getThumbnailImg();
        getMvpView().setUpThumbnailView(thumbnailUrl);
//        set up title
        getMvpView().setUpTitleView(productDetail.getTitle());
        getMvpView().setUpPriceView("$ " + productDetail.getPrice());
//        set up installments
        if(productDetail.getInstallments() != null) {
            String installments = buildInstalmentsInfo(productDetail.getInstallments());
            boolean hasInterest = productDetail.getInstallments().getRate() > 0;
            getMvpView().setUpInstallmentsView(installments, hasInterest);
        }else{
            getMvpView().hideInstallmentsView();
        }
//        set up shipping
        boolean hasFreeShipping = productDetail.isShipping();
        getMvpView().setUpShippingView(hasFreeShipping);
//        set up full address
        String fullAddress = buildFullAddressInfo(productDetail.getAddressModel());
        getMvpView().setUpAddressView(fullAddress);
//        set up attributes list
        List<ProdDetailAttributeModel> attributeList = productDetail.getAttributeModelList();
        getMvpView().setUpAttributeListView(attributeList);
    }

    private String buildFullAddressInfo(ProdDetailAddressModel addressModel) {
        return addressModel.getStateName() + " - " + addressModel.getCityName();
    }

    private String buildInstalmentsInfo(ProdDetailInstallmentsModel installmentsModel) {
        StringBuilder installments = new StringBuilder();
        if (installmentsModel.getRate() > 0)
            installments.append("Pagá en hasta ").append(installmentsModel.getQuantity()).append(" cuotas");
        else
            installments.append("Pagá en hasta ").append(installmentsModel.getQuantity()).append(" cuotas sin interés");

        return installments.toString();
    }

    private String buildHeaderInfo(String conditionType, int soldQuantity, Integer availableQuantity) {
        StringBuilder headerInfo = new StringBuilder();
        switch (conditionType){
            case "new":
                headerInfo.append("Nuevo | ");
                break;
            case "used":
                headerInfo.append("Usado | ");
                break;
        }

        if (soldQuantity == 1)
            headerInfo.append(soldQuantity).append(" vendido | ");
        else if (soldQuantity > 1)
            headerInfo.append(soldQuantity).append(" vendidos | ");

        if (availableQuantity == 1)
            headerInfo.append(availableQuantity).append(" disponible");
        else if (availableQuantity > 1)
            headerInfo.append(availableQuantity).append(" disponibles");

        return headerInfo.toString();
    }

    private final class GetProductsDetailSingleObserver extends DisposableSingleObserver<ProductEntity> {

        @Override
        public void onSuccess(ProductEntity productEntity) {
            Log.d(TAG, "onSuccess");
            ProductDetailPresenter.this.setUpViewWithDataObtained(
                    ProductDetailPresenter.this.productDetailMapper
                            .transformForProductDetail(productEntity)
                );
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.getMessage() );
        }
    }

}
