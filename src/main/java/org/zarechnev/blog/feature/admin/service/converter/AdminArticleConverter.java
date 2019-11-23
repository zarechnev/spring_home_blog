package org.zarechnev.blog.feature.admin.service.converter;

import org.zarechnev.blog.feature.admin.dto.ArticleDTO;
import org.zarechnev.blog.feature.admin.dto.ArticleFullDTO;
import org.zarechnev.blog.model.Article;

import java.util.List;

public interface AdminArticleConverter {

    ArticleFullDTO entityToFullDTO(Article article);

    List<ArticleDTO> entityToDTO(List<Article> articles);

    Article DTOToEntity(ArticleFullDTO articleDTO);
}
