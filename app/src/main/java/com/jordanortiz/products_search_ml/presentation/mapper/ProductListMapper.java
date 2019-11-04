package com.jordanortiz.products_search_ml.presentation.mapper;

import com.jordanortiz.products_search_ml.domain.model.product.ProductEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ListPagingModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductInstallmentsModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductListModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProductListMapper {

    @Inject
    public ProductListMapper() {
    }

    /**
     * Transform a {@link ProductsPagingEntity} into an {@link ProductListModel }.
     *
     * @param productsPagingEntity Object to be transformed.
     * @return {@link ProductListModel }.
     */
    public ProductListModel transformForList(ProductsPagingEntity productsPagingEntity){
        ProductListModel productList = new ProductListModel();

        productList.setPaging(new ListPagingModel(
                        productsPagingEntity.getPaging().getTotal(),
                        productsPagingEntity.getPaging().getPrimaryResults(),
                        productsPagingEntity.getPaging().getLimit())
        );

        List<ProductModel> productModelList = transformForProductList(productsPagingEntity.getProductList());
        productList.setProductList(productModelList);

        return productList;
    }

    /**
     * Transform a {@link ProductEntity} into an {@link ProductModel }.
     *
     * @param productEntity Object to be transformed.
     * @return {@link ProductModel }.
     */
    public ProductModel transformForProduct(ProductEntity productEntity){
        ProductModel productModel =  new ProductModel();
        productModel.setId(productEntity.getId());
        productModel.setTitle(productEntity.getTitle());
        productModel.setThumbnailImg(productEntity.getThumbnail());
        productModel.setPrice(productEntity.getPrice());
        productModel.setAvailableQuantity(productEntity.getAvailableQuantity());
        productModel.setSoldQuantity(productEntity.getSoldQuantity());
        productModel.setConditionType(productEntity.getCondition());
        productModel.setShipping(productEntity.getShipping().getFreeShipping());

        if(productEntity.getInstallments() != null)
            productModel.setProductInstallments(new ProductInstallmentsModel(
                    productEntity.getInstallments().getQuantity(),
                    productEntity.getInstallments().getAmount(),
                    productEntity.getInstallments().getRate()
            ));

        return productModel;
    }

    /**
     * Transform a {@link List<ProductEntity>} into an {@link List<ProductModel> }.
     *
     * @param productEntityList Object to be transformed.
     * @return {@link List<ProductModel> }.
     */
    public List<ProductModel> transformForProductList(List<ProductEntity> productEntityList) {
        List<ProductModel> productModelList =  null;
        if (productEntityList != null){
            productModelList =  new ArrayList<>();

            for(ProductEntity product : productEntityList) {
               ProductModel productModel = transformForProduct(product);
               productModelList.add(productModel);
            }
        }
        
        return productModelList;
    }
}
