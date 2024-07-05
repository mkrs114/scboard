package com.msnks.scboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msnks.scboard.service.MemberService;
import com.msnks.scboard.util.Ut;
import com.msnks.scboard.vo.Member;
import com.msnks.scboard.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {

	MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 액션 메서드 시작
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId(을)를 입력해주세요");
		}

		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw(을)를 입력해주세요");
		}

		if (Ut.empty(name)) {
			return ResultData.from("F-3", "name(을)를 입력해주세요");
		}

		if (Ut.empty(email)) {
			return ResultData.from("F-4", "email(을)를 입력해주세요");
		}

		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (joinRd.isFail())  {
			return joinRd;
		}
		
		Member member = memberService.getMemberById((int)joinRd.getData1());
		
		return ResultData.newData(joinRd, member);
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData<Member> doLogin(HttpSession httpSession, String loginId, String loginPw ) {
		
		boolean isLogined = false;
	
		if( httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		
		if ( isLogined ) {
			return ResultData.from("F-5", "이미 로그인되었습니다.");
		}

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId(을)를 입력해주세요");
		}

		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw(을)를 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if ( member == null)  {
			return ResultData.from("F-3", "존재하지 않은 로그인 아이디입니다.");
		}
		
		if( member.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
		}
		
		httpSession.setAttribute("loginedMemberId", member.getId());
		
		return ResultData.from("S-1", Ut.f("%s님 환영합니다.", member.getNickname()));
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData<Member> doLogout(HttpSession httpSession ) {
		
		boolean isLogined = true;
	
		if( httpSession.getAttribute("loginedMemberId") == null) {
			isLogined = false;
		}
		
		if ( isLogined == false ) {
			return ResultData.from("F-5", "이미 로그아웃되었습니다.");
		}

		httpSession.removeAttribute("loginedMemberId");
		
		return ResultData.from("S-1", "로그아웃 되었습니다.");
	}
}
