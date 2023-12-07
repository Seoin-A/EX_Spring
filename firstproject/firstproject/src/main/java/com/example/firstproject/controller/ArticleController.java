package com.example.firstproject.controller;

import com.example.firstproject.Repository.ArticleRepository;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

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
        List<CommentDto> commentDtos= commentService.comments(id);

        // 2. 가져온 데이터를 모델에 등록

        m.addAttribute("article",articleEntity);
        m.addAttribute("commentDtos",commentDtos);

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

    /* Edit Form 이동 */
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable(name = "id") Long id, Model m){

        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록
        m.addAttribute("article",articleEntity);

        // view page 설정
        return "articles/edit";
    }

    /* Edit DB*/
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        // 1. dto를 entity로 변환
        Article articleEntity = form.toEntity();

        // 2. entity를 DB에 저장
        // 2-1 : DB에서 기존 데이터를 가져온다
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-1 : 기존 데이터에 값을 갱신한다

        if(target != null){
            articleRepository.save(articleEntity);
            log.info("성공");
        }
        // 3. 수정 결과 페이지로 redirect
        return "redirect:/articles/" + articleEntity.getId();
    }


    /* delete data */
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다");

        // 1. 삭제 대상을 가져온다
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상을 삭제한다
        if(target != null){
            articleRepository.delete(target);
            log.info("정상적으로 삭제되었습니다.");
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");
        }

        // 결과 페이지로 리다이렉트 한다
        return "redirect:/articles";
    }

}
