package org.zarechnev.blog.feature.article.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zarechnev.blog.feature.article.dto.ArticleDTO;
import org.zarechnev.blog.feature.article.dto.ArticleFullDTO;
import org.zarechnev.blog.model.Article;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleConverterImpl implements ArticleConverter {

    @Autowired
    private ModelMapper modelMapper;
    private int ABSTRACT_ARTICLE_LENGTH = 400;

    @Override
    public ArticleFullDTO entityToFullDTO(Article article) {
        return modelMapper.map(article, ArticleFullDTO.class);
    }

    @Override
    public List<ArticleDTO> entityToDTO(List<Article> articleList) {
        return articleList.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    private ArticleDTO entityToDTO(Article article) {
        // TODO подумать как обрывать статью (сейчас можно оборвать между тегами например)
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        articleDTO.setAbstraction(article
                .getArticleHTMLBody()
                .substring(
                        0, Math.min(ABSTRACT_ARTICLE_LENGTH, article.getArticleBody().length())
                ) + " ...");

        return articleDTO;
    }

}