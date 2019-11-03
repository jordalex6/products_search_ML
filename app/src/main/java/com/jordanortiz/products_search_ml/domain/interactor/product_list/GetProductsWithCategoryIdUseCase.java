package com.jordanortiz.products_search_ml.domain.interactor.product_list;

import com.jordanortiz.products_search_ml.domain.executor.PostExecutionThread;
import com.jordanortiz.products_search_ml.domain.executor.ThreadExecutor;
import com.jordanortiz.products_search_ml.domain.interactor.base.SingleUseCase;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;
import com.jordanortiz.products_search_ml.domain.repository.DataManager;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetProductsWithCategoryIdUseCase extends SingleUseCase<ProductsPagingEntity, GetProductsWithCategoryIdUseCase.Params> {

    private DataManager dataManager;

    @Inject
    public GetProductsWithCategoryIdUseCase(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            DataManager dataManager) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
    }

    @Override
    protected Single<ProductsPagingEntity> buildUseCaseSingle(Params params) {
        return dataManager.getProductsDataWithCategoryId(params.categoryId);
    }


    public static final class Params {

        private final String categoryId;

        private Params(String categoryId) {
            this.categoryId = categoryId;
        }

        public static Params forProductsCategory(String categoryId) {
            return new Params(categoryId);
        }
    }
}
