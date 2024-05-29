package com.ty.ams.exceptionclasses.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	private String message;

}
