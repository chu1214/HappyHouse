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
public class Interest {
	private int no;
	private String aptCode;
	private String userid;
	private String apartmentName;
	private String lat;
	private String lng;
}
