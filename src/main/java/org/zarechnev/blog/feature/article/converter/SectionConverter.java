package org.zarechnev.blog.feature.article.converter;

import org.zarechnev.blog.feature.article.SectionDTO;
import org.zarechnev.blog.model.ArticlesSection;

import java.util.List;

public interface SectionConverter {

    SectionDTO sectionEntityToDTO(ArticlesSection articlesSection);

    List<SectionDTO> sectionEntityToDTO(List<ArticlesSection> articlesSectionList);

}
