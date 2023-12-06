package com.example.firstproject.Repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;


    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회") // 테스트 결과에 보여줄 name
    void findByArticleId() {

        /* Case 1 : 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long id = 4L;
            // 실제 수행
            List<Comment> commentList = commentRepository.findByArticleId(id);

            /*  예상하기
                1	서울의 봄	김	4
                2	돼김볶	    이	4
                3	아이언맨	    박	4  */
            Article article = new Article(4L,"당신의 인생 영화는?","댓글1");

            Comment a= new Comment(1L, article, "김", "서울의 봄");
            Comment b= new Comment(2L, article, "이", "돼김볶");
            Comment c= new Comment(3L, article, "박", "아이언맨");
            List<Comment> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(),commentList.toString(), "4번 글의 모든 댓글 출력!" );
        }

        /* Case 2 : 1번 댓글의 모든 댓글 조회*/
        {
            // 입력 데이터 준비
            Long id = 1L;
            // 실제 수행
            List<Comment> commentList = commentRepository.findByArticleId(id);


            Article article = new Article(1L,"제목1","내용1");
            List<Comment> expected = List.of();
            // 검증
            assertEquals(expected.toString(),commentList.toString(), "1번 글은 댓글이 없음" );
        }

        /* Case 3 : 9번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터
            Long id = 9L;
            // 실제 수행
            List<Comment> commentList = commentRepository.findByArticleId(id);
            // 예상
            List<Comment> expected = List.of();
            // 검증
            assertEquals(expected.toString(), commentList.toString(), "9번 게시글은 존재하지 않는다");
        }

        /* Case 4 : 9999번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터
            Long id = 9999L;
            // 실제 수행
            List<Comment> commentList = commentRepository.findByArticleId(id);
            // 예상
            List<Comment> expected = List.of();
            // 검증
            assertEquals(expected.toString(), commentList.toString(), "9999번 게시글은 존재하지 않는다");
        }

        /* Case 5 : -1번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터
            Long id = -1L;
            // 실제 수행
            List<Comment> commentList = commentRepository.findByArticleId(id);
            // 예상
            List<Comment> expected = List.of();
            // 검증
            assertEquals(expected.toString(), commentList.toString(), "-1번 게시글은 존재하지 않는다");
        }


    }

    @Test
    void findByNickname() {
    }
}