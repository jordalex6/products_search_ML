package com.jordanortiz.products_search_ml.presentation.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jordanortiz.products_search_ml.presentation.di.scope.ViewModelKey;
import com.jordanortiz.products_search_ml.presentation.screen.ViewModelFactory;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.home.HomeViewModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.ProductDetailViewModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductsListViewModel.class)
    abstract ViewModel bindProductsListViewModel(ProductsListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel.class)
    abstract ViewModel bindProductDetailViewModel(ProductDetailViewModel viewModel);
}
