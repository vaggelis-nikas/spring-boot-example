package com.intrasoft.handson.functional;

import com.intrasoft.handson.exception.IntlException;

@FunctionalInterface
public interface IntlRunnable<E extends IntlException> {

	void run() throws E;
}
