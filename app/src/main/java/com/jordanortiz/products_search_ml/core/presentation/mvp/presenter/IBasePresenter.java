package com.jordanortiz.products_search_ml.core.presentation.mvp.presenter;

import com.androidnetworking.error.ANError;
import com.jordanortiz.products_search_ml.core.presentation.mvp.view.MvpBaseView;

public interface IBasePresenter {
    int handleApiError(ANError error);
}
