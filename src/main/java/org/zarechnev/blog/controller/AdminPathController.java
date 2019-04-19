package org.zarechnev.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zarechnev.blog.repository.ArticleSectionRepository;
import org.zarechnev.blog.repository.ArticleRepository;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static org.zarechnev.blog.constant.ControllerPathURLs.ADMIN_URL_PATH;
import static org.zarechnev.blog.constant.ControllerPathURLs.ARTICLE_URL_PATH;

/**
 * The controller for admin path.
 */
@Slf4j
@Controller
public class AdminPathController {
    @Autowired
    private ArticleRepository msgRepo;

    @Autowired
    private ArticleSectionRepository articleSectionRepo;

    @Autowired
    private Environment env;

    /**
     * Main Admin page.
     *
     * @param request the request
     * @return string
     */
    @GetMapping(ADMIN_URL_PATH)
    public String mainAdminPage(HttpServletRequest request) {
        log.info("Someone come to us with URL: " + request.getRequestURL());

        return "administration";
    }

    /**
     * Admin page for Articles.
     *
     * @param request the request
     * @return string
     */
    @GetMapping(ADMIN_URL_PATH + ARTICLE_URL_PATH)
    public String adminPage(HttpServletRequest request, Map<String, Object> model) {
        log.info("Someone come to us with URL: " + request.getRequestURL());
        model.put("articles", msgRepo.findAll());
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty("site.url"));

        return "administration_articles";
    }
}
