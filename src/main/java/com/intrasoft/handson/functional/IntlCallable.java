package com.intrasoft.handson.functional;

import com.intrasoft.handson.exception.IntlException;

@FunctionalInterface
public interface IntlCallable<T, E extends IntlException> {

	T call() throws E;
}
