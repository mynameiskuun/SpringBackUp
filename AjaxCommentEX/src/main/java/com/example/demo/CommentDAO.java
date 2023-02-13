package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDAO {

	public int insertComment(CommentDTO commentDTO);
	public List<CommentDTO> selectAllComment();
	public int deleteComment(int com_id);
}
