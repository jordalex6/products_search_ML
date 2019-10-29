package com.jordanortiz.products_search_ml;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.jordanortiz.products_search_ml.presentation.di.component.ApplicationComponent;
import com.jordanortiz.products_search_ml.presentation.di.component.DaggerApplicationComponent;
import com.jordanortiz.products_search_ml.presentation.di.module.ApplicationModule;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class ProductSearchMLApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeFastNetWork();
        //this.initializeFabric();
        //this.initializeLeakDetection();
    }


    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

  /*  private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }*/

    private void initializeFastNetWork(){
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addNetworkInterceptor(interceptor)
                    .build();

            AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
        }else {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
            AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
        }

        //AndroidNetworking.setParserFactory(new JacksonParserFactory());
     /*   AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(com.androidnetworking.interceptors.HttpLoggingInterceptor.Level.BODY);
        }*/
    }

/*    private void initializeFabric() {
        Fabric.with(this, new Crashlytics());
    }*/

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
