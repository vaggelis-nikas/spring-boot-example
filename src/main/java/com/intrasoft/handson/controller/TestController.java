package com.intrasoft.handson.controller;

import java.util.List;

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
import com.intrasoft.handson.service.TestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

	@Autowired
	private TestService testService;

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

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") final Integer id) throws IntlDataException {

		log.debug("ENTERED delete [id={}]", id);

		this.testService.delete(id);

		log.debug("EXITING delete");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
