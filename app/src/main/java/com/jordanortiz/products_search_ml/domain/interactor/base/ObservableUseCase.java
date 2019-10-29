package com.itnnovar.capturescorpion.domain.interactor.base;

import com.jordanortiz.products_search_ml.domain.executor.PostExecutionThread;
import com.jordanortiz.products_search_ml.domain.executor.ThreadExecutor;
import com.jordanortiz.products_search_ml.domain.interactor.base.BaseReactiveUseCase;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a [DisposableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * This use case is to be used when we expect multiple values to be emitted via an [Observable].
 *
 */

public abstract class ObservableUseCase<T, Params> extends BaseReactiveUseCase {

    protected ObservableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    /**
     * Builds an {@link Single} which will be used when executing the current {@link BaseReactiveUseCase}.
     */
    protected abstract Observable<T> buildUseCaseObservable(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link io.reactivex.observers.DisposableSingleObserver} which will be listening to the observable build
     * by {@link #buildUseCaseObservable(Params)} ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableObserver<T> observer, Params params) {
        //Preconditions.checkNotNull(observer);
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        // Subscribe Custom Observer
        addDisposable(observable.subscribeWith(observer));
    }
}
