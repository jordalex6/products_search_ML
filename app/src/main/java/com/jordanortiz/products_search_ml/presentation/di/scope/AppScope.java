package com.jordanortiz.products_search_ml.presentation.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;
import javax.inject.Singleton;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Scope
@Retention(RUNTIME)
@Singleton
public @interface AppScope {
}
