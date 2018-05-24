package com.intrasoft.handson.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.intrasoft.handson.exception.IntlException;
import com.intrasoft.handson.model.IntlFault;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(basePackages = {"com.intrasoft"})
@Slf4j
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IntlException.class)
	@ResponseBody
	protected ResponseEntity<IntlFault> handleChecked(final HttpServletRequest request, final Throwable ex) {

		log.error("Checked Exception {}", ex.getMessage());

		final HttpStatus status = getStatus(request);
		return new ResponseEntity<>(new IntlFault(status.value(), ex.getMessage()), status);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	protected ResponseEntity<IntlFault> handleUnchecked(final HttpServletRequest request, final Throwable ex) {

		log.error("Unchecked Exception {}", ex.getMessage(), ex);

		final HttpStatus status = getStatus(request);
		return new ResponseEntity<>(new IntlFault(status.value(), ex.getMessage()), status);
	}

	private HttpStatus getStatus(final HttpServletRequest request) {

		final Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
}