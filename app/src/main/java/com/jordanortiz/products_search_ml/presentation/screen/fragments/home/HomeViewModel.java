package com.jordanortiz.products_search_ml.presentation.screen.fragments.home;

import com.jordanortiz.products_search_ml.core.presentation.mvp.presenter.BasePresenter;
import com.jordanortiz.products_search_ml.core.presentation.mvp.presenter.IBasePresenter;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;

import javax.inject.Inject;

@PerActivity
public class HomeViewModel extends BasePresenter {

    @Inject
    public HomeViewModel() {
    }

}
