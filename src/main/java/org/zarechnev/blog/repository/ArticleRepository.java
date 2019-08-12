package org.zarechnev.blog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.zarechnev.blog.entity.Article;

/**
 * The interface Blog article repository.
 */
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
}
