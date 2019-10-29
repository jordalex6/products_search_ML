package com.jordanortiz.products_search_ml.domain.interactor.product_search;

import com.jordanortiz.products_search_ml.domain.executor.PostExecutionThread;
import com.jordanortiz.products_search_ml.domain.executor.ThreadExecutor;
import com.jordanortiz.products_search_ml.domain.interactor.base.CompletableUseCase;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;

import javax.inject.Inject;

import io.reactivex.Completable;

public class PushProductQueryUseCase extends CompletableUseCase<PushProductQueryUseCase.Params> {

    private DataManager dataManager;

    @Inject
    public PushProductQueryUseCase(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            DataManager dataManager) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
    }

    @Override
    protected Completable buildUseCaseCompletable(Params params) {
        return dataManager.pushData();
    }

    public static final class Params {

        private final String data;

        private Params(String data) {
            this.data = data;
        }

        public static Params forCaptureScorpion(String data) {
            return new Params(data);
        }
    }
}
