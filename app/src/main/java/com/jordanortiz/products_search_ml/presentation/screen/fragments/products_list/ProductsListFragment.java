package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.presentation.ui.BaseFragment;
import com.jordanortiz.products_search_ml.presentation.di.component.ViewComponent;
import com.jordanortiz.products_search_ml.presentation.screen.activities.MainActivity;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.ProductDetailFragment;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 */
public class ProductsListFragment extends BaseFragment implements ProductsListMvpView,
        ProductsListRecyclerViewAdapter.ProductsListAdapterListener {

    public static final String TAG = ProductsListFragment.class.getSimpleName();
    private static final String CATEGORY = "category";

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    @Inject
    ProductsListMvpPresenter<ProductsListMvpView> mPresenter;

    @Inject
    ProductsListRecyclerViewAdapter mRecyclerViewAdapter;
    @Inject
    LinearLayoutManager mLayoutManager;

    public ProductsListFragment() {

    }

    public static ProductsListFragment newInstance(String category) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);

        ViewComponent component = getViewComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mRecyclerViewAdapter.setCallbackListener(this);
            mPresenter.onAttach(this);
        }
        Log.e(TAG, "onCreateView: ");
        return view;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvProducts.setLayoutManager(mLayoutManager);
        rvProducts.setItemAnimator(new DefaultItemAnimator());
        rvProducts.setAdapter(mRecyclerViewAdapter);
        mPresenter.onViewPrepared();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Create menu
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate menu
        ((MainActivity)getActivity()).getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Buscar productos");
        searchView.setSubmitButtonEnabled(Boolean.TRUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(TAG, "onQueryTextSubmit: query -> " + query );
                mPresenter.applyQueryOfProduct(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(TAG, "onQueryTextChange: partial query -> " + newText );
                return false;
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /*
    * Callback to listener{@link ProductsListRecyclerViewAdapter.ProductsListAdapterListener}
    * */
    @Override
    public void onProductSelected(ProductModel product) {
        ((MainActivity)getBaseActivity()).replaceFragment(
                ProductDetailFragment.newInstance(product.getId()),
                ProductDetailFragment.TAG
        );
    }


    @Override
    public void showMsgQueryIsEmpty() {
        this.showMessageToastyInfo(getBaseActivity().getString(R.string.msg_query_empty));
    }

    @Override
    public void showProductList(List<ProductModel> productList) {
        mRecyclerViewAdapter.addItems(productList);
    }
}
