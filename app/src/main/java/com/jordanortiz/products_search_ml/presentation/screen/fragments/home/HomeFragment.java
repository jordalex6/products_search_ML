package com.jordanortiz.products_search_ml.presentation.screen.fragments.home;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.presentation.ui.BaseFragment;
import com.jordanortiz.products_search_ml.presentation.di.component.ViewComponent;
import com.jordanortiz.products_search_ml.presentation.screen.activities.MainActivity;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListFragment;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment{
    public static final String TAG = HomeFragment.class.getSimpleName();
    private static final String CATEGORY_COMPUTING = "MLA1648";
    private static final String CATEGORY_SMARTPHONE = "MLA1051";
    private static final String CATEGORY_GAME = "MLA1144";
    private static final String CATEGORY_HOME_APPLIANCES = "MLA5726";
    private static final String CATEGORY_CAMERA = "MLA1039";
    private static final String CATEGORY_CARS = "MLA1743";

    @Inject
    ViewModelProvider.Factory factory;

    private HomeViewModel mViewModel;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.setupTitleToolbar(getString(R.string.title_toolbar_home));
        this.setupDrawerEnabled(Boolean.TRUE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewComponent component = getViewComponent();
        if (component != null) {
            component.inject(this);
        }

        mViewModel = ViewModelProviders.of(this,factory).get(HomeViewModel.class);
    }

    @OnClick(R.id.category_computing)
    void onClickCategoryComputing(){
        launchFragmentProductListByCategory(CATEGORY_COMPUTING);
    }

    @OnClick(R.id.category_smartphone)
    void onClickCategorySmartphone(){
        launchFragmentProductListByCategory(CATEGORY_SMARTPHONE);
    }

    @OnClick(R.id.category_game)
    void onClickCategoryGame(){
        launchFragmentProductListByCategory(CATEGORY_GAME);
    }

    @OnClick(R.id.category_home_appliances)
    void onClickCategoryHomeAppliances(){
        launchFragmentProductListByCategory(CATEGORY_HOME_APPLIANCES);
    }

    @OnClick(R.id.category_camera)
    void onClickCategoryCamera(){
        launchFragmentProductListByCategory(CATEGORY_CAMERA);
    }

    @OnClick(R.id.category_cars)
    void onClickCategoryCards(){
        launchFragmentProductListByCategory(CATEGORY_CARS);
    }

    private void launchFragmentProductListByCategory(String category){
        ((MainActivity)getBaseActivity()).replaceFragment(
                ProductsListFragment.newInstance(category),
                ProductsListFragment.TAG
        );
    }

}
