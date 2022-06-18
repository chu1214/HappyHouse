package com.ssafy.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.model.dto.Interest;
import com.ssafy.api.model.dto.MemberDto;
import com.ssafy.api.model.service.JwtServiceImpl;
import com.ssafy.api.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MemberController V1")
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원가입", notes = "신규 회원 정보를 DB에 저장한다.", response = Integer.class)
	@PostMapping("/regist")
	public ResponseEntity<Integer> registMember(@RequestBody MemberDto memberDto) throws Exception {
		if (memberService.insertMember(memberDto) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원가입", notes = "회원 가입시 아이디 중복여부를 확인한다.", response = Integer.class)
	@GetMapping("/search/{userid}")
	public ResponseEntity<Integer> checkDuplicated(@PathVariable String userid) throws Exception {
		if (memberService.selectMember(userid) != null) { // 중복인 경우
			System.out.println("중복");
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 정보 수정", notes = "수정된 회원 정보를 DB에 저장한다.", response = Integer.class)
	@PutMapping("/modify")
	public ResponseEntity<Integer> modifyMember(@RequestBody MemberDto memberDto) throws Exception {
		if (memberService.modifyMember(memberDto) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원 정보 삭제", notes = "회원 정보를 DB에서 삭제한다.", response = Integer.class)
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<Integer> deleteMember(@PathVariable("userid") String userid) throws Exception {
		if (memberService.deleteMember(userid) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "아이디 찾기", notes = "이름과 이메일을 기반으로 아이디를 찾는다.", response = String.class)
	@PostMapping("/findid")
	public ResponseEntity<String> findMemberId(@RequestBody MemberDto memberDto) throws Exception {
			String userid = memberService.findMemberId(memberDto);
		if (userid != null) {
			return new ResponseEntity<String>(userid, HttpStatus.OK);
		}
		return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "관심목록 등록", notes = "아파트코드와 아이디로 관심아파트를 등록한다", response = Integer.class)
	@PostMapping("/interest/regist")
	public ResponseEntity<Integer> registInterest(
			@RequestBody Interest interest) throws Exception {
			Integer result = memberService.registInterest(interest);
		if (result == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "관심목록 삭제", notes = "아파트코드와 아이디로 관심아파트를 삭제한다.", response = Integer.class)
	@DeleteMapping("/interest/delete")
	public ResponseEntity<Integer> deleteInterest(
			@RequestParam("userid") String userid, @RequestParam("aptCode") String aptCode) throws Exception {
			Integer result = memberService.deleteInterest(userid, aptCode);
		if (result == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "관심목록 조회", notes = "아이디로 관심아파트를 조회한다", response = String.class)
	@GetMapping("/interest/search")
	public ResponseEntity<List<Interest>> searchInterest(
			@RequestParam("userid") String userid) throws Exception {
			List<Interest> interestList = memberService.searchInterest(userid);
			return new ResponseEntity<List<Interest>>(interestList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "비밀번호 찾기", notes = "가입한 이메일로 임시비밀번호를 발급한다.", response = String.class)
	@PostMapping("/findpwd")
	public String findMemberPwd(
			@RequestBody MemberDto memberDto, HttpServletResponse response) throws Exception {
			String userpwd = ""; 
			Integer check = memberService.findMemberPass(memberDto);
		if (check == 1) {
			UUID uid = UUID.randomUUID();
			userpwd = uid.toString().substring(0,8);
			memberDto.setUserpwd(userpwd);
			memberService.modifyMember(memberDto);
			String toMail = memberDto.getEmail();
			String content = userpwd;
			response.sendRedirect("/mail/pwd/findpwdmail/"+toMail+"/"+content+"/");
			return "YES";
		}
		return "NO";
	}
}
