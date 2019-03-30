package com.kai.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	
	private String id;
	
	private String username;
	private String phone;
	private String email;
	private String name;
	private  Date birthday;

}
