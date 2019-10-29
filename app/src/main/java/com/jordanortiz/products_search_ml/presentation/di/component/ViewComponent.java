package com.jordanortiz.products_search_ml.presentation.di.component;

import com.jordanortiz.products_search_ml.presentation.di.module.ActivityModule;
import com.jordanortiz.products_search_ml.presentation.di.module.ViewModule;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;
import com.jordanortiz.products_search_ml.presentation.screen.activities.MainActivity;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.home.HomeFragment;

import dagger.Component;


/**
 * A scope {@link PerActivity} component.
 * Injects specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ViewModule.class})
public interface ViewComponent extends ActivityComponent{

      void inject(MainActivity activity);

      void inject(HomeFragment fragment);

}
