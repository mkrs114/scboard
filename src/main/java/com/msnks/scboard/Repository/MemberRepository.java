package com.msnks.scboard.Repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.msnks.scboard.vo.Member;

@Mapper
public interface MemberRepository {

	public void join (String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) ;

	public int getLastInsertID();

	public Member getMemberById(int id);

	public Member getMemberByLoginId(String loginId);

	public Member getMemberByNameAndEmail(String name, String email);
}
