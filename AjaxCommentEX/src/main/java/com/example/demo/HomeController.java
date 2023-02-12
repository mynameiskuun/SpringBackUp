package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {

	private final CommentDAO commentdao;
	
@GetMapping("/")
public String logIn() {
	return "login";
}

@GetMapping("/signup")
public void signUp() {
}

@GetMapping("/myHome")
public String myHome() {
	return "my_home";
}

@GetMapping("/feed")
public void feed() {
}

@GetMapping("/view")
public void view() {
}

@GetMapping("/newPost")
public String posting() {
	return "new_post";
}

@GetMapping("/insertComment")
public String insertComment(CommentDTO commDTO) {
	
	System.out.println("commDTO : " + commDTO.toString());
	commentdao.insertComment(commDTO);
	return "redirect:/view";
}
  
} 
