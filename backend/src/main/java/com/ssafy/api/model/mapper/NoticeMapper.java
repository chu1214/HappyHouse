package com.ssafy.api.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.api.model.dto.Board;

@Mapper
public interface NoticeMapper {
	public List<Board> selectNotice();
	public Board selectNoticeByNo(int articleno);
	public List<Board> selectNoticeBySubject(String subject);
	public int insertNotice(Board board);
	public int updateNotice(Board board);
	public int deleteNotice(int articleno);
}
