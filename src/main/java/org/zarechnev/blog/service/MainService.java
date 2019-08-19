package org.zarechnev.blog.service;

import org.zarechnev.blog.dto.ArticleDTO;

import java.util.List;

public interface MainService {

    ArticleDTO getSpecificArticleToMainPage(Long articleId);

    List<ArticleDTO> getListArticlesToMainPage();

}
