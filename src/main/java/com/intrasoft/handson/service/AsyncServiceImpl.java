package com.intrasoft.handson.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.intrasoft.handson.exception.IntlConcurrencyException;
import com.intrasoft.handson.exception.IntlException;
import com.intrasoft.handson.functional.IntlCallable;
import com.intrasoft.handson.functional.IntlRunnable;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

	private void submitAndForget(final IntlRunnable<IntlException> runnable, final ExecutorService executor) {

		executor.submit(() -> {
			try {
				runnable.run();
			} catch (final IntlException e) {
				final String errorMessage = MessageFormat.format("A general error occurred while executing async job [Error={0}]", e.getMessage());
				log.error(errorMessage, e);
				throw new RuntimeException(e);
			} finally {
				executor.shutdown();
			}
		});
	}

	/** {@inheritDoc} */
	@Override
	public void submitAndForget(final IntlRunnable<IntlException> runnable) {

		submitAndForget(runnable, Executors.newSingleThreadExecutor());
	}

	/** {@inheritDoc} */
	@Override
	public void submitMultipleAndForget(final List<IntlRunnable<IntlException>> runnables, final ExecutorService executor) {

		runnables.forEach(runnable -> submitAndForget(runnable, executor));
	}

	private <T> Future<T> submitAndGetPromise(final IntlCallable<T, IntlException> callable, final ExecutorService executor) {

		try {
			return executor.submit(callable::call);
		} finally {
			executor.shutdown();
		}
	}

	/** {@inheritDoc} */
	@Override
	public <T> Future<T> submitAndGetPromise(final IntlCallable<T, IntlException> callable) {

		return submitAndGetPromise(callable, Executors.newSingleThreadExecutor());
	}

	/** {@inheritDoc} */
	@Override
	public <T> T submitAndGetResult(final IntlCallable<T, IntlException> callable, final ExecutorService executor) throws IntlConcurrencyException {

		try {
			return submitAndGetPromise(callable, executor).get();
		} catch (final InterruptedException | ExecutionException e) {
			final String errorMessage = MessageFormat.format("Concurrency error {0}.", e.getMessage());
			throw new IntlConcurrencyException(errorMessage);
		}
	}

}
