package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail;

import android.util.Log;

import com.jordanortiz.products_search_ml.core.presentation.mvp.presenter.BasePresenter;
import com.jordanortiz.products_search_ml.domain.interactor.product_detail.GetProductDetailByIdUseCase;
import com.jordanortiz.products_search_ml.domain.model.product.ProductEntity;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

@PerActivity
public class ProductDetailPresenter<V extends ProductDetailMvpView>
        extends BasePresenter<V> implements ProductDetailMvpPresenter<V> {

    private static final String TAG = ProductDetailPresenter.class.getSimpleName();

    private final GetProductDetailByIdUseCase getProductDetailByIdUseCase;

    @Inject
    public ProductDetailPresenter(GetProductDetailByIdUseCase getProductDetailByIdUseCase) {
        this.getProductDetailByIdUseCase = getProductDetailByIdUseCase;
    }

    @Override
    public void onViewPrepared(String productId) {
        this.getProductDetailByIdUseCase.execute(
                new GetProductsDetailSingleObserver(),
                GetProductDetailByIdUseCase.Params.forProductsDetail(productId));
    }

    private final class GetProductsDetailSingleObserver extends DisposableSingleObserver<ProductEntity> {

        @Override
        public void onSuccess(ProductEntity productEntity) {
            Log.e(TAG, "onSuccess: product" + productEntity.toString() );
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.getMessage() );
        }
    }
}
