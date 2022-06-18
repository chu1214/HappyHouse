package com.ssafy.api.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.api.model.dto.Comment;

@Mapper
public interface CommentMapper {
	public List<Comment> selectCommentByArticle(int articleno);
	public Comment selectCommentByNo(int commentno);
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(int commentno);
}
