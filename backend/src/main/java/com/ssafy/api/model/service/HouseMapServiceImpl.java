package com.ssafy.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.model.dto.HouseDealDto;
import com.ssafy.api.model.dto.HouseInfoDto;
import com.ssafy.api.model.dto.SidoGugunCodeDto;
import com.ssafy.api.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService{
	
	@Autowired
	HouseMapMapper houseMapMapper;
	
	@Override
	public List<SidoGugunCodeDto> getSido() {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<SidoGugunCodeDto> getDongInGugun(String gugun) {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getInfoInRange(String lat1, String lng1, String lat2, String lng2) {
		return houseMapMapper.getInfoInRange(lat1, lng1, lat2, lng2);
	}

	@Override
	public HouseInfoDto getInfoByLatLng(String lat, String lng) {
		return houseMapMapper.getInfoByLatLng(lat, lng);
	}

	@Override
	public List<HouseDealDto> getDealByDong(String dong) {
		return houseMapMapper.getDealByDong(dong);
	}

	@Override
	public List<HouseDealDto> getDealByLatLng(String lat, String lng) {
		return houseMapMapper.getDealByLatLng(lat, lng);
	}

}
