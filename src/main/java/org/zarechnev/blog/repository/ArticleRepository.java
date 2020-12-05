package org.zarechnev.blog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.zarechnev.blog.model.Article;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

    List<Article> findByIsVisibleTrue();
}
