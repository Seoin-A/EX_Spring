package com.example.firstproject.Repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Long> { // <관리대상, pk 타입>


}
