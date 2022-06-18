package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.model.dto.HouseDealDto;
import com.ssafy.api.model.dto.HouseInfoDto;
import com.ssafy.api.model.dto.SidoGugunCodeDto;
import com.ssafy.api.model.service.HouseMapService;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	@Autowired
	HouseMapService houseMapService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunCodeDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	// 지도 로딩 시 범위 내 아파트 조회
	@GetMapping("/searchinfo/range")
	public ResponseEntity<List<HouseInfoDto>> getInfoInRange(@RequestParam String lat1, @RequestParam String lng1, @RequestParam String lat2, @RequestParam String lng2) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getInfoInRange(lat1, lng1, lat2, lng2), HttpStatus.OK);
	}
	
	// 마커 클릭 시 해당 아파트 정보 조회
	@GetMapping("/searchinfo/latlng")
	public ResponseEntity<HouseInfoDto> getInfoByLatLng(@RequestParam String lat,
			@RequestParam String lng) {
		return new ResponseEntity<HouseInfoDto>(houseMapService.getInfoByLatLng(lat, lng), HttpStatus.OK);
	}
	
	// 마커 클릭 시 같은 동네 아파트 매매 가격 조회
	@GetMapping("/searchdeal/{dongCode}")
	public ResponseEntity<List<HouseDealDto>> getDealByDong(@PathVariable("dongCode") String dong) throws Exception {
		return new ResponseEntity<List<HouseDealDto>>(houseMapService.getDealByDong(dong), HttpStatus.OK);
	}
		
	// 마커 클릭 시 해당 아파트 거래 내역 조회
	@GetMapping("/searchdeal/latlng")
	public ResponseEntity<List<HouseDealDto>> getDealByLatLng(@RequestParam String lat,
				@RequestParam String lng) {
		return new ResponseEntity<List<HouseDealDto>>(houseMapService.getDealByLatLng(lat, lng), HttpStatus.OK);
	}

}
