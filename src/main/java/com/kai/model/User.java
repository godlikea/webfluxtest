package com.kai.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class User {
	
	@Id
	private String id;
	
	@Indexed(unique=true)
	private String username;
	private String phone;
	private String email;
	private String name;
	private  Date birthday;

}
