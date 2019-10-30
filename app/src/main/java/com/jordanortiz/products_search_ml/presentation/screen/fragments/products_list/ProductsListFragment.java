package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
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
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 */
public class ProductsListFragment extends BaseFragment implements ProductsListMvpView,
        ProductsListRecyclerViewAdapter.ProductsListAdapterListener {

    // TODO: Customize parameter argument names
    public static final String TAG = ProductsListFragment.class.getSimpleName();
    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private List<ProductModel> productsList;


    @Inject
    ProductsListMvpPresenter<ProductsListMvpView> mPresenter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductsListFragment() {
        productsList =  new ArrayList<>();
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProductsListFragment newInstance(int columnCount) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);

        ViewComponent component = getViewComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ProductsListRecyclerViewAdapter(productsList, this));
        }
        return view;
    }

    @Override
    protected void setUp(View view) {

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
        Log.d(TAG, "onProductSelected: " + product.toString());
    }


    @Override
    public void showMsgQueryIsEmpty() {
        this.showMessageToastyInfo(getBaseActivity().getString(R.string.msg_query_empty));
    }
}
