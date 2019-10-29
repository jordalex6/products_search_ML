package com.jordanortiz.products_search_ml.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
