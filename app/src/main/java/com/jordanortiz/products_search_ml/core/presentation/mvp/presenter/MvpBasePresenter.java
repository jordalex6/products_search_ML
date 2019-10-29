package com.jordanortiz.products_search_ml.core.presentation.mvp.presenter;

import com.androidnetworking.error.ANError;
import com.jordanortiz.products_search_ml.core.presentation.mvp.view.MvpBaseView;

public interface MvpBasePresenter<V extends MvpBaseView>{

    void onResume();
    void onPause();

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(ANError error);

    void setUserAsLoggedOut();
}
