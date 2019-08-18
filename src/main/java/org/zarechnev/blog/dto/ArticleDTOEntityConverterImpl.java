package org.zarechnev.blog.dto;

import org.springframework.stereotype.Service;
import org.zarechnev.blog.repository.ArticleEntity;

import java.util.List;

@Service
public class ArticleDTOEntityConverterImpl implements ArticleDTOEntityConverter {

    @Override
    public ArticleDTO articleEntityToDTO(ArticleEntity articleEntity) {
        return null;
    }

    @Override
    public List<ArticleDTO> articleEntityToDTO(List<ArticleEntity> articleEntityList) {
        return null;
    }

    @Override
    public ArticleEntity articleDTOToEntity(ArticleDTO articleDTO) {
        return null;
    }

    @Override
    public List<ArticleEntity> articleDTOToEntity(List<ArticleDTO> articleDTOList) {
        return null;
    }

}
