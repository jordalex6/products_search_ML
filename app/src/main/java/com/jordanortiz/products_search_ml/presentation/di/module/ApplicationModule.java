package com.jordanortiz.products_search_ml.presentation.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import com.jordanortiz.products_search_ml.data.executor.JobExecutor;
import com.jordanortiz.products_search_ml.data.repository.AppDataManager;
import com.jordanortiz.products_search_ml.domain.executor.PostExecutionThread;
import com.jordanortiz.products_search_ml.domain.executor.ThreadExecutor;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;
import com.jordanortiz.products_search_ml.presentation.di.ApplicationContext;

import com.jordanortiz.products_search_ml.presentation.executor.UIThread;


import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application){
        this.mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }


    @Provides
    @Singleton
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

/*    @Provides
    @Singleton
    NetworkManager provideNetworkManager(NetworkManagerImpl networkManager){ return networkManager; }

    @Provides
    @Singleton
    NetworkStateReceiver provideNetWorkStateReceiver(NetworkStateReceiver networkStateReceiver){
        return networkStateReceiver;
    }*/

/*    @Provides
    @Singleton
    PermissionManager providesPermissionManager(){
        return new PermissionManager();
    }*/

}
