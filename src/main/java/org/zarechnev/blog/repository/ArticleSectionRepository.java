package org.zarechnev.blog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.zarechnev.blog.entity.ArticleSectionModel;

/**
 * The interface Article section repository.
 */
public interface ArticleSectionRepository extends PagingAndSortingRepository<ArticleSectionModel, Integer> {
}
