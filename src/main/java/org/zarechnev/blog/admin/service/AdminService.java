package org.zarechnev.blog.admin.service;

import org.zarechnev.blog.admin.dto.ArticleDTO;

import java.util.List;

public interface AdminService {

    List<ArticleDTO> getArticles();

}
