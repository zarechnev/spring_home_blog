package org.zarechnev.blog.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zarechnev.blog.admin.dto.ArticleDTO;
import org.zarechnev.blog.admin.dto.ArticleFullDTO;
import org.zarechnev.blog.admin.service.converter.AdminArticleConverter;
import org.zarechnev.blog.exception.ResourceNotFound;
import org.zarechnev.blog.model.Article;
import org.zarechnev.blog.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService, AdminAPIService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AdminArticleConverter adminArticleConverter;
    @Autowired
    private MDRenderService mdRenderService;

    @Override
    public ArticleFullDTO getArticle(Long articleId) {
        return adminArticleConverter.entityToFullDTO(
                articleRepository.findById(articleId).orElseThrow(ResourceNotFound::new)
        );
    }

    @Override
    public List<ArticleDTO> getArticles() {
        return adminArticleConverter.entityToDTO(articleRepository.findAll());
    }

    @Override
    public ArticleFullDTO addArticle(ArticleFullDTO articleDTO) {
        articleDTO.setCreatedDateTime(LocalDateTime.now());
        articleDTO.setArticleHTMLBody(mdRenderService.getHTML(articleDTO.getArticleBody()));
        Article article = adminArticleConverter.DTOToEntity(articleDTO);
        Article savedArticle = articleRepository.save(article);

        return adminArticleConverter.entityToFullDTO(savedArticle);
    }

    @Override
    public ArticleFullDTO updateArticle(ArticleFullDTO articleFullDTO) {
        return null;
    }

}
