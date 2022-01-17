package com.basketball.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class GraphQLException extends RuntimeException{

	public GraphQLException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GraphQLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GraphQLException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GraphQLException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public GraphQLException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
