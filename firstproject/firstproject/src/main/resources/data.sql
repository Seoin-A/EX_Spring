insert into article(title, content) values('제목1','내용1');
insert into article(title, content) values('제목2','내용2');
insert into article(title, content) values('제목3','내용3');

-- article 더미 데이터
insert into article(title, content) values('당신의 인생 영화는?','댓글1');
insert into article(title, content) values('당신의 소울 푸드는?','댓글2');
insert into article(title, content) values('당신의 취미는?','댓글3');

-- comment  4번 게시글 더미 데이터
insert into comment(article_id, nickname, body ) values(4,'김','서울의 봄');
insert into comment(article_id, nickname, body ) values(4, '이','돼김볶');
insert into comment(article_id, nickname, body ) values(4, '박', '아이언맨');

-- comment  5번 게시글 더미 데이터
insert into comment(article_id, nickname, body ) values(5,'김','서울의 봄');
insert into comment(article_id, nickname, body ) values(5, '이','돼김볶');
insert into comment(article_id, nickname, body ) values(5, '박', '아이언맨');

-- comment  6번 게시글 더미 데이터
insert into comment(article_id, nickname, body ) values(6,'김','서울');
insert into comment(article_id, nickname, body ) values(6, '이','돼');
insert into comment(article_id, nickname, body ) values(6, '박', '아');

