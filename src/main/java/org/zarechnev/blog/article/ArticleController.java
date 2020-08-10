package org.zarechnev.blog.article;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zarechnev.blog.article.dto.ArticleFullDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.zarechnev.blog.constant.ControllerPathURLs.*;
import static org.zarechnev.blog.constant.LoggingConstant.LOGGING_CLIENT_INFO;

@Slf4j
@Controller
@RequestMapping(ROOT_URL_PATH)
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private Environment env;

    @GetMapping
    public String main(HttpServletRequest request, Map<String, Object> model) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        model.put("articles", articleService.getAllArticles());
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty(SITE_URL_PROPERTY));
        return "index";
    }

    @GetMapping(value = ARTICLE_URL_PATH + "/{id}")
    public String articleById(HttpServletRequest request, @PathVariable("id") Long id, Map<String, Object> model) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        ArticleFullDTO article = articleService.getArticle(id);
        model.put("article", article);
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty(SITE_URL_PROPERTY));

        return "oneArticle";
    }

}
