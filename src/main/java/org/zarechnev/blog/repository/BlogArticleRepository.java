package org.zarechnev.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.zarechnev.blog.entity.ArticleModel;

/**
 * The interface Blog article repository.
 */
public interface BlogArticleRepository extends CrudRepository<ArticleModel, Long> {
}
