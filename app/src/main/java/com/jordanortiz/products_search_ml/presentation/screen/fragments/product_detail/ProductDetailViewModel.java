package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jordanortiz.products_search_ml.core.presentation.mvp.presenter.BasePresenter;
import com.jordanortiz.products_search_ml.domain.interactor.product_detail.GetProductDetailByIdUseCase;
import com.jordanortiz.products_search_ml.domain.model.product.ProductEntity;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;
import com.jordanortiz.products_search_ml.presentation.mapper.ProductDetailMapper;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailModel;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;


public class ProductDetailViewModel extends BasePresenter{

    private static final String TAG = ProductDetailViewModel.class.getSimpleName();

    private MutableLiveData<ProdDetailModel> productDetail;

    private final GetProductDetailByIdUseCase getProductDetailByIdUseCase;
    private final ProductDetailMapper productDetailMapper;

    @Inject
    ProductDetailViewModel(
            GetProductDetailByIdUseCase getProductDetailByIdUseCase,
            ProductDetailMapper productDetailMapper) {
        this.getProductDetailByIdUseCase = getProductDetailByIdUseCase;
        this.productDetailMapper = productDetailMapper;

    }

    @Override
    protected void onCleared() {
        getProductDetailByIdUseCase.dispose();
        super.onCleared();
    }

    void onViewPrepared(String productId) {
        if (productDetail == null){
            productDetail = new MutableLiveData<>();
            loadProductDetail(productId);
        }
    }

    private void loadProductDetail(String productId) {
        this.getProductDetailByIdUseCase.execute(
                new GetProductsDetailSingleObserver(),
                GetProductDetailByIdUseCase.Params.forProductsDetail(productId));
    }

    LiveData<ProdDetailModel> getProductDetail(){
        return productDetail;
    }

    private void setProductDetail(ProdDetailModel productDetail) {
        this.productDetail.setValue(productDetail);
    }

    private final class GetProductsDetailSingleObserver extends DisposableSingleObserver<ProductEntity> {

        @Override
        public void onSuccess(ProductEntity productEntity) {
            Log.d(TAG, "onSuccess");
            ProductDetailViewModel.this.setProductDetail(
                    ProductDetailViewModel.this.productDetailMapper
                            .transformForProductDetail(productEntity)
                );
        }
        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.getMessage() );
        }
    }

}
