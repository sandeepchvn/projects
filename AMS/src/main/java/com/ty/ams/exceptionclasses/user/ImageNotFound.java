package com.ty.ams.exceptionclasses.user;

public class ImageNotFound extends RuntimeException{

	public String getMessage() {
		return "image does not exists";
	}
}
