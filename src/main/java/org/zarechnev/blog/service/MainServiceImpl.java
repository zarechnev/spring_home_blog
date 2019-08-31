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
    @Autowired
    private HTMLRenderFromMarkDownServiceImpl htmlRenderFromMarkDownService;

    @Override
    public ArticleDTO getSpecificArticleToMainPage(Long articleId) {
        ArticleDTO articleDTO = articleDTOEntityConverter.articleEntityToDTO(articleRepository.findById(articleId)
                .get());

        String articleBodyInHTML;

        if (!renderedArticleRepository.findByArticleId(articleId).isPresent()) {
            String articleBodyInMarkDown = articleDTO.getArticleBody();
            articleBodyInHTML = htmlRenderFromMarkDownService.getHTML(articleBodyInMarkDown);

            RenderedArticleEntity renderedArticleEntity = RenderedArticleEntity.builder()
                    .articleId(articleId)
                    .articleBodyInHTML(articleBodyInHTML)
                    .build();

            renderedArticleRepository.save(renderedArticleEntity);
        } else {
            articleBodyInHTML = renderedArticleRepository.findByArticleId(articleId)
                    .get()
                    .getArticleBodyInHTML();
        }

        articleDTO.setArticleBody(articleBodyInHTML);

        return articleDTO;
    }

    @Override
    public List<ArticleDTO> getListArticlesToMainPage() {
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        for (ArticleEntity articleEnt : articleRepository.findAll()) {
            ArticleDTO articleDTO = articleDTOEntityConverter.articleEntityToDTO(articleEnt);
            articleDTO.setAbstraction(renderedArticleRepository.findByArticleId(articleEnt.getId())
                    .get()
                    .getArticleBodyInHTML()
                    .substring(0, 400) + " ...");
            articleDTOList.add(articleDTO);
        }
        return articleDTOList;
    }

    @Override
    public void addArticle(ArticleDTO articleDTO) {
        ArticleEntity articleEntity = articleRepository.save(articleDTOEntityConverter.articleDTOToEntity(articleDTO));

        RenderedArticleEntity renderedArticleEntity = RenderedArticleEntity.builder()
                .articleBodyInHTML(htmlRenderFromMarkDownService.getHTML(articleEntity.getArticleBody()))
                .articleId(articleEntity.getId())
                .build();
        renderedArticleRepository.save(renderedArticleEntity);
    }

    @Override
    public void updateArticle(ArticleDTO articleDTO) {
        articleRepository.save(articleDTOEntityConverter.articleDTOToEntity(articleDTO));
    }

}
