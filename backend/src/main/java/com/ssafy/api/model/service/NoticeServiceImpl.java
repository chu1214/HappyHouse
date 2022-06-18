package com.ssafy.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.model.dto.Board;
import com.ssafy.api.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
	private NoticeMapper noticeMapper;

    @Override
	public List<Board> retrieveNotice() {
		return noticeMapper.selectNotice();
	}

	@Override
	public Board detailNotice(int articleno) {
		return noticeMapper.selectNoticeByNo(articleno);
	}

	@Override
	public int writeNotice(Board board) {
		return noticeMapper.insertNotice(board);
	}

	@Override
	public int updateNotice(Board board) {
		return noticeMapper.updateNotice(board);
	}

	@Override
	public int deleteNotice(int articleno) {
		return noticeMapper.deleteNotice(articleno);
	}

	@Override
	public List<Board> detailNoticeBySubject(String subject) {
		return noticeMapper.selectNoticeBySubject(subject);
	}

	

}
