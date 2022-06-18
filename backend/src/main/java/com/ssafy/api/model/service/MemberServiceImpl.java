package com.ssafy.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.model.dto.Interest;
import com.ssafy.api.model.dto.MemberDto;
import com.ssafy.api.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public Integer insertMember(MemberDto memberDto) throws Exception {
		return memberMapper.insertMember(memberDto);
	}

	@Override
	public Integer modifyMember(MemberDto memberDto) throws Exception {
		return memberMapper.modifyMember(memberDto);
	}

	@Override
	public Integer deleteMember(String userid) throws Exception {
		return memberMapper.deleteMember(userid);
	}
	
	@Override
	public MemberDto selectMember(String userid) throws Exception {
		return memberMapper.selectMember(userid);
	}

	@Override
	public String findMemberId(MemberDto memberDto) throws Exception {
		return memberMapper.findMemberId(memberDto);
	}

	@Override
	public Integer findMemberPass(MemberDto memberDto) throws Exception {
		return memberMapper.findMemberPass(memberDto);
	}

	@Override
	public Integer registInterest(Interest interest) throws Exception {
		return memberMapper.registInterest(interest);
	}

	@Override
	public Integer deleteInterest(String userid, String aptCode) throws Exception {
		return memberMapper.deleteInterest(userid, aptCode);
	}

	@Override
	public List<Interest> searchInterest(String userid) throws Exception {
		return memberMapper.searchInterest(userid);
	}

	

}
