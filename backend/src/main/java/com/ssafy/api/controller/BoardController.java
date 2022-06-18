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
import com.ssafy.api.model.service.BoardService;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@GetMapping
	public ResponseEntity<List<Board>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<Board>>(boardService.retrieveBoard(), HttpStatus.OK);
	}
   
	@GetMapping("{articleno}")
	public ResponseEntity<Board> detailBoard(@PathVariable int articleno) {
		return new ResponseEntity<Board>(boardService.detailBoard(articleno), HttpStatus.OK);
	}
	
	@GetMapping("/search/{subject}")
	public ResponseEntity<List<Board>> searchBoard(@PathVariable String subject) {
		return new ResponseEntity<List<Board>>(boardService.detailBoardBySubject(subject), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Integer> writeBoard(@RequestBody Board board) {
		if (boardService.writeBoard(board) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}

	@PutMapping("{articleno}")
	public ResponseEntity<Integer> updateBoard(@RequestBody Board board) {
		
		if (boardService.updateBoard(board) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("{articleno}")
	public ResponseEntity<Integer> deleteBoard(@PathVariable int articleno) {
		if (boardService.deleteBoard(articleno) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}
}
