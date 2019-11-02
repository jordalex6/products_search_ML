package com.jordanortiz.products_search_ml.presentation.di.component;

import com.jordanortiz.products_search_ml.presentation.di.module.ActivityModule;
import com.jordanortiz.products_search_ml.presentation.di.module.ViewModelModule;
import com.jordanortiz.products_search_ml.presentation.di.module.ViewModule;
import com.jordanortiz.products_search_ml.presentation.di.scope.PerActivity;
import com.jordanortiz.products_search_ml.presentation.screen.activities.MainActivity;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.home.HomeFragment;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.ProductDetailFragment;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListFragment;

import dagger.Component;


/**
 * A scope {@link PerActivity} component.
 * Injects specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ViewModule.class, ViewModelModule.class})
public interface ViewComponent extends ActivityComponent{

      void inject(MainActivity activity);

      void inject(HomeFragment fragment);
      void inject(ProductsListFragment fragment);
      void inject(ProductDetailFragment fragment);

}
