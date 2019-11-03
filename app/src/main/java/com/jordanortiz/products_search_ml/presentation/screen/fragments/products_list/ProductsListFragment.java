package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.presentation.ui.BaseFragment;
import com.jordanortiz.products_search_ml.presentation.di.component.ViewComponent;
import com.jordanortiz.products_search_ml.presentation.screen.activities.MainActivity;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.ProductDetailFragment;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ListPagingModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 */
public class ProductsListFragment extends BaseFragment implements
        ProductsListRecyclerViewAdapter.ProductsListAdapterListener {

    public static final String TAG = ProductsListFragment.class.getSimpleName();
    private static final String CATEGORY = "category";

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    @Inject
    ViewModelProvider.Factory factory;
    @Inject
    ProductsListRecyclerViewAdapter mRecyclerViewAdapter;
    @Inject
    LinearLayoutManager mLayoutManager;

    private ProductsListViewModel mViewModel;
    private String mProductsCategory;


    public static ProductsListFragment newInstance(String category) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_products_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mProductsCategory = getArguments().getString(CATEGORY);
            Log.e(TAG, "onCreate: category selected -> " + mProductsCategory );
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ViewComponent component = getViewComponent();
        if (component != null) {
            component.inject(this);
        }

        mViewModel = ViewModelProviders.of(this,factory).get(ProductsListViewModel.class);
        mViewModel.onViewPrepared(mProductsCategory);

        mRecyclerViewAdapter.setCallbackListener(this);

        rvProducts.setItemAnimator(new DefaultItemAnimator());
        rvProducts.setAdapter(mRecyclerViewAdapter);
        rvProducts.setLayoutManager(mLayoutManager);

        subscribeToModel(mViewModel);
        // Create menu
        setHasOptionsMenu(true);

        Log.e(TAG, "onActivityCreated: ");
    }

    private void subscribeToModel(ProductsListViewModel viewModel) {
        viewModel.getProductList().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productListModel) {
                if(productListModel.size() > 0)
                    showProductList(productListModel);
//                TODO add else showProductListEmpty()
            }
        });

        viewModel.getListPaging().observe(this, new Observer<ListPagingModel>() {
            @Override
            public void onChanged(ListPagingModel listPagingModel) {
                Log.e(TAG, "onChanged: ListPagingModel -> " + listPagingModel.toString() );
            }
        });

        viewModel.getErrorMessage().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer errorMessage) {
                if(errorMessage != null)
                    showMessageToastyError(errorMessage);
            }
        });
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
                mViewModel.loadProductList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(TAG, "onQueryTextChange: partial query -> " + newText );
                return false;
            }
        });

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

    private void showProductList(List<ProductModel> productList) {
        mRecyclerViewAdapter.addItems(productList);
    }
}
