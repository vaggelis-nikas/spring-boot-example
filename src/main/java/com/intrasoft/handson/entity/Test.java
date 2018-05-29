package com.intrasoft.handson.entity;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ATS_TEST")
@Cacheable
@Getter
@Setter
@ToString
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	@NotEmpty
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CREATED_AT")
	private Date createdAt;

}
