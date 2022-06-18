package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.model.dto.Board;
import com.ssafy.api.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	// 전체 Notice 반환
	@GetMapping
	public ResponseEntity<List<Board>> retrieveNotice() throws Exception {
		return new ResponseEntity<List<Board>>(noticeService.retrieveNotice(), HttpStatus.OK);
	}
   
	@GetMapping("{articleno}")
	public ResponseEntity<Board> detailNotice(@PathVariable int articleno) {
		return new ResponseEntity<Board>(noticeService.detailNotice(articleno), HttpStatus.OK);
	}
	
	@GetMapping("/search/{subject}")
	public ResponseEntity<List<Board>> searchNotice(@PathVariable String subject) {
		return new ResponseEntity<List<Board>>(noticeService.detailNoticeBySubject(subject), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Integer> writeNotice(@RequestBody Board board) {
		if (noticeService.writeNotice(board) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}

	@PutMapping("{articleno}")
	public ResponseEntity<Integer> updateNotice(@RequestBody Board board) {
		
		if (noticeService.updateNotice(board) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("{articleno}")
	public ResponseEntity<Integer> deleteNotice(@PathVariable int articleno) {
		if (noticeService.deleteNotice(articleno) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}
}
