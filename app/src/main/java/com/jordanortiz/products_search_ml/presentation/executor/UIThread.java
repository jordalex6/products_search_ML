package com.jordanortiz.products_search_ml.presentation.executor;

import com.jordanortiz.products_search_ml.domain.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
* MainThread (UI Thread) implementation based on a {@link Scheduler}
* which will execute actions on the Android UI thread
*/
@Singleton
public class UIThread implements PostExecutionThread {

    @Inject
    UIThread() {}

    @Override public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}

