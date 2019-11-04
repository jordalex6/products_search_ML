package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
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
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductListModel;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A fragment representing a list of Items.
 */
public class ProductsListFragment extends BaseFragment implements
        ProductsListRecyclerViewAdapter.ProductsListAdapterListener {

    public static final String TAG = ProductsListFragment.class.getSimpleName();
    private static final String CATEGORY = "category";

    @BindView(R.id.loading)
    ProgressBar loading;

    @BindView(R.id.informative_view_container)
    LinearLayoutCompat informativeViewContainer;
    @BindView(R.id.informative_img)
    ImageView ivInformativeImg;
    @BindView(R.id.informative_title)
    TextView tvInformativeTitle;
    @BindView(R.id.informative_suggestion)
    TextView tvInformativeSuggestion;
    @BindView(R.id.btn_retry)
    Button btnRetry;

    @BindView(R.id.product_list_container)
    RelativeLayout productListContainer;
    @BindView(R.id.list_paging)
    TextView tvListPaging;
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
    private String mProductsQuery;


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

    }

    private void subscribeToModel(ProductsListViewModel viewModel) {
        viewModel.getProductList().observe(this, new Observer<ProductListModel>() {
            @Override
            public void onChanged(ProductListModel productListModel) {
                if(productListModel.getProductList().size() > 0){
                    setUpProductList(productListModel.getProductList());
                }else {
                    setUpProductListEmpty();
                }
                setUpListPaging(productListModel.getPaging());
            }
        });

        viewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isShow) {
                setUpLoadingStatus(isShow);
            }
        });

        viewModel.getNetworkStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasNetwork) {
                setUpNetworkStatus(hasNetwork);
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
                mProductsQuery = query;
                mProductsCategory = null;
                mViewModel.loadProductList(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @OnClick(R.id.btn_retry)
    void onClickBtnRetry(){
        if(mProductsCategory != null)
            mViewModel.loadProductListByCategory(mProductsCategory);
        else if(mProductsQuery != null)
            mViewModel.loadProductList(mProductsQuery);
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

    private void setUpLoadingStatus(Boolean isShow) {
        if (isShow) {
            loading.setVisibility(View.VISIBLE);
            productListContainer.setVisibility(View.GONE);
            informativeViewContainer.setVisibility(View.GONE);
        }
        else
            loading.setVisibility(View.GONE);
    }

    private void setUpNetworkStatus(Boolean hasNetwork) {
        if (!hasNetwork){
            productListContainer.setVisibility(View.GONE);
            informativeViewContainer.setVisibility(View.VISIBLE);
            btnRetry.setVisibility(View.VISIBLE);
            setUpInformativeView(
                    "¡Parece que no hay internet!",
                    "Revisa tu conexión para seguir navegando.",
                    getBaseActivity().getResources().getDrawable(R.drawable.network)
            );
        }else{
            informativeViewContainer.setVisibility(View.GONE);
            btnRetry.setVisibility(View.GONE);
        }

    }


    private void setUpProductList(List<ProductModel> productList) {
        productListContainer.setVisibility(View.VISIBLE);
        informativeViewContainer.setVisibility(View.GONE);
        mRecyclerViewAdapter.addItems(productList);
    }

    private void setUpProductListEmpty() {
        productListContainer.setVisibility(View.GONE);
        informativeViewContainer.setVisibility(View.VISIBLE);
        setUpInformativeView(
                "No encontramos publicaciones",
                "Revisa que la palabra esté bien escrita.",
                getBaseActivity().getResources().getDrawable(R.drawable.search_cloud)
        );
    }

    private void setUpListPaging(ListPagingModel listPaging) {
        if (listPaging.getTotal() > 0){
            String paging = listPaging.getTotal() + " resultados ";
            tvListPaging.setText(paging);
        }
    }

    private void setUpInformativeView(String title, String suggestion, Drawable img) {
        ivInformativeImg.setImageDrawable(img);
        tvInformativeTitle.setText(title);
        tvInformativeSuggestion.setText(suggestion);
    }
}
