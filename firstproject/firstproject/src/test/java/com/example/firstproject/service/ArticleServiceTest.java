package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅
class ArticleServiceTest {

    @Autowired ArticleService articleService;
    @Test // 해당 메서드가 테스트임을 알려주는 어노테이션

    void index() {
        // 1. 예상
        Article a= new Article(1L,"제목1","내용1");
        Article b= new Article(2L,"제목2","내용2");
        Article c= new Article(3L,"제목3","내용3");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 2. 실제
        List<Article> articles=articleService.index();
        // 3. 비교

        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_성공___존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "제목1", "내용1");
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패___존재하지_않는_id_입력() {
        // 예상
        Long id = -1L;
        Article expected = null;
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_____title과__content만_있는_dto_입력() {
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm( null,title,content);
        Article expected = new Article( 4L,title,content);
        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected.toString(), article.toString());

    }
    @Test
    @Transactional
    void create_실패____id가_포함된_dto_입력() {
        // 예상
        String title = "제목3";
        String content = "내용3";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;
        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공____존재하는_id와_title_content_가_있는_dto_입력() {
        Long id = 3L;
        String title = "바꾼 제목";
        String content = "바꾼 내용";
        ArticleForm dto = new ArticleForm(id,title,content);

        // 예상
        Article expected = new Article(3L,title,content);

        // 실제
        Article article = articleService.update(id,dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공____존재하는_id와_title만_있는_dto_입력() {
        Long id = 1L;
        String title = "바꾼 제목";

        ArticleForm dto = new ArticleForm(id,title,null); // 수정 폼 입력한 값

        // 예상
        Article expected = new Article(1L,title,"내용1");

        // id = 1 title = 바꾼 제목 content = 내용1

        // 실제
        Article article = articleService.update(id,dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void update_실패____존재하지_않는_id의_dto_입력() {
        Long id = -1L;
        String title = "바꾼 제목";
        String content = "바꾼 내용";

        ArticleForm dto = new ArticleForm(id,title,content);

        // 예상
        Article expected = null;

        // 실제
        Article article = articleService.update(id,dto);

        // 비교
        assertEquals(expected, article);

    }

    @Test
    void update_실패____id가_다른_dto_입력() {
        Long id = 2L;
        ArticleForm dto = new ArticleForm(1L,null,null);

        // 예상
        Article expected = null;

        // 실제
        Article article = articleService.update(id,dto);

        // 비교
        assertEquals(expected, article);


    }

    @Test
    @Transactional
    void delete____성공__존재하는_id_입력() {
        Long id = 1L;

        // 예상
        Article expected = articleService.show(id);

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    void delete_실패____존재하지_않는_id_입력() {
        Long id = -1L;

        // 예상
        Article expected = articleService.show(id); // null이 나올것

        // 실제
        Article article = articleService.delete(id);

        // 비겨
        assertEquals(expected,article);

    }


}