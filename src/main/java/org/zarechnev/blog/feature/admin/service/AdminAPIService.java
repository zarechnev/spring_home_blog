package org.zarechnev.blog.feature.admin.service;

import org.zarechnev.blog.feature.admin.dto.ArticleFullDTO;

public interface AdminAPIService {

    ArticleFullDTO getArticle(Long articleId);

    ArticleFullDTO addArticle(ArticleFullDTO articleDTO);

    ArticleFullDTO updateArticle(ArticleFullDTO articleFullDTO);

}
