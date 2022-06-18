package com.ssafy.api.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.api.model.dto.NewsDto;

import lombok.RequiredArgsConstructor;

public interface NewsService {
	public List<NewsDto> getNewsList();
	public List<NewsDto> getNewsBySearch(String search);
	
}
