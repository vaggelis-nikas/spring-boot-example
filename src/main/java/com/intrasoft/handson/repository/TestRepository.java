package com.intrasoft.handson.repository;

import org.springframework.data.repository.CrudRepository;

import com.intrasoft.handson.entity.Test;

public interface TestRepository extends CrudRepository<Test, Integer> {
}
