package com.jordanortiz.products_search_ml.presentation.mapper;

import com.jordanortiz.products_search_ml.domain.model.product.ProductAttributeEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductEntity;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAddressModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAttributeModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailInstallmentsModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProductDetailMapper {

    @Inject
    public ProductDetailMapper() {
    }

    /**
     * Transform a {@link ProductEntity} into an {@link ProdDetailModel }.
     *
     * @param productEntity Object to be transformed.
     * @return {@link ProdDetailModel }.
     */
    public ProdDetailModel transformForProductDetail(ProductEntity productEntity){
        ProdDetailModel prodDetailModel =  new ProdDetailModel();
        prodDetailModel.setId(productEntity.getId());
        prodDetailModel.setTitle(productEntity.getTitle());
        prodDetailModel.setThumbnailImg(productEntity.getThumbnail());
        prodDetailModel.setPrice(productEntity.getPrice());
        prodDetailModel.setAvailableQuantity(productEntity.getAvailableQuantity());
        prodDetailModel.setSoldQuantity(productEntity.getSoldQuantity());
        prodDetailModel.setConditionType(productEntity.getCondition());
        prodDetailModel.setShipping(productEntity.getShipping().getFreeShipping());

        if(productEntity.getInstallments() != null)
            prodDetailModel.setInstallments(new ProdDetailInstallmentsModel(
                    productEntity.getInstallments().getQuantity(),
                    productEntity.getInstallments().getAmount(),
                    productEntity.getInstallments().getRate()
            ));

        if(productEntity.getAddress() != null)
            prodDetailModel.setAddressModel(new ProdDetailAddressModel(
                    productEntity.getAddress().getStateName(),
                    productEntity.getAddress().getCityName()
            ));

        if(productEntity.getAttributes() != null)
            prodDetailModel.setAttributeModelList(
                    this.transformForAttributes(productEntity.getAttributes())
            );

        return prodDetailModel;
    }

    private List<ProdDetailAttributeModel> transformForAttributes(List<ProductAttributeEntity> attributes) {
        List<ProdDetailAttributeModel> prodDetailAttributeModelList = new ArrayList<>();
        for (ProductAttributeEntity attribute : attributes) {
            prodDetailAttributeModelList.add(
                    new ProdDetailAttributeModel(
                            attribute.getSource(),
                            attribute.getName(),
                            attribute.getValueName()
                    )
            );
        }

        return prodDetailAttributeModelList;
    }

}
