package org.zarechnev.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.zarechnev.blog.entity.SectionModel;

/**
 * The interface Article section repository.
 */
public interface SectionRepository extends CrudRepository<SectionModel, Integer> {
}
