package org.zarechnev.blog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Long> {
}
