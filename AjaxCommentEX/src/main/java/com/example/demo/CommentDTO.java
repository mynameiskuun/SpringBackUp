package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {

	
	private int com_id;
	private String mem_id;
	private int post_id;
	private String com_text;
	private String com_time;
	private String up_time;
	
}
