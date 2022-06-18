package com.ssafy.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Board {
	private int articleno;
	private String subject;
	private String userid;
	private String content;
	private int hit;
	private String regtime;
	private int commentCnt;
}
