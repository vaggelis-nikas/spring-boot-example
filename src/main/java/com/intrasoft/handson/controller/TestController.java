package com.intrasoft.handson.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intrasoft.handson.entity.Test;
import com.intrasoft.handson.exception.IntlDataException;
import com.intrasoft.handson.exception.IntlException;
import com.intrasoft.handson.service.AsyncService;
import com.intrasoft.handson.service.TestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private AsyncService asyncService;

	@GetMapping
	public ResponseEntity<List<Test>> list() throws IntlDataException {

		log.debug("ENTERED list");

		final List<Test> tests = this.testService.list();

		log.debug("EXITING list [tests={}]", tests);
		return new ResponseEntity<>(tests, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Test> get(@PathVariable final Integer id) throws IntlDataException {

		log.debug("ENTERED get [id={}]", id);

		final Test test = this.testService.get(id);

		log.debug("EXITING get [test={}]", test);
		return new ResponseEntity<>(test, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Test> saveOrUpdate(@RequestBody @Valid Test test) throws IntlDataException {

		log.debug("ENTERED saveOrUpdate [test={}]", test);

		test = this.testService.saveOrUpdate(test);

		log.debug("EXITING saveOrUpdate [test={}]", test);
		return new ResponseEntity<>(test, HttpStatus.OK);
	}

	@PostMapping("async")
	public ResponseEntity<Test> saveOrUpdateAsync(@RequestBody @Valid Test test) throws IntlDataException {

		log.debug("ENTERED saveOrUpdateAsync [test={}]", test);

		test = this.testService.saveOrUpdateAsync(test);

		log.debug("EXITING saveOrUpdateAsync [test={}]", test);
		return new ResponseEntity<>(test, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") final Integer id) throws IntlDataException {

		log.debug("ENTERED delete [id={}]", id);

		this.testService.delete(id);

		log.debug("EXITING delete");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("async/{id}")
	public ResponseEntity<Void> deleteAsync(@PathVariable("id") final Integer id) throws IntlDataException {

		log.debug("ENTERED deleteAsync [id={}]", id);

		final ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			try {
				this.testService.delete(id);
			} catch (final IntlException e) {
				final String errorMessage = MessageFormat.format("A general error occurred while executing async job [Error={0}]", e.getMessage());
				log.error(errorMessage, e);
				throw new RuntimeException(e);
			} finally {
				executor.shutdown();
			}
		});

		this.asyncService.submitAndForget(() -> this.testService.delete(id));

		log.debug("EXITING deleteAsync");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
