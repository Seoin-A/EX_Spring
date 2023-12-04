package com.example.firstproject.api;

import com.example.firstproject.Repository.ArticleRepository;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 데이터를 반환한다 (JSON)
@RequestMapping("/api")
@Slf4j
public class ArticleApiController {

    @Autowired // DI
    private ArticleRepository articleRepository;
    // get
    @GetMapping("/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // post
    @PostMapping("/articles")
    public Article create(@RequestBody ArticleForm dto){
        // @RequestBody 를 통해 Request의 body에 있는 내용을 ArticleForm에 담는다.
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // patch
    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){

        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {} , article : {}",id, article.toString());

        // 2. 대상 엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리 ( 대상이 없거나 id가 다른 경우 )
        if(target == null || !id.equals(article.getId())){
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청 입니다 => id : {} , article : {}",id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 업데이트 및 정상 응답 (200)
        target.patch(article);

        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }


    // delete

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        // 대상 찾기
        Article target =  articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 대상 삭제
        articleRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
