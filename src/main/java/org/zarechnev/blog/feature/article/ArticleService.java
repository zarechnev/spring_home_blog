package org.zarechnev.blog.feature.article;

import org.zarechnev.blog.feature.article.dto.ArticleDTO;
import org.zarechnev.blog.feature.article.dto.ArticleFullDTO;

import java.util.List;

public interface ArticleService {

    ArticleFullDTO getArticle(Long articleId);

    List<ArticleDTO> getAllArticles();

}
