package com.adpassignment.billchange.customexecption;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Global Exception handling class
 * @author Savita Rohra
 *
 */
@ControllerAdvice
public class GlobalException{
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public @ResponseBody String MissingServletRequestParameterException(Exception exception, WebRequest webRequest){
		return "Error!! Please provide valid input parameter";	    
	}
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public @ResponseBody String handleTypeMismatch(Exception exception, WebRequest webRequest){
		System.out.println("In Global Exception handler");
		return "Error!! Please enter valid input";
	    
	}

}
