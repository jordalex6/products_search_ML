package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidnetworking.error.ANError;
import com.jordanortiz.products_search_ml.core.presentation.mvp.presenter.BasePresenter;
import com.jordanortiz.products_search_ml.domain.interactor.product_list.GetProductsWithCategoryIdUseCase;
import com.jordanortiz.products_search_ml.domain.interactor.product_list.GetProductsWithQueryUseCase;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;
import com.jordanortiz.products_search_ml.presentation.mapper.ProductListMapper;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ListPagingModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductListModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

@PerActivity
public class ProductsListViewModel extends BasePresenter  {

    private static final String TAG = ProductsListViewModel.class.getSimpleName();

    private MutableLiveData<List<ProductModel>> productList;
    private MutableLiveData<ListPagingModel> listPaging;
    private MutableLiveData<Integer> errorMessage;

    private final GetProductsWithQueryUseCase getProductsWithQueryUseCase;
    private final GetProductsWithCategoryIdUseCase getProductsWithCategoryIdUseCase;
    private final ProductListMapper productListMapper;

    @Inject
    ProductsListViewModel(
            GetProductsWithQueryUseCase getProductsWithQueryUseCase,
            GetProductsWithCategoryIdUseCase getProductsWithCategoryIdUseCase,
            ProductListMapper productListMapper) {
        this.getProductsWithQueryUseCase = getProductsWithQueryUseCase;
        this.getProductsWithCategoryIdUseCase = getProductsWithCategoryIdUseCase;
        this.productListMapper = productListMapper;
    }

    void onViewPrepared(String categoryId){
        if(productList == null && listPaging == null){
            productList = new MutableLiveData<>();
            listPaging = new MutableLiveData<>();
            errorMessage =  new MutableLiveData<>();
            loadProductListByCategory(categoryId);
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        getProductsWithQueryUseCase.dispose();
        getProductsWithCategoryIdUseCase.dispose();
    }

    private void loadProductListByCategory(String categoryId) {
        this.getProductsWithCategoryIdUseCase.execute(
                new GetProductsSingleObserver(),
                GetProductsWithCategoryIdUseCase.Params.forProductsCategory(categoryId));
    }

    void loadProductList(String q) {
        this.getProductsWithQueryUseCase.execute(
                new GetProductsSingleObserver(),
                GetProductsWithQueryUseCase.Params.forProductsQuery(q));

    }

    LiveData<List<ProductModel>> getProductList() {
        return productList;
    }

    LiveData<ListPagingModel> getListPaging() {
        return listPaging;
    }

    LiveData<Integer> getErrorMessage(){ return errorMessage; }


    private void setProductListModel(ProductListModel productListModel){
        this.listPaging.setValue(productListModel.getPaging());
        this.productList.setValue(productListModel.getProductList());
        this.errorMessage.setValue(null);
    }

    private void setUpErrorMessage(int stringResource) {
        this.errorMessage.setValue(stringResource);
    }

    private final class GetProductsSingleObserver extends DisposableSingleObserver<ProductsPagingEntity>{

        @Override
        public void onSuccess(ProductsPagingEntity productsPagingEntity) {
            ProductsListViewModel.this.setProductListModel(
                    productListMapper.transformForList(productsPagingEntity)
            );
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.getMessage() );
            int stringResource = handleApiError((ANError)e);
            ProductsListViewModel.this.setUpErrorMessage(stringResource);
        }
    }



}
