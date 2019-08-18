package org.zarechnev.blog.dto;

import java.util.List;
import org.zarechnev.blog.repository.ArticleEntity;

public interface ArticleDTOEntityConverter {

    ArticleDTO articleEntityToDTO(ArticleEntity articleEntity);
    List<ArticleDTO> articleEntityToDTO(List<ArticleEntity> articleEntityList);

    ArticleEntity articleDTOToEntity(ArticleDTO articleDTO);
    List<ArticleEntity> articleDTOToEntity(List<ArticleDTO> articleDTOList);

}
