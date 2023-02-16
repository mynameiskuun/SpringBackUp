package com.example.demo;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDTO {

	private int post_id;
	private String mem_id;
	private String content;
	private Timestamp posttime;
	private String uptime;
	
}
