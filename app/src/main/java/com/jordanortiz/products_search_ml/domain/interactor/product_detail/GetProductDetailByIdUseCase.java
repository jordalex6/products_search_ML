package com.jordanortiz.products_search_ml.domain.interactor.product_detail;

import com.jordanortiz.products_search_ml.domain.executor.PostExecutionThread;
import com.jordanortiz.products_search_ml.domain.executor.ThreadExecutor;
import com.jordanortiz.products_search_ml.domain.interactor.base.SingleUseCase;
import com.jordanortiz.products_search_ml.domain.model.product.ProductEntity;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetProductDetailByIdUseCase
        extends SingleUseCase<ProductEntity, GetProductDetailByIdUseCase.Params> {

    private final DataManager dataManager;

    @Inject
    protected GetProductDetailByIdUseCase(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            DataManager dataManager) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
    }

    @Override
    protected Single<ProductEntity> buildUseCaseSingle(Params params) {
        return dataManager.getProductsDetailById(params.id);
    }

    public static class Params {
        private final String id;

        private Params(String id) {
            this.id = id;
        }

        public static Params forProductsDetail(String id) {
            return new Params(id);
        }
    }
}
