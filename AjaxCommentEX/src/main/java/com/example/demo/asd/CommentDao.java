package com.example.demo.asd;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.CommentDto;
import com.example.demo.PostDTO;
import com.example.demo.SubCommentDto;

@Mapper
public interface CommentDao {
	public int insertComment(CommentDto commentDTO);
	public int insertSubComment(SubCommentDto subCommentDTO);
	public List<CommentDto> selectAllComment(int post_id);
	public int deleteComment(int com_id);
	public int calculateCommentTime(int com_id);
	public List<PostDTO> selectAllPost();
}
