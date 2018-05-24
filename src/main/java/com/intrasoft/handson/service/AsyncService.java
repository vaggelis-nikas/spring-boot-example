package com.intrasoft.handson.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.intrasoft.handson.exception.IntlConcurrencyException;
import com.intrasoft.handson.exception.IntlException;
import com.intrasoft.handson.functional.IntlCallable;
import com.intrasoft.handson.functional.IntlRunnable;

/**
 * The interface Async service.
 */
public interface AsyncService {

	/**
	 * Spawns the execution of a function to a new Thread and not wait for any result (SingleThreadExecutor is used by default). This is a non-blocking operation.
	 *
	 * @param runnable the runnable
	 */
	void submitAndForget(IntlRunnable<IntlException> runnable);

	/**
	 * Spawns the execution of a function to a new Thread and not wait for any result. This is a non-blocking operation.
	 *
	 * @param runnables the runnables
	 * @param executor the executor
	 */
	void submitMultipleAndForget(List<IntlRunnable<IntlException>> runnables, ExecutorService executor);

	/**
	 * Spawns the execution of a function to a new Thread and gets a future result (SingleThreadExecutor is used by default). This is a non-blocking operation.
	 *
	 * @param <T> the type parameter
	 * @param callable the callable
	 *
	 * @return the future
	 */
	<T> Future<T> submitAndGetPromise(IntlCallable<T, IntlException> callable);

	/**
	 * Spawns the execution of a function to a new Thread and gets an actual result. This is a blocking operation.
	 *
	 * @param <T> the type parameter
	 * @param callable the callable
	 * @param executor the executor
	 *
	 * @return the t
	 *
	 * @throws IntlConcurrencyException the intl concurrency exception
	 */
	<T> T submitAndGetResult(IntlCallable<T, IntlException> callable, ExecutorService executor) throws IntlConcurrencyException;

}
