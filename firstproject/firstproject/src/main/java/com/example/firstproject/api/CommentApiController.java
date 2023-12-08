package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.firstproject.dto.CommentDto.createDto;

@RestController
@RequestMapping("/articles")
public class CommentApiController {

    @Autowired
    private CommentService commentService;


    // 댓글 목록 조회
    @GetMapping("/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> commentList(@PathVariable Long articleId){
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과를 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable(name = "articleId") Long id, @RequestBody CommentDto dto){
        // 서비스 위임
        CommentDto createDto = commentService.create(id,dto);
        // 결과 응답
        return ResponseEntity.ok().body(createDto);
    }

    // 댓글 수정
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable(name = "id") Long id, @RequestBody CommentDto dto){
        // 서비스 위임
        CommentDto updatedDto = commentService.update(id,dto);
        // 결과 응답
        return ResponseEntity.ok().body(updatedDto);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable(name = "id") Long id){
        // 서비스 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.ok().body(deletedDto);
    }

}
