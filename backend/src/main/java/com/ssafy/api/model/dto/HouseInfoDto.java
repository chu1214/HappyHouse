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
public class HouseInfoDto {
	private String aptCode;
	private String buildYear;
	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String dongCode;
	private String dong;
	private String apartmentName;
	private String lng;
	private String lat;
	private	String recentDate;
	private String dealAmount;
	private String area;
	private String floor;
}
