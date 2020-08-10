package org.zarechnev.blog.article.converter;

import org.zarechnev.blog.article.SectionDTO;
import org.zarechnev.blog.model.ArticlesSection;

import java.util.List;

public interface SectionConverter {

    SectionDTO sectionEntityToDTO(ArticlesSection articlesSection);

    List<SectionDTO> sectionEntityToDTO(List<ArticlesSection> articlesSectionList);

}
