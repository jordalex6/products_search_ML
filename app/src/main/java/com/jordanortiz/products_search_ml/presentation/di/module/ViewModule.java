package com.jordanortiz.products_search_ml.presentation.di.module;

import android.content.Context;

import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.home.HomeMvpPresenter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.home.HomeMvpView;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.home.HomePresenter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListMvpPresenter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListMvpView;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListPresenter;


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
    /* endBlock: Products List fragment */



}
