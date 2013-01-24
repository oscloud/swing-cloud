package com.cloudfoundry.vmc.exception;

public class CloudException extends Exception {
	
	private static final long serialVersionUID = -7921647446378410131L;

	public CloudException(String message,Throwable e){
		super(message,e);
	}
	
	public CloudException(String message){
		super(message);
	}

}