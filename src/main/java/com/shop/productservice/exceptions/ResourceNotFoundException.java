package com.shop.productservice.exceptions;

public class ResourceNotFoundException extends RuntimeException
{

	public ResourceNotFoundException() 
	{
		super("Failed to find resource");
	}
	
	public ResourceNotFoundException(String msg)
	{
		super(msg);
	}
}
