package com.jordanortiz.products_search_ml.domain.interactor.base;

import android.util.Log;

import com.jordanortiz.products_search_ml.domain.executor.PostExecutionThread;
import com.jordanortiz.products_search_ml.domain.executor.ThreadExecutor;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseReactiveUseCase{

    protected final ThreadExecutor threadExecutor;
    protected final PostExecutionThread postExecutionThread;
    private CompositeDisposable disposables;

    protected BaseReactiveUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
            disposables = null;
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    protected void addDisposable(Disposable disposable) {
        if(this.disposables == null)
            this.disposables = new CompositeDisposable();
       /* Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);*/
        disposables.add(disposable);
    }
}
