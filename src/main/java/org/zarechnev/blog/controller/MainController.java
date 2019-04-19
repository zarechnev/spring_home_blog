package org.zarechnev.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zarechnev.blog.entity.ArticleModel;
import org.zarechnev.blog.repository.ArticleRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

import static org.zarechnev.blog.constant.ControllerPathURLs.ARTICLE_URL_PATH;

/**
 * The type Main controller.
 */
@Slf4j
@Controller
public class MainController {
    @Autowired
    private ArticleRepository msgRepo;

    @Autowired
    private Environment env;

    /**
     * Main WEB-Page.
     *
     * @param request the request
     * @param model   the entity
     * @return string
     */
    @GetMapping("/")
    public String main(HttpServletRequest request, Map<String, Object> model) {
        log.info("someone come to us with URL: " + request.getRequestURL());
        model.put("articles", msgRepo.findAll());
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty("site.url"));
        return "index";
    }

    /**
     * Article page by Id in Url.
     * If article Id does not exist then redirect to main page.
     * <p>
     * TODO: May be need to show alert to User.
     *
     * @param request the request
     * @param id      the id
     * @param model   the entity
     * @return string
     */
    @GetMapping(value = ARTICLE_URL_PATH + "/{id}")
    public String articleById(HttpServletRequest request, @PathVariable("id") long id, Map<String, Object> model) {
        Optional<ArticleModel> article = msgRepo.findById(id);

        if (!article.isPresent()) {
            log.warn(request.getRequestURL() + " does not exist!");
            return "redirect:" + env.getProperty("site.url");
        }

        log.info("someone come to us with URL: " + request.getRequestURL());
        model.put("article", article.get());
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty("site.url"));

        return "oneArticle";
    }

    /**
     * Foundation template for HTML markup.
     *
     * @param request the request
     * @return the string
     */
    @GetMapping("/foundation")
    public String foundation(HttpServletRequest request) {
        log.info("Someone come to us with URL: " + request.getRequestURL());
        return "foundation";
    }
}
