package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidnetworking.error.ANError;
import com.jordanortiz.products_search_ml.core.data.manager.NetworkManager;
import com.jordanortiz.products_search_ml.core.presentation.mvp.presenter.BasePresenter;
import com.jordanortiz.products_search_ml.domain.interactor.product_list.GetProductsWithCategoryIdUseCase;
import com.jordanortiz.products_search_ml.domain.interactor.product_list.GetProductsWithQueryUseCase;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;
import com.jordanortiz.products_search_ml.presentation.mapper.ProductListMapper;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductListModel;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;


public class ProductsListViewModel extends BasePresenter  {

    private static final String TAG = ProductsListViewModel.class.getSimpleName();

    private MutableLiveData<ProductListModel> productList;
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<Boolean> networkStatus;
    private MutableLiveData<Integer> errorMessage;

    private final GetProductsWithQueryUseCase getProductsWithQueryUseCase;
    private final GetProductsWithCategoryIdUseCase getProductsWithCategoryIdUseCase;
    private final ProductListMapper productListMapper;

    private final NetworkManager networkManager;

    @Inject
    ProductsListViewModel(
            GetProductsWithQueryUseCase getProductsWithQueryUseCase,
            GetProductsWithCategoryIdUseCase getProductsWithCategoryIdUseCase,
            ProductListMapper productListMapper,
            NetworkManager networkManager) {
        this.getProductsWithQueryUseCase = getProductsWithQueryUseCase;
        this.getProductsWithCategoryIdUseCase = getProductsWithCategoryIdUseCase;
        this.productListMapper = productListMapper;
        this.networkManager = networkManager;

        productList = new MutableLiveData<>();
        errorMessage =  new MutableLiveData<>();
        networkStatus = new MutableLiveData<>();
        loading =  new MutableLiveData<>();
    }

    void onViewPrepared(String categoryId){
        if(productList != null){
            loadProductListByCategory(categoryId);
        }
    }


    @Override
    protected void onCleared() {
        getProductsWithQueryUseCase.dispose();
        getProductsWithCategoryIdUseCase.dispose();
        super.onCleared();
    }

    public void loadProductListByCategory(String categoryId) {
        if(networkManager.isNetworkAvailable()) {
            loading.setValue(Boolean.TRUE);
            networkStatus.setValue(Boolean.TRUE);
            this.getProductsWithCategoryIdUseCase.execute(
                    new GetProductsSingleObserver(),
                    GetProductsWithCategoryIdUseCase.Params.forProductsCategory(categoryId));
        }else {
            loading.setValue(Boolean.FALSE);
            networkStatus.setValue(Boolean.FALSE);
        }
    }

    void loadProductList(String q) {
        if(networkManager.isNetworkAvailable()) {
            loading.setValue(Boolean.TRUE);
            networkStatus.setValue(Boolean.TRUE);
            this.getProductsWithQueryUseCase.execute(
                    new GetProductsSingleObserver(),
                    GetProductsWithQueryUseCase.Params.forProductsQuery(q));
        }else {
            loading.setValue(Boolean.FALSE);
            networkStatus.setValue(Boolean.FALSE);
        }

    }

    LiveData<ProductListModel> getProductList() { return productList; }
    LiveData<Boolean> getLoading(){ return loading; }
    LiveData<Boolean> getNetworkStatus(){ return networkStatus; }
    LiveData<Integer> getErrorMessage(){ return errorMessage; }


    private void setProductListModel(ProductListModel productListModel){
        this.productList.setValue(productListModel);
    }


    private final class GetProductsSingleObserver extends DisposableSingleObserver<ProductsPagingEntity>{

        @Override
        public void onSuccess(ProductsPagingEntity productsPagingEntity) {
            ProductsListViewModel.this.errorMessage.setValue(null);
            ProductsListViewModel.this.loading.setValue(Boolean.FALSE);
            ProductsListViewModel.this.setProductListModel(
                    productListMapper.transformForList(productsPagingEntity)
            );
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.getMessage() );
            ProductsListViewModel.this.loading.setValue(Boolean.FALSE);
            int stringResource = handleApiError((ANError)e);
            ProductsListViewModel.this.errorMessage.setValue(stringResource);
        }
    }



}
