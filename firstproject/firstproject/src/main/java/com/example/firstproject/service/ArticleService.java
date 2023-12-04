package com.example.firstproject.service;


import com.example.firstproject.Repository.ArticleRepository;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 선언 (서비스 객체를 스프링부트에 생성)
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    // 모든 데이터 가져오기
    public List<Article> index() {
        return articleRepository.findAll();

    }

    // 1개 데이터 가져오기
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }


    // 데이터 추가
    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null ){
            return null;
        }
        return articleRepository.save(article);
    }

    // 데이터 수정
    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);

        if(target == null || !id.equals(article.getId())) return null;

        target.patch(article);
        return articleRepository.save(target);
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if(target == null) return null;

        articleRepository.delete(target);
        return target;
    }


    @Transactional // 해당 메서드를 트랜잭션으로 묶는다
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 Entity 묶음으로 변환
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());

        /*
        List<Article articleList = new ArrayList<>();
        for ( int i = 0; i< dtos.size(); i++ ){
            ArticleForm dto = dtos.get(i);
            Article entity = dto.toEntity();
            articleList.add(entity);
        }
         */

        // entity 묶음을 DB로 저장
        articleList.stream().forEach(article -> articleRepository.save(article));

        /*
        for( int i = 0; i< articleList.size(); i++){
            Article article = articleList.get(i);
            articleRepository.save(article);
        }
        */

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(

                () -> new IllegalArgumentException("결제 실패")

        );

        // 결과값 반환
        return articleList;
    }


}

