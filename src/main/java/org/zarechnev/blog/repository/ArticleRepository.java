package org.zarechnev.blog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<ArticleEntity, Long> {
}
