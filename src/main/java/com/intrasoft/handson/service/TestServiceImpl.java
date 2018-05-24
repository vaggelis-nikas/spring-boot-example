package com.intrasoft.handson.service;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.intrasoft.handson.entity.Test;
import com.intrasoft.handson.exception.IntlDataException;
import com.intrasoft.handson.repository.TestRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	@Override
	public List<Test> list() throws IntlDataException {

		log.debug("ENTERED list");

		final List<Test> tests;
		try {
			tests = Lists.newArrayList(this.testRepository.findAll());
		} catch (final DataAccessException e) {
			final String errorMessage = MessageFormat.format("Error while listing test objects. Error={0}", e.getMessage());
			throw new IntlDataException(errorMessage, e);
		}

		log.debug("EXITING list [tests={}]", tests);
		return tests;
	}

	@Override
	public Test get(final Integer id) throws IntlDataException {

		log.debug("ENTERED get [id={}]", id);

		final Test test;
		try {
			test = this.testRepository.findById(id).orElse(null);
		} catch (final DataAccessException e) {
			final String errorMessage = MessageFormat.format("Error while getting test object with id={0}. Error={1}", id, e.getMessage());
			throw new IntlDataException(errorMessage, e);
		}

		log.debug("EXITING get [test={}]", test);
		return test;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Test saveOrUpdate(Test test) throws IntlDataException {

		log.debug("ENTERED saveOrUpdate [test={}]", test);

		try {
			test = this.testRepository.save(test);
		} catch (final DataAccessException e) {
			final String errorMessage = MessageFormat.format("Error while creating test object {0}. Error={1}", test, e.getMessage());
			throw new IntlDataException(errorMessage, e);
		}

		log.debug("EXITING saveOrUpdate [test={}]", test);
		return test;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(final Integer id) throws IntlDataException {

		log.debug("ENTERED delete [id={}]", id);

		try {
			this.testRepository.deleteById(id);
		} catch (final DataAccessException e) {
			final String errorMessage = MessageFormat.format("Error while deleting test object with id={0}. Error={1}", id, e.getMessage());
			throw new IntlDataException(errorMessage, e);
		}
		log.debug("EXITING delete");
	}
}
