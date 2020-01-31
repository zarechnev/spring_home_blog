package org.zarechnev.blog.article;

import org.zarechnev.blog.article.dto.ArticleDTO;
import org.zarechnev.blog.article.dto.ArticleFullDTO;

import java.util.List;

public interface ArticleService {

    ArticleFullDTO getArticle(Long articleId);

    List<ArticleDTO> getAllArticles();

}
