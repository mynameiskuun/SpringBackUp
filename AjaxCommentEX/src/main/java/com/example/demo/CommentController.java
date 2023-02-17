package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.asd.CommentDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentController {

	private final CommentDao commentdao;
	
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
public String view(Model model) {
	
	int post_id = 2;
	List<CommentDto> cList = commentdao.selectAllComment(post_id);
	model.addAttribute("list", cList);
	
	return "/view";
//	for (int i = 0 ; i<cList.size(); i++) {
//		//tList.add(cList.get(i).getCom_time());
//		int a = commentdao.calculateCommentTime(cList.get(i).getCom_id());
//		tList.add(a);
//	}
}

@GetMapping("/newPost")
public String posting() {
	return "new_post";
}

@PostMapping("/insertComment")
public String insertComment(Model model, @RequestParam Map<String, Object> resultMap) {
	
	CommentDto commDto = new CommentDto();
	
	commDto.setMem_id(resultMap.get("mem_id").toString());
	commDto.setPost_id(Integer.parseInt((String)(resultMap.get("post_id"))));
	commDto.setCom_text(resultMap.get("com_text").toString());
	commentdao.insertComment(commDto);
	
	/*
	 * model.addAttribute("list",
	 * commentdao.selectAllComment(commDto.getPost_id()));
	 * commentdao.insertComment(commDto);
	 */
	
	return "/view :: #commBox";
}

@PostMapping("/insertSubComment")
public String insertSubComment(Model model, @RequestParam Map<String, Object> resultMap) {
	
	CommentDto commDto = new CommentDto();
	
	commDto.setMem_id(resultMap.get("mem_id").toString());
	commDto.setPost_id(Integer.parseInt((String)(resultMap.get("post_id"))));
	commDto.setCom_text(resultMap.get("com_text").toString());
	
	model.addAttribute("list", commentdao.selectAllComment(commDto.getPost_id()));
	commentdao.insertComment(commDto);
	return "/view :: #commBox"; 
}
  
@GetMapping("/selectAllComment")
public String selectAllComment(Model model, int post_id) {
	
	System.out.println("selectCommentTest");
	List<CommentDto> cList = commentdao.selectAllComment(post_id);
	model.addAttribute("list", cList);
	//int day = commentdao.calculateCommentTime(com_id);
	//model.addAttribute("day", day);
	
	return "redirect:/view";
}

@GetMapping("/deleteComment")
public String deleteComment(int com_id) {
	
	System.out.println("com_id : " + com_id);
	
	try {
		commentdao.deleteComment(com_id);
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	return "redirect:/view";
}

@ResponseBody
@PostMapping("/test") 
public String test1() {
	
	String content = "";
	return "테스트용 메소드";
}


/*
 * @GetMapping("/calculateCommentTime") public String calculateCommentTime(Model
 * model, int com_id) {
 * 
 * int day = commentdao.calculateCommentTime(com_id); model.addAttribute("day",
 * day); return "redirect:/selectAllComment"; }
 */
}
