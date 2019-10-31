package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import android.util.Log;

import com.jordanortiz.products_search_ml.core.presentation.mvp.presenter.BasePresenter;
import com.jordanortiz.products_search_ml.domain.interactor.product_search.GetProductsWithQueryUseCase;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductInstallmentsModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

@PerActivity
public class ProductsListPresenter<V extends ProductsListMvpView>
        extends BasePresenter<V> implements ProductsListMvpPresenter<V> {

    private static final String TAG = ProductsListPresenter.class.getSimpleName();

    private final GetProductsWithQueryUseCase getProductsWithQueryUseCase;

    @Inject
    public ProductsListPresenter(GetProductsWithQueryUseCase getProductsWithQueryUseCase) {
        this.getProductsWithQueryUseCase = getProductsWithQueryUseCase;
    }


    @Override
    public void onViewPrepared() {
//        createProductListDummy();
    }

    @Override
    public void applyQueryOfProduct(String query) {
        if(isQueryEmpty(query)){
            getMvpView().showMsgQueryIsEmpty();
        }else{
            this.getProductsWithQueryUseCase.execute(
                    new GetProductsWithQuerySingleObserver(),
                    GetProductsWithQueryUseCase.Params.forProductsQuery(query));
        }
    }

    private void applyListOfProductObtained(List<ProductModel> productList){
        if(productList.size() > 0)
            getMvpView().showProductList(productList);

        // TODO else -> add empty product list

    }

    boolean isQueryEmpty(String query) {
        return query.isEmpty();
    }

    private void createProductListDummy(){
        List<ProductModel> productModelList = new ArrayList<>();

        ProductModel prod1 = new ProductModel();
        prod1.setId("MLA821315858");
        prod1.setTitle("Motorola G7 64 Gb Ceramic Black 4 Gb Ram");
        prod1.setPrice(17899);
        prod1.setShipping(true);
        prod1.setThumbnailImg("http://mla-s1-p.mlstatic.com/780760-MLA31818439856_082019-I.jpg/");
        prod1.setProductInstallments(new ProductInstallmentsModel(12,1491.58,0));

        ProductModel prod2 = new ProductModel();
        prod2.setId("MLA818016782");
        prod2.setTitle("Motorola G7 Power Dual Sim 64 Gb Marine Blue");
        prod2.setPrice(23990);
        prod2.setShipping(true);
        prod2.setThumbnailImg("http://mla-s1-p.mlstatic.com/743639-MLA31002769310_062019-I.jpg/");
        prod2.setProductInstallments(new ProductInstallmentsModel(12,3274.04,63.77));

        ProductModel prod3 = new ProductModel();
        prod3.setId("MLA818707298");
        prod3.setTitle("Motorola G6 Dual Sim 32 Gb Índigo Oscuro");
        prod3.setPrice(17100);
        prod3.setShipping(false);
        prod3.setThumbnailImg("http://mla-s1-p.mlstatic.com/708400-MLA31003470610_062019-I.jpg/");
        prod3.setProductInstallments(new ProductInstallmentsModel(12,2333.72,63.77));

        productModelList.add(prod1);
        productModelList.add(prod2);
        productModelList.add(prod3);

        this.applyListOfProductObtained(productModelList);
    }

    private final class GetProductsWithQuerySingleObserver extends DisposableSingleObserver<ProductsPagingEntity>{

        @Override
        public void onSuccess(ProductsPagingEntity productsPagingEntity) {
            Log.e(TAG, "onSuccess: paging" + productsPagingEntity.getPaging().toString() );
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.getMessage() );
        }
    }


}
