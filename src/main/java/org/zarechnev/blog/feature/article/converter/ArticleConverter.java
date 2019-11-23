package org.zarechnev.blog.feature.article.converter;

import org.zarechnev.blog.feature.article.dto.ArticleDTO;
import org.zarechnev.blog.feature.article.dto.ArticleFullDTO;
import org.zarechnev.blog.model.Article;

import java.util.List;

public interface ArticleConverter {

    ArticleFullDTO entityToFullDTO(Article article);

    List<ArticleDTO> entityToDTO(List<Article> articles);

}
