package org.zarechnev.blog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RenderedArticleRepository extends PagingAndSortingRepository<RenderedArticleEntity, Long> {

    RenderedArticleEntity findByArticleId(Long articleId);

}
