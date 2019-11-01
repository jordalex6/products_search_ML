package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail;


import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.presentation.ui.BaseFragment;
import com.jordanortiz.products_search_ml.presentation.di.component.ViewComponent;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAttributeModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A {@link BaseFragment} subclass.
 * Use the {@link ProductDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailFragment extends BaseFragment
        implements ProductDetailMvpView {

    public static final String TAG = ProductDetailFragment.class.getSimpleName();
    private static final String ARG_PRODUCT_ID = "PRODUCT_ID";

    private String productId;

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
    ProductDetailMvpPresenter<ProductDetailMvpView> mPresenter;
    @Inject
    ProductDetailAtributteRecViewAdapter mRecyclerViewAdapter;
    @Inject
    LinearLayoutManager mLayoutManager;

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
            productId = getArguments().getString(ARG_PRODUCT_ID);
        }
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ViewComponent component = getViewComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        Log.e(TAG, "onCreateView: ");
        return view;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvAttributeList.setLayoutManager(mLayoutManager);
        rvAttributeList.setAdapter(mRecyclerViewAdapter);
        mPresenter.onViewPrepared(productId);
    }

    @Override
    public void setUpHeaderInfoView(String info) {
        tvHeaderInfo.setText(info);
    }

    @Override
    public void setUpThumbnailView(String thumbnailUrl) {
        Glide.with(this)
                .load(thumbnailUrl)
                .placeholder(R.drawable.smartphone_muestra)
                .fitCenter()
                .into(ivThumbnail);
    }

    @Override
    public void setUpTitleView(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setUpPriceView(String price) {
        tvPrice.setText(price);
    }

    @Override
    public void setUpInstallmentsView(String installment, boolean hasInterest) {
        tvInstallments.setText(installment);
        if(hasInterest){
            tvInstallments.setTextColor(
                    getBaseActivity().getResources().getColor(R.color.primaryTextColor)
            );
            ivIconInstallments.setColorFilter(
                    getBaseActivity().getResources().getColor(R.color.primaryTextColor)
            );
        }
    }

    @Override
    public void hideInstallmentsView() {
        llInstallments.setVisibility(View.GONE);
    }

    @Override
    public void setUpShippingView(boolean hasFreeShipping) {
        if (hasFreeShipping)
            llShipping.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUpAddressView(String fullAddress) {
        tvAddress.setText(fullAddress);
    }

    @Override
    public void setUpAttributeListView(List<ProdDetailAttributeModel> attributeList) {
        mRecyclerViewAdapter.addItems(attributeList);
    }
}
