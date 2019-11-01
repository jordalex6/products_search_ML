package com.jordanortiz.products_search_ml.presentation.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jordanortiz.products_search_ml.presentation.screen.fragments.home.HomeMvpPresenter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.home.HomeMvpView;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.home.HomePresenter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.ProductDetailAtributteRecViewAdapter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.ProductDetailMvpPresenter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.ProductDetailMvpView;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.ProductDetailPresenter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAttributeModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListMvpPresenter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListMvpView;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListPresenter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListRecyclerViewAdapter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel;


import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private final AppCompatActivity mActivity;

    public ViewModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    Context provideContext() {
        return mActivity;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    /* startBlock: Home fragment */
    @Provides
    HomeMvpPresenter<HomeMvpView> provideHomePresenter(
            HomePresenter<HomeMvpView> presenter) {
        return presenter;
    }

    /* endBlock: Home fragment */

    /* startBlock: Products List fragment */
    @Provides
    ProductsListMvpPresenter<ProductsListMvpView> provideProductsListPresenter(
            ProductsListPresenter<ProductsListMvpView> presenter) {
        return presenter;
    }

    @Provides
    ProductsListRecyclerViewAdapter provideProductsListRecyclerViewAdapter(Context context){
        return new ProductsListRecyclerViewAdapter(new ArrayList<ProductModel>(), context);
    }
    /* endBlock: Products List fragment */

    /* startBlock: Products Detail fragment */
    @Provides
    ProductDetailMvpPresenter<ProductDetailMvpView> provideProductDetailPresenter(
            ProductDetailPresenter<ProductDetailMvpView> presenter) {
        return presenter;
    }

    @Provides
    ProductDetailAtributteRecViewAdapter provideProductDetailAtributteRecViewAdapter (Context context){
        return new ProductDetailAtributteRecViewAdapter (context, new ArrayList<ProdDetailAttributeModel>());
    }

    /* endBlock: Products Detail fragment */



}
