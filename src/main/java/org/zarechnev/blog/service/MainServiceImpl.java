package org.zarechnev.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zarechnev.blog.dto.ArticleDTO;
import org.zarechnev.blog.dto.ArticleDTOEntityConverterImpl;
import org.zarechnev.blog.repository.ArticleEntity;
import org.zarechnev.blog.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private ArticleDTOEntityConverterImpl articleDTOEntityConverter;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ArticleDTO getSpecificArticleToMainPage(Long articleId) {
//        if (!article.isPresent()) {
//            log.warn(LOGGING_MISSING_PAGE_WARN, request.getRemoteAddr(), request.getRequestURL());
//            return "redirect:" + env.getProperty(SITE_URL_PROPERTY);
//        }

        return articleDTOEntityConverter.articleEntityToDTO(articleRepository.findById(articleId).get());
    }

    @Override
    public List<ArticleDTO> getListArticlesToMainPage() {
        List<ArticleEntity> articleEntity = new ArrayList<>();

        for (ArticleEntity artclEnt : articleRepository.findAll()) {
            articleEntity.add(artclEnt);
        }

        return articleDTOEntityConverter.articleEntityToDTO(articleEntity);
    }
}
