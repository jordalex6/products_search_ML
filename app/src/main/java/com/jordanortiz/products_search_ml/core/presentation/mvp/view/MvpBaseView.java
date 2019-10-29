package com.jordanortiz.products_search_ml.core.presentation.mvp.view;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */

public interface MvpBaseView {

    void showMessage(String message);
    void showMessage(int messageResId);

    void showMessageToastyError(String message);
    void showMessageToastyError(int messageResId);

    void showMessageToastyInfo(String message);
    void showMessageToastyInfo(int messageResId);

    void showMessageToastySuccess(String message);
    void showMessageToastySuccess(int messageResId);

    boolean isNetworkConnected();

    void hideKeyboard();
}
