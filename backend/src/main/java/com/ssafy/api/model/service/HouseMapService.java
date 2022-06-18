package com.ssafy.api.model.service;

import java.util.List;

import com.ssafy.api.model.dto.HouseDealDto;
import com.ssafy.api.model.dto.HouseInfoDto;
import com.ssafy.api.model.dto.SidoGugunCodeDto;

public interface HouseMapService {
	List<SidoGugunCodeDto> getSido();
	List<SidoGugunCodeDto> getGugunInSido(String sido);
	List<SidoGugunCodeDto> getDongInGugun(String gugun);
	List<HouseInfoDto> getInfoInRange(String lat1, String lng1, String lat2, String lng2);
	HouseInfoDto getInfoByLatLng(String lat, String lng);
	List<HouseDealDto> getDealByDong(String dong);
	List<HouseDealDto> getDealByLatLng(String lat, String lng);
}
