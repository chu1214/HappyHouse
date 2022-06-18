package com.ssafy.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.model.dto.NewsDto;
import com.ssafy.api.model.service.NewsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
@CrossOrigin("*")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping
	public List<NewsDto> getNewsList(HttpServletRequest request) {
        return newsService.getNewsList();

    }
	
	@GetMapping("/search/{search}")
    public List<NewsDto> getNewsListS(@PathVariable String search) {
    	return newsService.getNewsBySearch(search);
    }
	
}
