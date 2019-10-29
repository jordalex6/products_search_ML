package com.jordanortiz.products_search_ml.core.presentation.mvp.presenter;

import android.util.Log;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.data.ApiError;
import com.jordanortiz.products_search_ml.core.presentation.mvp.view.MvpBaseView;
import com.jordanortiz.products_search_ml.core.utilities.AppConstants;


import javax.net.ssl.HttpsURLConnection;


public abstract class BasePresenter<V extends MvpBaseView> implements MvpBasePresenter<V> {

    private static final String TAG = BasePresenter.class.getSimpleName();



    public BasePresenter() {

    }

    protected V view;

    public void onResume(){}
    public void onPause(){}

    @Override
    public void onAttach(V mvpView) {
        view = mvpView;
    }

    @Override
    public void onDetach() {
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getMvpView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    @Override
    public void setUserAsLoggedOut() {
        // Clear token
    }

    @Override
    public void handleApiError(ANError error) {
        if (error == null) {
            getMvpView().showMessageToastyError(R.string.api_default_error);
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
            getMvpView().showMessageToastyError(R.string.connection_error);
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            getMvpView().showMessageToastyError(R.string.api_retry_error);
            return;
        }

        final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        try {
            int error_message = R.string.api_default_error;
            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);

            if (apiError != null && apiError.getMessage() != null) {
                getMvpView().showMessageToastyError(apiError.getMessage());
                return;
            }

            switch (error.getErrorCode()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                case HttpsURLConnection.HTTP_FORBIDDEN:
//                    setUserAsLoggedOut();
//                    getMvpView().openActivityOnTokenExpire();
                    break;
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                    error_message = R.string.error_500;
                    break;
                case HttpsURLConnection.HTTP_NOT_FOUND:
                    error_message = R.string.error_404;
                    break;
                case HttpsURLConnection.HTTP_CLIENT_TIMEOUT:
                    error_message = R.string.network_timeout_408;
                    break;
                default:
                    Log.e(TAG, "handleApiError: default -> " + apiError.getMessage() );
                    getMvpView().showMessageToastyError(error_message);
            }

            getMvpView().showMessageToastyError(error_message);
        } catch (JsonSyntaxException | NullPointerException e) {
            Log.e(TAG, "handleApiError"  + e.getLocalizedMessage());
            getMvpView().showMessageToastyError(R.string.api_default_error);
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
