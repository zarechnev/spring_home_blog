package org.zarechnev.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.zarechnev.blog.model.ArticleSectionModel;

/**
 * The interface Article section repository.
 */
public interface ArticleSectionRepository extends CrudRepository<ArticleSectionModel, Integer> {
}
