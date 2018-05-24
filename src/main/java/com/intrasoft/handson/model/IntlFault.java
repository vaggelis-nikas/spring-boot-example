package com.intrasoft.handson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class IntlFault {

	Integer error;
	String message;
}
