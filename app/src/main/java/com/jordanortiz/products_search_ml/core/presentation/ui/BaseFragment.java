package com.jordanortiz.products_search_ml.core.presentation.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.jordanortiz.products_search_ml.core.presentation.mvp.view.MvpBaseView;
import com.jordanortiz.products_search_ml.presentation.di.component.ViewComponent;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements MvpBaseView {

    private static final String TAG = BaseFragment.class.getSimpleName();


    private Context mContext;
    private BaseActivity mActivity;
    private Unbinder mUnBinder;

    private OnStartFragmentListener mCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();

            mCallback = (OnStartFragmentListener)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public ViewComponent getViewComponent(){
        if (mActivity != null) {
            return mActivity.getViewComponent();
        }
        return null;
    }

    public void setupTitleToolbar(String title) {
        mCallback.setupTitleToolbar(title);
    }

    public void setupDrawerEnabled(Boolean flag) {
        mCallback.setupDrawerEnabled(flag);
    }

    @Override
    public void showMessageToastyError(String message) {
        if (mActivity != null) {
            mActivity.showMessageToastyError(message);
        }
    }

    @Override
    public void showMessageToastyError(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessageToastyError(resId);
        }
    }

    @Override
    public void showMessageToastyInfo(String message) {
        if (mActivity != null) {
            mActivity.showMessageToastyInfo(message);
        }
    }

    @Override
    public void showMessageToastyInfo(int messageResId) {
        if (mActivity != null) {
            mActivity.showMessageToastyInfo(messageResId);
        }
    }

    @Override
    public void showMessageToastySuccess(String message) {
        if (mActivity != null) {
            mActivity.showMessageToastySuccess(message);
        }
    }

    @Override
    public void showMessageToastySuccess(int messageResId) {
        if (mActivity != null) {
            mActivity.showMessageToastySuccess(messageResId);
        }
    }

    @Override
    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    protected abstract void setUp(View view);

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        mActivity = null;
        super.onDestroy();
    }

    protected static Intent getBaseStartIntent(Context context, Class<? extends BaseActivity> activityClass, boolean clearStack) {
        Intent intent = new Intent(context, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (clearStack) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        return intent;
    }


    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
