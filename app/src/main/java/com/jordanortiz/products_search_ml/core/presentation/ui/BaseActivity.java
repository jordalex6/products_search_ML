package com.jordanortiz.products_search_ml.core.presentation.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.jordanortiz.products_search_ml.ProductSearchMLApplication;
import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.presentation.mvp.view.MvpBaseView;
import com.jordanortiz.products_search_ml.core.presentation.util.ProgressDialogHelper;
import com.jordanortiz.products_search_ml.presentation.di.component.ApplicationComponent;
import com.jordanortiz.products_search_ml.presentation.di.component.DaggerViewComponent;
import com.jordanortiz.products_search_ml.presentation.di.component.ViewComponent;
import com.jordanortiz.products_search_ml.presentation.di.module.ActivityModule;
import com.jordanortiz.products_search_ml.presentation.di.module.ViewModule;

import java.util.Objects;

import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

public abstract class BaseActivity extends AppCompatActivity
    implements MvpBaseView, BaseFragment.Callback {

        private ViewComponent mViewComponent;

        private Unbinder mUnBinder;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            initializeInjector();
            init();
        }

        public ViewComponent getViewComponent() {
            return mViewComponent;
        }

        private void initializeInjector() {
            this.mViewComponent = DaggerViewComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .activityModule(getActivityModule())
                    .viewModule(getViewModule())
                    .build();
        }

        /**
         * Get the Main Application component for dependency injection.
         *
         * @return {@link ApplicationComponent}
         */
        protected ApplicationComponent getApplicationComponent() {
            return ((ProductSearchMLApplication) getApplication()).getApplicationComponent();
        }

        /**
         * Get an Activity module for dependency injection.
         *
         * @return {@link ViewModule}
         */
        protected ViewModule getViewModule() {
            return new ViewModule(this);
        }
        /**
         * Get an Activity module for dependency injection.
         *
         * @return {@link ActivityModule}
         */
        protected ActivityModule getActivityModule() {
            return new ActivityModule(this);
        }


        private void init() {

        }


        @TargetApi(Build.VERSION_CODES.M)
        public void requestPermissionsSafely(String[] permissions, int requestCode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, requestCode);
            }
        }

        @TargetApi(Build.VERSION_CODES.M)
        public boolean hasPermission(String permission) {
            return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                    checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }


        private void showSnackBar(String message) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    message, Snackbar.LENGTH_SHORT);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView
                    .findViewById(R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            snackbar.show();
        }

        @Override
        public void showMessage(String message) {
            showSnackBar(message);
        }

        @Override
        public void showMessage(int messageResId) {
            showMessage(getString(messageResId));
        }

        @Override
        public void showMessageToastyError(String message) {
            Toasty.error(this,message, Toast.LENGTH_SHORT, true).show();
        }

        @Override
        public void showMessageToastyError(int messageResId) {
            showMessageToastyError(getString(messageResId));
        }

        @Override
        public void showMessageToastyInfo(String message) {
            Toasty.info(this,message,Toast.LENGTH_SHORT, true).show();
        }

        @Override
        public void showMessageToastyInfo(int messageResId) {
            showMessageToastyInfo(getString(messageResId));
        }

        @Override
        public void showMessageToastySuccess(String message) {
            Toasty.success(this,message,Toast.LENGTH_SHORT, true).show();
        }

        @Override
        public void showMessageToastySuccess(int messageResId) {
            showMessageToastySuccess(getString(messageResId));
        }

        @Override
        public boolean isNetworkConnected() {
//            return NetworkUtils.isNetworkConnected(getApplicationContext());
            return true;
        }

        @Override
        public void onFragmentAttached() {

        }

        @Override
        public void onFragmentDetached(String tag) {

        }

        public void hideKeyboard() {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

        public void setUnBinder(Unbinder unBinder) {
            mUnBinder = unBinder;
        }

        @Override
        protected void onDestroy() {
            if (mUnBinder != null) {
                mUnBinder.unbind();
            }
            super.onDestroy();
        }

        protected abstract void setUp();
}
