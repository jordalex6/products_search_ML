package com.jordanortiz.products_search_ml.core.presentation.mvp.presenter;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.data.ApiError;
import com.jordanortiz.products_search_ml.core.utilities.AppConstants;

import javax.net.ssl.HttpsURLConnection;


public abstract class BasePresenter extends ViewModel implements IBasePresenter {

    private static final String TAG = BasePresenter.class.getSimpleName();

    protected BasePresenter() {

    }


    @Override
    public int handleApiError(ANError error) {
        if (error == null) {
            return R.string.api_default_error;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
            return R.string.connection_error;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            return R.string.api_retry_error;
        }

        final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        try {
            int error_message = R.string.api_default_error;

            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);
            if (apiError != null && apiError.getMessage() != null) {
//              TODO cambiar String por Recursos String en capa de datos.
                return R.string.api_default_error;
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
            }

            return error_message;
        } catch (JsonSyntaxException | NullPointerException e) {
            Log.e(TAG, "handleApiError"  + e.getLocalizedMessage());
            return R.string.api_default_error;
        }
    }

}
