package org.zarechnev.blog.feature.admin.service;

import org.zarechnev.blog.feature.admin.dto.ArticleDTO;

import java.util.List;

public interface AdminService {

    List<ArticleDTO> getArticles();

}
