package com.ssafy.api.model.service;

import java.util.List;

import com.ssafy.api.model.dto.Comment;

public interface CommentService {
	public List<Comment> retrieveComment(int articleno);
	public Comment selectComment(int commentno);
	public int writeComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(int commentno);
}
