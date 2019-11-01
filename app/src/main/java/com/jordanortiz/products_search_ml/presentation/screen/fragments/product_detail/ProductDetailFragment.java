package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.presentation.ui.BaseFragment;
import com.jordanortiz.products_search_ml.presentation.di.component.ViewComponent;

import javax.inject.Inject;

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

    @Inject
    ProductDetailMvpPresenter<ProductDetailMvpView> mPresenter;

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
        return view;
    }

    @Override
    protected void setUp(View view) {
        mPresenter.onViewPrepared(productId);
    }

}
