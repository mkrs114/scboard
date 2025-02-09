package com.msnks.scboard.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private int id;
	private String loginId;
	private String loginPw;
	private String authLevel;
	private String name;
	private String nickname;
	private String cellphoneNo;
	private String email;
	private String delStatus;
	private String regDate;
	private String modDate;
	private String delDate;
	
}
