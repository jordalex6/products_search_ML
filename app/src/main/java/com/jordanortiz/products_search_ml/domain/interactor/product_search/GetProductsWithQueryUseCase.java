package com.jordanortiz.products_search_ml.domain.interactor.product_search;

import com.jordanortiz.products_search_ml.domain.executor.PostExecutionThread;
import com.jordanortiz.products_search_ml.domain.executor.ThreadExecutor;
import com.jordanortiz.products_search_ml.domain.interactor.base.SingleUseCase;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetProductsWithQueryUseCase extends SingleUseCase<ProductsPagingEntity, GetProductsWithQueryUseCase.Params> {

    private DataManager dataManager;

    @Inject
    public GetProductsWithQueryUseCase(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            DataManager dataManager) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
    }

    @Override
    protected Single<ProductsPagingEntity> buildUseCaseSingle(Params params) {
        return dataManager.getProductsDataWithQuery(params.query);
    }


    public static final class Params {

        private final String query;

        private Params(String query) {
            this.query = query;
        }

        public static Params forProductsQuery(String query) {
            return new Params(query);
        }
    }
}
