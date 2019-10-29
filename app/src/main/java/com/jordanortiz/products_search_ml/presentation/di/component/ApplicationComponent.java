package com.jordanortiz.products_search_ml.presentation.di.component;

import android.content.Context;

import javax.inject.Singleton;

//import com.itnnovar.capturescorpion.core.data.manager.NetworkManager;
//import com.itnnovar.capturescorpion.core.utilities.NetworkStateReceiver;

import com.jordanortiz.products_search_ml.ProductSearchMLApplication;
import com.jordanortiz.products_search_ml.data.di.module.DataStoreModule;
import com.jordanortiz.products_search_ml.domain.executor.PostExecutionThread;
import com.jordanortiz.products_search_ml.domain.executor.ThreadExecutor;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;
import com.jordanortiz.products_search_ml.presentation.di.ApplicationContext;
import com.jordanortiz.products_search_ml.presentation.di.module.ApplicationModule;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

@Singleton
@Component(modules =
        {
                ApplicationModule.class,
                DataStoreModule.class,
        })
public interface ApplicationComponent {


    void inject(ProductSearchMLApplication app);

    @ApplicationContext
    Context context();
    //NetworkManager networkManager();
    //NetworkStateReceiver networkStateReceiver();
    CompositeDisposable compositeDisposable();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();

    DataManager appDataManager();


}