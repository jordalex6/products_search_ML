package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.presentation.ui.BaseFragment;
import com.jordanortiz.products_search_ml.presentation.di.component.ViewComponent;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.dummy.DummyContent;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.dummy.DummyContent.DummyItem;

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
    // TODO: Customize parameters
    private int mColumnCount = 1;


    @Inject
    ProductsListMvpPresenter<ProductsListMvpView> mPresenter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductsListFragment() {
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
            recyclerView.setAdapter(new ProductsListRecyclerViewAdapter(DummyContent.ITEMS, this));
        }
        return view;
    }

    @Override
    protected void setUp(View view) {

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
    public void onProductSelected(DummyItem dummy) {
        Log.d(TAG, "onProductSelected: " + dummy.toString());
    }


}
