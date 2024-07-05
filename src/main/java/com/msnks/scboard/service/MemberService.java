package com.msnks.scboard.service;

import org.springframework.stereotype.Service;

import com.msnks.scboard.Repository.MemberRepository;
import com.msnks.scboard.util.Ut;
import com.msnks.scboard.vo.Member;
import com.msnks.scboard.vo.ResultData;

@Service
public class MemberService {

	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		
        Member oldMember = getMemberByLoginId(loginId);
        if( oldMember != null) {
        	return ResultData.from("F-7", Ut.f("해당 로그인아이디(%s)는 이미 사용중입니다.", loginId));
        }
        
        // Name & Email Check
        oldMember = getMemberByNameAndEmail(name, email);
        if( oldMember != null) {
        	return ResultData.from("F-7", Ut.f("해당 이름(%s)과 이메일(%s)은(는) 이미 사용중입니다.", name, email));
        }

        memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
        int id = memberRepository.getLastInsertID();

		return ResultData.from("S-1", "회원가입이 완료되었습니다.", id);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email );
	}

	public Member getMemberByLoginId(String loginId) {
		
		return memberRepository.getMemberByLoginId(loginId);
	}

	public int getLastInsertID() {
		return memberRepository.getLastInsertID();
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

}
