package com.ssafy.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.model.dto.Board;
import com.ssafy.api.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
	private BoardMapper boardMapper;

	
	@Override
	public List<Board> retrieveBoard() {
		return boardMapper.selectBoard();
	}

	@Override
	public Board detailBoard(int articleno) {
		return boardMapper.selectBoardByNo(articleno);
	}

	@Override
	public int writeBoard(Board board) {
		return boardMapper.insertBoard(board);
	}

	@Override
	public int updateBoard(Board board) {
		return boardMapper.updateBoard(board);
	}

	@Override
	public int deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno);
	}

	@Override
	public List<Board> detailBoardBySubject(String subject) {
		return boardMapper.selectBoardBySubject(subject);
	}

}
