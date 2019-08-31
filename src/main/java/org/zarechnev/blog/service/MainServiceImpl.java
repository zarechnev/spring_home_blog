package org.zarechnev.blog.service;

import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zarechnev.blog.dto.ArticleDTO;
import org.zarechnev.blog.dto.ArticleDTOEntityConverterImpl;
import org.zarechnev.blog.repository.ArticleEntity;
import org.zarechnev.blog.repository.ArticleRepository;
import org.zarechnev.blog.repository.RenderedArticleEntity;
import org.zarechnev.blog.repository.RenderedArticleRepository;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private ArticleDTOEntityConverterImpl articleDTOEntityConverter;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RenderedArticleRepository renderedArticleRepository;

    @Override
    public ArticleDTO getSpecificArticleToMainPage(Long articleId) {
        ArticleDTO articleDTO = articleDTOEntityConverter.articleEntityToDTO(articleRepository.findById(articleId).get());
        articleDTO.setArticleBody(renderedArticleRepository.findByArticleId(articleId).getArticleBodyInHTML());
        return articleDTO;
    }

    @Override
    public List<ArticleDTO> getListArticlesToMainPage() {
        List<ArticleEntity> articleEntity = new ArrayList<>();
        for (ArticleEntity articleEnt : articleRepository.findAll()) {
            articleEntity.add(articleEnt);
        }
        return articleDTOEntityConverter.articleEntityToDTO(articleEntity);
    }

    @Override
    public void addArticle(ArticleDTO articleDTO) {
        ArticleEntity articleEntity = articleRepository.save(articleDTOEntityConverter.articleDTOToEntity(articleDTO));
        Parser parser = Parser.builder().build();
        Node document = parser.parse(articleEntity.getArticleBody());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String s = renderer.render(document);
        RenderedArticleEntity renderedArticleEntity = RenderedArticleEntity.builder()
                .articleBodyInHTML(s)
                .articleId(articleEntity.getId())
                .build();
        renderedArticleRepository.save(renderedArticleEntity);
    }

    @Override
    public void updateArticle(ArticleDTO articleDTO) {
        articleRepository.save(articleDTOEntityConverter.articleDTOToEntity(articleDTO));
    }

}
