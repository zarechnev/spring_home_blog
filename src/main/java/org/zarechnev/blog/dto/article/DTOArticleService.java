package org.zarechnev.blog.dto.article;

import java.util.List;

public interface DTOArticleService {

    List<ArticleDTO> findAll();

    void save(ArticleDTO article);
}
