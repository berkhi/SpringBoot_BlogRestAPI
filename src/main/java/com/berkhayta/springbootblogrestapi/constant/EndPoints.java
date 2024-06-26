package com.berkhayta.springbootblogrestapi.constant;

public class EndPoints {
	public static final String VERSION="/v1";
	//profiller:
	public static final String API="/api";
	public static final String DEV="/dev";
	public static final String TEST="/test";
	
	public static final String ROOT=API+VERSION;
	
	
	//entities:
	public static final String POST="/post";
	public static final String CATEGORY="/category";
	public static final String USER="/user";
	//methods:
	public static final String SAVE="/save";
	public static final String UPDATE="/update";
	public static final String DELETE="/delete";
	public static final String FINDALL_DTO="/findAllDto";
	public static final String FINDBYID="/findByIdDto";

	//POST methods
	public static final String FINDPOSTBYID="/posts/{id}";
	public static final String UPDATEPOSTBYID="/posts/{id}";

}