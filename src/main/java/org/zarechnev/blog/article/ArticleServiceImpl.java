package org.zarechnev.blog.article;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zarechnev.blog.article.converter.ArticleConverter;
import org.zarechnev.blog.article.dto.ArticleDTO;
import org.zarechnev.blog.article.dto.ArticleFullDTO;
import org.zarechnev.blog.exception.ResourceNotFound;
import org.zarechnev.blog.model.Article;
import org.zarechnev.blog.repository.ArticleRepository;

import java.util.List;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleConverter articleConverter;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ArticleFullDTO getArticle(Long articleId) {
        return articleConverter.entityToFullDTO(
                articleRepository.findById(articleId)
                        .filter(Article::getIsVisible)
                        .orElseThrow(ResourceNotFound::new)
        );
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        return articleConverter.entityToDTO(
                articleRepository.findByIsVisibleTrue()
        );
    }

}
