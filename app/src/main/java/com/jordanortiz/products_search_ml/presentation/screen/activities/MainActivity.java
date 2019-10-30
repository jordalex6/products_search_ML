package com.jordanortiz.products_search_ml.presentation.screen.activities;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.core.presentation.ui.BaseActivity;
import com.jordanortiz.products_search_ml.core.presentation.ui.OnStartFragmentListener;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.home.HomeFragment;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.ProductsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements  OnStartFragmentListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.toolbar_title)
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        setUp();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        initHomeFragment();
    }

    public void initHomeFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(ProductsListFragment.TAG)
                .replace(
                        R.id.frame_container,
                        ProductsListFragment.newInstance("MLA1055"), /* Tecnology */
                        ProductsListFragment.TAG
                )
                .commit();
    }


    public void replaceFragment(Fragment fragment, String tag) {
        //Get current fragment placed in container
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);

        //Prevent adding same fragment on top
        assert currentFragment != null;
        if (currentFragment.getClass() == fragment.getClass()) {
            return;
        }
        //If fragment is already on stack, we can pop back stack to prevent stack infinite growth
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            //return;
        }

        //Otherwise, just replace fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_left, R.anim.slide_right);
        transaction.replace(R.id.frame_container, fragment, tag);
        assert fragment.getTag() != null;
        if(!fragment.getTag().equals(HomeFragment.TAG))
            transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();
        if (fragmentsInStack > 1) { // If we have more than one fragment, pop back stack
            getSupportFragmentManager().popBackStack();
        } else if (fragmentsInStack == 1) { // Finish activity, if only one fragment left, to prevent leaving empty screen
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentAttached() {
        super.onFragmentAttached();
    }

    @Override
    public void onFragmentDetached(String tag) {
        Log.e(TAG, "onFragmentDetached: ");
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupTitleToolbar(String title) {
        if(txtTitle != null)
            txtTitle.setText(title);
    }

    @Override
    public void setupDrawerEnabled(Boolean flag) {

    }

}
