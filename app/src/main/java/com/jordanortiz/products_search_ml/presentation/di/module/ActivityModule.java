package com.jordanortiz.products_search_ml.presentation.di.module;

import android.app.Activity;
import android.content.Context;


import androidx.appcompat.app.AppCompatActivity;

import com.jordanortiz.products_search_ml.presentation.di.scope.ActivityContext;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {
    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }


    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }




}