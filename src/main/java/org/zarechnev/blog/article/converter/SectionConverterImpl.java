package org.zarechnev.blog.article.converter;

import org.springframework.stereotype.Service;
import org.zarechnev.blog.article.SectionDTO;
import org.zarechnev.blog.model.ArticlesSection;

import java.util.List;

@Service
public class SectionConverterImpl implements SectionConverter {

    @Override
    public SectionDTO sectionEntityToDTO(ArticlesSection articlesSection) {
        return null;
    }

    @Override
    public List<SectionDTO> sectionEntityToDTO(List<ArticlesSection> articlesSectionList) {
        return null;
    }

}
