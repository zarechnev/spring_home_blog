package org.zarechnev.blog.article.converter;

import org.mapstruct.Mapper;
import org.zarechnev.blog.article.dto.ArticleDTO;
import org.zarechnev.blog.article.dto.ArticleFullDTO;
import org.zarechnev.blog.model.Article;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleConverter {

    ArticleFullDTO entityToFullDTO(Article article);

    List<ArticleDTO> entityToDTO(List<Article> articles);

}
