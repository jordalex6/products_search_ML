package com.jordanortiz.products_search_ml.core.data.manager.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jordanortiz.products_search_ml.core.data.manager.NetworkManager;
import com.jordanortiz.products_search_ml.presentation.di.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NetworkManagerImpl extends BroadcastReceiver implements NetworkManager {

    private Context context;

    private IntentFilter connectivityIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

    private Map<String, Listener> listeners = new HashMap<>();

    @Inject
    public NetworkManagerImpl(@ApplicationContext Context context) {
        this.context = context;
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = Objects.requireNonNull(cm).getActiveNetworkInfo();
        if (null != activeNetwork) {
            return activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }

    @Override
    public void start() {
        context.registerReceiver(this, connectivityIntentFilter);
    }

    @Override
    public void stop() {
        context.unregisterReceiver(this);
    }

    @Override
    public void add(String tag, Listener listener) {
        listeners.put(tag, listener);
    }

    @Override
    public void remove(String tag) {
        listeners.remove(tag);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isNetworkAvailable()) {
            if (!isInitialStickyBroadcast()) {
                for (Listener listener : listeners.values()) {
                    if (listener != null) {
                        listener.onNetworkAvailable();
                    }
                }
            }
        }
    }

}
