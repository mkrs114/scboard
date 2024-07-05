package com.msnks.scboard.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

	private int id;
	private String title;
	private String content;
	private int memberid;
	private String regDate;
	private String modDate;
}
