package com.ssafy.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.model.component.JsoupComponent;
import com.ssafy.api.model.dto.NewsDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private JsoupComponent jsoupComponent;
	
	@Override
	public List<NewsDto> getNewsList() {
		return jsoupComponent.getNewsList();
	}

	@Override
	public List<NewsDto> getNewsBySearch(String search) {
		return jsoupComponent.getNewsBySearch(search);
	}

}
