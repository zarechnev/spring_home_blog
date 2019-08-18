package org.zarechnev.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zarechnev.blog.repository.ArticleEntity;
import org.zarechnev.blog.repository.ArticleRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

import static org.zarechnev.blog.constant.ControllerPathURLs.*;
import static org.zarechnev.blog.constant.LoggingConstant.LOGGING_CLIENT_INFO;
import static org.zarechnev.blog.constant.LoggingConstant.LOGGING_MISSING_PAGE_WARN;

@Slf4j
@Controller
@RequestMapping(ROOT_URL_PATH)
public class MainController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private Environment env;

    @GetMapping
    public String main(HttpServletRequest request, Map<String, Object> model) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        model.put("articles", articleRepository.findAll());
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty(SITE_URL_PROPERTY));
        return "index";
    }

    @GetMapping(value = ARTICLE_URL_PATH + "/{id}")
    public String articleById(HttpServletRequest request, @PathVariable("id") long id, Map<String, Object> model) {
        Optional<ArticleEntity> article = articleRepository.findById(id);

        if (!article.isPresent()) {
            log.warn(LOGGING_MISSING_PAGE_WARN, request.getRemoteAddr(), request.getRequestURL());
            return "redirect:" + env.getProperty(SITE_URL_PROPERTY);
        }

        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        model.put("article", article.get());
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty(SITE_URL_PROPERTY));

        return "oneArticle";
    }

}
