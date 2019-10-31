package com.jordanortiz.products_search_ml.data.network.mercado_libre.mapper;

import com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw.ProductAttributeRaw;
import com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw.ProductsRaw;
import com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw.ProductsResponse;
import com.jordanortiz.products_search_ml.domain.model.product.PagingEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductAddressEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductAttributeEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductInstallmentsEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductShippingEntity;
import com.jordanortiz.products_search_ml.domain.model.product.ProductsPagingEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProductEntityDataMapper {

    @Inject
    public ProductEntityDataMapper() {
    }

    public ProductsPagingEntity transform(ProductsResponse productsResponse){
        ProductsPagingEntity productsPaging = new ProductsPagingEntity();
        //          PagingRaw to PagingEntity mapper
        PagingEntity paging =  new PagingEntity(
                productsResponse.getProductsPaging().getTotal(),
                productsResponse.getProductsPaging().getLimit(),
                productsResponse.getProductsPaging().getPrimaryResults()
        );
        productsPaging.setPaging(paging);

        List<ProductEntity> productList = productTransform(productsResponse.getProductsRaws());
        productsPaging.setProductList(productList);

        return productsPaging;
    }

    private List<ProductEntity> productTransform(List<ProductsRaw> productsRaw) {
        List<ProductEntity> productEntityList =  new ArrayList<>();
        for (ProductsRaw product : productsRaw) {
            //          ProductRaw to ProductEntity mapper
            ProductEntity productEntity =  new ProductEntity();
            productEntity.setId(product.getId());
            productEntity.setSiteId(product.getSiteId());
            productEntity.setTitle(product.getTitle());
            productEntity.setPrice(product.getPrice());
            productEntity.setCurrencyId(product.getCurrencyId());
            productEntity.setAvailableQuantity(product.getAvailableQuantity());
            productEntity.setSoldQuantity(product.getSoldQuantity());
            productEntity.setCondition(product.getCondition());
            productEntity.setCategoryId(product.getCategoryId());
            productEntity.setThumbnail(product.getThumbnail());

            //          ProductAddressRaw to ProductAddressEntity  mapper
            ProductAddressEntity productAddress =  new ProductAddressEntity(
                    product.getProductAddressRaw().getStateName(),
                    product.getProductAddressRaw().getCityName()
            );
            productEntity.setAddress(productAddress);

            //          ProductInstallmentsRaw to ProductInstallmentsEntity  mapper
            if(product.getProductInstallmentsRaw() !=  null)
            {
                ProductInstallmentsEntity productInstallments =  new ProductInstallmentsEntity(
                        product.getProductInstallmentsRaw().getQuantity(),
                        product.getProductInstallmentsRaw().getAmount(),
                        product.getProductInstallmentsRaw().getRate(),
                        product.getProductInstallmentsRaw().getCurrencyId()
                );
                productEntity.setInstallments(productInstallments);
            }

            //          ProductShippingRaw to ProductShippingEntity mapper
            ProductShippingEntity productShipping =  new ProductShippingEntity(
                    product.getProductShipping().getFreeShipping()
            );
            productEntity.setShipping(productShipping);

            //          ProductAttributeRaw to ProductAttributeEntity mapper
            List<ProductAttributeEntity> productAttributeEntityList = new ArrayList<>();
            for (ProductAttributeRaw attribute : product.getProductAttributesRaws()) {
               productAttributeEntityList.add(new ProductAttributeEntity(
                       attribute.getId(),
                       attribute.getName(),
                       attribute.getValueName(),
                       attribute.getAttributeGroupName(),
                       attribute.getAttributeGroupId()
               ));
            }
            productEntity.setAttributes(productAttributeEntityList);

            //          Add new ProductEntity to ProductEntityList
            productEntityList.add(productEntity);
        }

        return productEntityList;
    }

}
