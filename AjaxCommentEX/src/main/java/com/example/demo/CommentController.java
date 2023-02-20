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
import com.example.demo.CommentDto;
import com.example.demo.MemberDto;

import jakarta.servlet.http.HttpSession;
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

//대댓글 인서트 메소드

	 @PostMapping("/insertSubComment") 
	 public String insertSubComment(Model model, @RequestParam Map<String, Object> resultMap, HttpSession session) {
	 
		 SubCommentDto subCommDto = new SubCommentDto();
	 
//	 MemberDto ses_user = (MemberDto) session.getAttribute("user");
//	 String loginUserId = ses_user.getMem_id();
//	 
//	 if(loginUserId != null) {
//		 commDto.setMem_id(loginUserId);
//	 } else {
//		 commDto.setMem_id(resultMap.get("mem_id").toString());
//	 }
	 
		 subCommDto.setMem_id("testID");
		 subCommDto.setCom_id(Integer.parseInt((String)(resultMap.get("com_id"))));
		 subCommDto.setSubcom_text(resultMap.get("subcom_text").toString());
	 
	 //model.addAttribute("list", commentdao.selectAllComment(subCommDto.getPost_id()));
	 commentdao.insertSubComment(subCommDto); 
	 
	 return "/view :: #commBox"; 
	 }
  
@GetMapping("/selectAllComment")
public String selectAllComment(Model model, int post_id) {
	
	List<CommentDto> cList = commentdao.selectAllComment(post_id);
	model.addAttribute("list", cList);	
	//int day = commentdao.calculateCommentTime(com_id);
	//model.addAttribute("day", day);
	
	return "/view";
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
@PostMapping("/ajaxTest") 
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
