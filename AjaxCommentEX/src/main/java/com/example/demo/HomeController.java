package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
public void view(Model model) {
	
}

@GetMapping("/newPost")
public String posting() {
	return "new_post";
}

@GetMapping("/insertComment")
public String insertComment(CommentDTO commDTO) {
	
	System.out.println("commDTO : " + commDTO.toString());
	commentdao.insertComment(commDTO);
	return "redirect:/selectAllComment";
}
  
@GetMapping("/selectAllComment")
public String selectAllComment(Model model) {
	
	System.out.println("selectCommentTest");
	List<CommentDTO> cList = commentdao.selectAllComment();
	model.addAttribute("list", cList);
	return "/view";
}

@GetMapping("/delete")
public String deleteComment(int com_id) {
	commentdao.deleteComment(com_id);
	return "redirect:/selectAllComment";
}
} 
