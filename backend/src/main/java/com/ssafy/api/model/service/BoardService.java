package com.ssafy.api.model.service;

import java.util.List;

import com.ssafy.api.model.dto.Board;

public interface BoardService {
	public List<Board> retrieveBoard();
	public Board detailBoard(int articleno);
	public List<Board> detailBoardBySubject(String subject);
	public int writeBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(int articleno);
}
