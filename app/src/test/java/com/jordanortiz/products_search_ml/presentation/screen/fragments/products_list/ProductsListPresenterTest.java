package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductsListPresenterTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void appliedQueryIsEmpty(){
        ProductsListPresenter presenter =  new ProductsListPresenter();
        Assert.assertTrue(presenter.isQueryEmpty(""));
    }

    @Test
    public void appliedQueryIsNotEmpty(){
        ProductsListPresenter presenter =  new ProductsListPresenter();
        Assert.assertFalse(presenter.isQueryEmpty("Moto G6"));
    }
}