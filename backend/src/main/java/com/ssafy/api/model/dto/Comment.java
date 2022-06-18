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
public class Comment { 
	private int commentno;
	private int articleno;
	private String userid;
	private String content;
	private String regtime;
}
