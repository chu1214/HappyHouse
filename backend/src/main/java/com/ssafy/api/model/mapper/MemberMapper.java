package com.ssafy.api.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.api.model.dto.Interest;
import com.ssafy.api.model.dto.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public Integer insertMember(MemberDto memberDto) throws Exception;
	public Integer modifyMember(MemberDto memberDto) throws Exception;
	public Integer deleteMember(String userid) throws Exception;
	public MemberDto selectMember(String userid) throws Exception;	
	public String findMemberId(MemberDto memberDto) throws Exception;
	public Integer findMemberPass(MemberDto memberDto) throws Exception;
	public Integer registInterest(Interest interest) throws Exception;
	public Integer deleteInterest(String userid, String aptCode) throws Exception;
	public List<Interest> searchInterest(String userid) throws Exception;
}