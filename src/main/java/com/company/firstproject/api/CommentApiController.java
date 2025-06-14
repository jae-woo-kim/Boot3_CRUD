package com.company.firstproject.api;

import com.company.firstproject.dto.CommentDto;
import com.company.firstproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    // 1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments (@PathVariable("articleId") Long articleId) {
        // 서비스 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable("articleId") Long articleId,
                                             @RequestBody CommentDto dto) {
        // 서비스에 위임
        CommentDto createdDto = commentService.create(articleId, dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 3. 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable("id") Long id,
                                             @RequestBody CommentDto dto) {
        // 서비스에 위임
        CommentDto updatedDto = commentService.update(id, dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }


    // 4. 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable("id") Long id) {

        // 서비스에 응답
        CommentDto deletedDto = commentService.delete(id);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
