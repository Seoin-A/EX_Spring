package com.example.firstproject.service;

import com.example.firstproject.Repository.ArticleRepository;
import com.example.firstproject.Repository.CommentRepository;
import com.example.firstproject.annotation.RunningTime;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 조회 : 댓글 목록

//        List<Comment> commentList = commentRepository.findByArticleId(articleId);


        // 변환 : Entity -> DTO

        /*List<CommentDto> dtos = new ArrayList<CommentDto>();

        for(int i = 0; i<commentList.size(); i++){
            Comment c = commentList.get(i);
            CommentDto dto = CommentDto.createDto(c);
            dtos.add(dto);
        }*/


        // 반환
        return commentRepository.findByArticleId(articleId).stream()
                .map(CommentDto::createDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long id, CommentDto dto) {
        /* 핵심 로직*/
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);

        // 댓글 엔티티 DB 저장
        Comment created = commentRepository.save(comment);

        // Dto 변경하여 반환
        return CommentDto.createDto(created);
        /* 핵심 로직*/

    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {


        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("댓글 수정 실패! 해당 댓글이 없습니다"));
        // 댓글 수정
        target.patch(dto);

        // DB 갱신
        Comment updated = commentRepository.save(target);
        // 댓글 앤티티를 DTO로 변환 및 반환
        return CommentDto.createDto(updated);
    }

    @Transactional
    @RunningTime
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 수정 실패! 해당 댓글이 없습니다"));
        // DB 댓글 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 반환
        return CommentDto.createDto(target);

    }
}

