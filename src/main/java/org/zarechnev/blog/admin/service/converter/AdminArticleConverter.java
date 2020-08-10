package org.zarechnev.blog.admin.service.converter;

import org.zarechnev.blog.admin.dto.ArticleDTO;
import org.zarechnev.blog.admin.dto.ArticleFullDTO;
import org.zarechnev.blog.model.Article;

import java.util.List;

public interface AdminArticleConverter {

    ArticleFullDTO entityToFullDTO(Article article);

    List<ArticleDTO> entityToDTO(List<Article> articles);

    Article DTOToEntity(ArticleFullDTO articleDTO);
}
