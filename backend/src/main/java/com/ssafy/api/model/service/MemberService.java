package com.ssafy.api.model.service;

import java.util.List;

import com.ssafy.api.model.dto.Interest;
import com.ssafy.api.model.dto.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
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
