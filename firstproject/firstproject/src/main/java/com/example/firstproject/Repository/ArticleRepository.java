package com.example.firstproject.Repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> { // <관리대상, pk 타입>


    @Override
    ArrayList<Article> findAll();
}
