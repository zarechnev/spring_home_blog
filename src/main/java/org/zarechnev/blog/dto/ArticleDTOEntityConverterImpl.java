package org.zarechnev.blog.dto;

import org.springframework.stereotype.Service;
import org.zarechnev.blog.repository.ArticleEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleDTOEntityConverterImpl implements ArticleDTOEntityConverter {

    @Override
    public ArticleDTO articleEntityToDTO(ArticleEntity articleEntity) {
        int SHORT_ARTICLE_LENGTH = 400;
        String articleAbstract = articleEntity
                .getArticleBody()
                .substring(
                        0, Math.min(SHORT_ARTICLE_LENGTH, articleEntity.getArticleBody().length())
                ) + " ...";

        return ArticleDTO.builder()
                .id(articleEntity.getId())
                .author(articleEntity.getAuthor())
                .articleTitle(articleEntity.getArticleTitle())
                .articleBody(articleEntity.getArticleBody())
                .abstraction(articleAbstract)
                .createdDatetime(articleEntity.getCreateDate())
                .visible(articleEntity.getVisible())
                .build();
    }

    @Override
    public List<ArticleDTO> articleEntityToDTO(List<ArticleEntity> articleEntityList) {
        return articleEntityList.stream()
                .map(this::articleEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleEntity articleDTOToEntity(ArticleDTO articleDTO) {
        if (articleDTO.getId() != null) {
            return ArticleEntity.builder()
                    .id(articleDTO.getId())
                    .author(articleDTO.getAuthor())
                    .articleTitle(articleDTO.getArticleTitle())
                    .articleBody(articleDTO.getArticleBody())
                    .createDate(LocalDateTime.now())
                    .visible(articleDTO.getVisible())
                    .build();
        } else {
            return ArticleEntity.builder()
                    .author(articleDTO.getAuthor())
                    .articleTitle(articleDTO.getArticleTitle())
                    .articleBody(articleDTO.getArticleBody())
                    .createDate(LocalDateTime.now())
                    .visible(articleDTO.getVisible())
                    .build();
        }
    }

    @Override
    public List<ArticleEntity> articleDTOToEntity(List<ArticleDTO> articleDTOList) {
        return articleDTOList.stream()
                .map(this::articleDTOToEntity)
                .collect(Collectors.toList());
    }

}