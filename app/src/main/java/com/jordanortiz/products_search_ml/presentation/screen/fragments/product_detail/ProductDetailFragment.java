package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.presentation.ui.BaseFragment;
import com.jordanortiz.products_search_ml.presentation.di.component.ViewComponent;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAddressModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAttributeModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailInstallmentsModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A {@link BaseFragment} subclass.
 * Use the {@link ProductDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailFragment extends BaseFragment {

    public static final String TAG = ProductDetailFragment.class.getSimpleName();
    private static final String ARG_PRODUCT_ID = "PRODUCT_ID";

    private String mProductId;

    @BindView(R.id.item_header_info)
    TextView tvHeaderInfo;
    @BindView(R.id.item_title)
    TextView tvTitle;
    @BindView(R.id.item_price)
    TextView tvPrice;
    @BindView(R.id.item_thumbnail)
    ImageView ivThumbnail;
    @BindView(R.id.detail_installments)
    LinearLayoutCompat llInstallments;
    @BindView(R.id.item_installments)
    TextView tvInstallments;
    @BindView(R.id.icon_item_installments)
    ImageView ivIconInstallments;
    @BindView(R.id.detail_shipping)
    LinearLayoutCompat llShipping;
    @BindView(R.id.item_address)
    TextView tvAddress;
    @BindView(R.id.rv_product_detail_attribute)
    RecyclerView rvAttributeList;


    @Inject
    ViewModelProvider.Factory factory;
    @Inject
    ProductDetailAttributeRecViewAdapter mRecyclerViewAdapter;
    @Inject
    LinearLayoutManager mLayoutManager;

    private ProductDetailViewModel mViewModel;

    public ProductDetailFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *.
     * @param productId Parameter.
     * @return A new instance of fragment ProductDetailFragment.
     */
    public static ProductDetailFragment newInstance(String productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProductId = getArguments().getString(ARG_PRODUCT_ID);
        }
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_product_detail;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewComponent component = getViewComponent();
        if (component != null) {
            component.inject(this);
        }

        mViewModel = ViewModelProviders.of(this,factory).get(ProductDetailViewModel.class);
        mViewModel.onViewPrepared(mProductId);

        rvAttributeList.setLayoutManager(mLayoutManager);
        rvAttributeList.setAdapter(mRecyclerViewAdapter);

        subscribeToModel(mViewModel);
    }

    private void subscribeToModel(ProductDetailViewModel mViewModel) {
        mViewModel.getProductDetail().observe(this, new Observer<ProdDetailModel>() {
            @Override
            public void onChanged(ProdDetailModel productDetail) {
                //  set up condition | sold quantity | available quantity
                ProductDetailFragment.this.setUpHeaderInfoView(buildHeaderInfo(
                        productDetail.getConditionType(),
                        productDetail.getSoldQuantity(),
                        productDetail.getAvailableQuantity()
                ));
                //  set up thumbnail url
                ProductDetailFragment.this.setUpThumbnailView(productDetail.getThumbnailImg());
                //  set up title
                ProductDetailFragment.this.setUpTitleView(productDetail.getTitle());
                //  set up price
                ProductDetailFragment.this.setUpPriceView("$ " + productDetail.getPrice());
                //  set up installments
                if(productDetail.getInstallments() != null) {
                    ProductDetailFragment.this.setUpInstallmentsView(
                            buildInstalmentsInfo(productDetail.getInstallments())
                    );
                    boolean hasInterest = productDetail.getInstallments().getRate() > 0;
                    ProductDetailFragment.this.setUpInstallmentsViewStyle(hasInterest);
                }
                //  set up shipping
                ProductDetailFragment.this.setUpShippingView(productDetail.isShipping());
                //  set up full address
                ProductDetailFragment.this.setUpAddressView(buildFullAddressInfo(productDetail.getAddressModel()));
                //  set up attributes list
                ProductDetailFragment.this.setUpAttributeListView(productDetail.getAttributeModelList());
            }
        });
    }


    private void setUpHeaderInfoView(String info) {
        tvHeaderInfo.setText(info);
    }

    private void setUpThumbnailView(String thumbnailUrl) {
        Glide.with(this)
                .load(thumbnailUrl)
                .placeholder(R.drawable.loading)
                .fitCenter()
                .into(ivThumbnail);
    }

    private void setUpTitleView(String title) {
        tvTitle.setText(title);
    }

    private void setUpPriceView(String price) {
        tvPrice.setText(price);
    }

    private void setUpInstallmentsView(String installment) {
        tvInstallments.setText(installment);
        tvInstallments.setVisibility(View.VISIBLE);
    }

    private void setUpInstallmentsViewStyle(boolean hasInterest) {
        if(hasInterest){
            tvInstallments.setTextColor(
                    getBaseActivity().getResources().getColor(R.color.primaryTextColor)
            );
            ivIconInstallments.setColorFilter(
                    getBaseActivity().getResources().getColor(R.color.primaryTextColor)
            );
        }
    }

    private void setUpShippingView(boolean hasFreeShipping) {
        if (hasFreeShipping)
            llShipping.setVisibility(View.VISIBLE);
    }

    private void setUpAddressView(String fullAddress) {
        tvAddress.setText(fullAddress);
    }

    private void setUpAttributeListView(List<ProdDetailAttributeModel> attributeList) {
        mRecyclerViewAdapter.addItems(attributeList);
    }

    private String buildFullAddressInfo(ProdDetailAddressModel addressModel) {
        return addressModel.getStateName() + " - " + addressModel.getCityName();
    }

    private String buildInstalmentsInfo(ProdDetailInstallmentsModel installmentsModel) {
        StringBuilder installments = new StringBuilder();
        if (installmentsModel.getRate() > 0)
            installments.append("Pagá en hasta ").append(installmentsModel.getQuantity()).append(" cuotas");
        else
            installments.append("Pagá en hasta ").append(installmentsModel.getQuantity()).append(" cuotas sin interés");

        return installments.toString();
    }

    private String buildHeaderInfo(String conditionType, int soldQuantity, Integer availableQuantity) {
        StringBuilder headerInfo = new StringBuilder();
        switch (conditionType){
            case "new":
                headerInfo.append("Nuevo | ");
                break;
            case "used":
                headerInfo.append("Usado | ");
                break;
        }

        if (soldQuantity == 1)
            headerInfo.append(soldQuantity).append(" vendido | ");
        else if (soldQuantity > 1)
            headerInfo.append(soldQuantity).append(" vendidos | ");

        if (availableQuantity == 1)
            headerInfo.append(availableQuantity).append(" disponible");
        else if (availableQuantity > 1)
            headerInfo.append(availableQuantity).append(" disponibles");

        return headerInfo.toString();
    }
}
