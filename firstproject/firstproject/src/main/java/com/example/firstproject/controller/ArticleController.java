package com.example.firstproject.controller;

import com.example.firstproject.Repository.ArticleRepository;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    /* 데이터 생성*/
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
//        System.out.println("form = " + form.toString()); -> 로깅 기능으로 대체
        log.info(form.toString());
        // 1. Dto 변환 -> Entity로
        Article article = form.toEntity();

        log.info(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함
        Article saved = articleRepository.save(article);

        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    /* 1개의 data select */
    @GetMapping("/articles/{id}")
    public String show(@PathVariable(name = "id") Long id, Model m){
        log.info("id :"+id);

        // 1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록
        m.addAttribute("article",articleEntity);

        // 3. 보여줄 페이지 설정
        return "articles/show";
    }

    /* 여러개의 data select*/
    @GetMapping("/articles")
    public String index(Model m){

/*      1. 모든 Article을 가져온다. (반환값 주의)
            - List<Article> articleEntityList =  (List<Article>) articleRepository.findAll();
            - Iterable<Article> articleEntityList =  articleRepository.findAll();
            - @Override
              ArrayList<Article> findAll();
 */
        List<Article> articleEntityList =  articleRepository.findAll();

        // 2. 가져온 Article 묶음을 뷰로 전달
        m.addAttribute("articleList",articleEntityList);

        // 3. 뷰 페이지 설정
        return "articles/index"; // articles/index.mustache
    }

    /* Edit Data 수정하기 */
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable(name = "id") Long id, Model m){

        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록
        m.addAttribute("article",articleEntity);

        // view page 설정
        return "articles/edit";
    }

}
