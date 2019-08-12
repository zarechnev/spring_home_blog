package org.zarechnev.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.zarechnev.blog.entity.Section;

/**
 * The interface Article section repository.
 */
public interface SectionRepository extends CrudRepository<Section, Integer> {
}
