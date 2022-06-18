package com.ssafy.api.model.service;

import java.util.List;

import com.ssafy.api.model.dto.Board;

public interface NoticeService {
	public List<Board> retrieveNotice();
	public Board detailNotice(int articleno);
	public List<Board> detailNoticeBySubject(String subject);
	public int writeNotice(Board board);
	public int updateNotice(Board board);
	public int deleteNotice(int articleno);
}
