package com.jordanortiz.products_search_ml.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.jordanortiz.products_search_ml.presentation.di.ApplicationContext;
import com.jordanortiz.products_search_ml.presentation.di.scope.PreferenceInfo;

import javax.inject.Inject;

public class PreferencesHelper implements  IPreferencesHelper {

    private static final String PREF_KEY_CURRENT_PRODUCT_RESPONSE = "PREF_KEY_CURRENT_PRODUCT_RESPONSE";

    private final SharedPreferences mPrefs;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context,
                             @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getCurrentProductsResponse() {
        return mPrefs.getString(PREF_KEY_CURRENT_PRODUCT_RESPONSE, null);
    }

    @Override
    public void setCurrentProductsResponse(String response) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_PRODUCT_RESPONSE, response).apply();
    }
}
