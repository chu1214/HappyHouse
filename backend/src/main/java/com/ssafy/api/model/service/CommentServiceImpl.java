package com.ssafy.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.model.dto.Comment;
import com.ssafy.api.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService{
	
    @Autowired
	private CommentMapper commentMapper;
	
	@Override
	public List<Comment> retrieveComment(int articleno) {
		return commentMapper.selectCommentByArticle(articleno);
	}

	@Override
	public Comment selectComment(int commentno) {
		return commentMapper.selectCommentByNo(commentno);
	}

	@Override
	public int writeComment(Comment comment) {
		return commentMapper.insertComment(comment);
	}

	@Override
	public int updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentMapper.updateComment(comment);
	}

	@Override
	public int deleteComment(int commentno) {
		return commentMapper.deleteComment(commentno);
	}

}
