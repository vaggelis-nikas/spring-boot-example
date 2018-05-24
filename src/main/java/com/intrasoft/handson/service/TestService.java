package com.intrasoft.handson.service;

import java.util.List;

import com.intrasoft.handson.entity.Test;
import com.intrasoft.handson.exception.IntlDataException;

public interface TestService {

	List<Test> list() throws IntlDataException;

	Test get(Integer id) throws IntlDataException;

	Test saveOrUpdate(Test test) throws IntlDataException;

	void delete(Integer id) throws IntlDataException;
}
