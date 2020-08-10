package org.zarechnev.blog.admin.service;

import org.zarechnev.blog.admin.dto.ArticleFullDTO;

public interface AdminAPIService {

    ArticleFullDTO getArticle(Long articleId);

    ArticleFullDTO addArticle(ArticleFullDTO articleDTO);

    ArticleFullDTO updateArticle(ArticleFullDTO articleFullDTO);

}
