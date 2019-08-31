package org.zarechnev.blog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RenderedArticleRepository extends PagingAndSortingRepository<RenderedArticleEntity, Long> {

    Optional<RenderedArticleEntity> findByArticleId(Long articleId);

}
