package org.zarechnev.blog.admin.service.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zarechnev.blog.admin.dto.ArticleDTO;
import org.zarechnev.blog.admin.dto.ArticleFullDTO;
import org.zarechnev.blog.model.Article;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminArticleConverterImpl implements AdminArticleConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ArticleFullDTO entityToFullDTO(Article article) {
        return modelMapper.map(article, ArticleFullDTO.class);
    }

    @Override
    public Article DTOToEntity(ArticleFullDTO articleDTO) {
        return modelMapper.map(articleDTO, Article.class);
    }

    @Override
    public List<ArticleDTO> entityToDTO(List<Article> articles) {
        return articles.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    private ArticleDTO entityToDTO(Article article) {
        return modelMapper.map(article, ArticleDTO.class);
    }

}