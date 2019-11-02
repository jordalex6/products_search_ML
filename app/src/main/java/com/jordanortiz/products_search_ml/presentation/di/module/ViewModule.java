package com.jordanortiz.products_search_ml.presentation.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.ProductDetailAttributeRecViewAdapter;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAttributeModel;
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

    /* startBlock: Products List fragment */
    @Provides
    ProductsListRecyclerViewAdapter provideProductsListRecyclerViewAdapter(Context context){
        return new ProductsListRecyclerViewAdapter(new ArrayList<ProductModel>(), context);
    }
    /* endBlock: Products List fragment */

    /* startBlock: Products Detail fragment */
    @Provides
    ProductDetailAttributeRecViewAdapter provideProductDetailAttributeRecViewAdapter (Context context){
        return new ProductDetailAttributeRecViewAdapter(context, new ArrayList<ProdDetailAttributeModel>());
    }
    /* endBlock: Products Detail fragment */



}
