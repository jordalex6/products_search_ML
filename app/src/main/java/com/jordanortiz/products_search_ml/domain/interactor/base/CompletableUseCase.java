package com.jordanortiz.products_search_ml.domain.interactor.base;

import com.jordanortiz.products_search_ml.domain.executor.PostExecutionThread;
import com.jordanortiz.products_search_ml.domain.executor.ThreadExecutor;
import com.jordanortiz.products_search_ml.domain.interactor.base.BaseReactiveUseCase;

import io.reactivex.Completable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a [DisposableCompletableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * This use case is to be used when we expect no value to be emitted but rather for an action to be completed.
 *
 * @see Completable
 */
public abstract class CompletableUseCase<Params> extends BaseReactiveUseCase {

    protected CompletableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    /**
     * Builds an {@link Completable} which will be used when executing the current {@link BaseReactiveUseCase}.
     */
    protected abstract Completable buildUseCaseCompletable(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link io.reactivex.observers.DisposableCompletableObserver} which will be listening to the observable build
     * by {@link #buildUseCaseCompletable(Params)} ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableCompletableObserver observer, Params params) {
        //Preconditions.checkNotNull(observer);
        final Completable observable = this.buildUseCaseCompletable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        // Subscribe Custom Observer
        addDisposable(observable.subscribeWith(observer));
    }
}
