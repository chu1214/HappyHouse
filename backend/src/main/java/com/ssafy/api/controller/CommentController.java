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

import com.ssafy.api.model.dto.Comment;
import com.ssafy.api.model.service.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/list/{articleno}")
	public ResponseEntity<List<Comment>> retrieveComment(@PathVariable int articleno) throws Exception {
		return new ResponseEntity<List<Comment>>(commentService.retrieveComment(articleno), HttpStatus.OK);
	}
	
	@GetMapping("{commentno}")
	public ResponseEntity<Comment> selectComment(@PathVariable int commentno) throws Exception {
		return new ResponseEntity<Comment>(commentService.selectComment(commentno), HttpStatus.OK);
	}
   
	@PostMapping
	public ResponseEntity<Integer> writeComment(@RequestBody Comment comment) {
		if (commentService.writeComment(comment) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}

	@PutMapping("{commentno}")
	public ResponseEntity<Integer> updateComment(@RequestBody Comment comment) {
		
		if (commentService.updateComment(comment) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("{commentno}")
	public ResponseEntity<Integer> deleteComment(@PathVariable int commentno) {
		if (commentService.deleteComment(commentno) == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}
}
