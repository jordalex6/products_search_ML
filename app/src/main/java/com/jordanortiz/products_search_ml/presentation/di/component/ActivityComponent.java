package com.jordanortiz.products_search_ml.presentation.di.component;

import androidx.appcompat.app.AppCompatActivity;

import com.jordanortiz.products_search_ml.presentation.di.module.ActivityModule;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    AppCompatActivity activity();

}
