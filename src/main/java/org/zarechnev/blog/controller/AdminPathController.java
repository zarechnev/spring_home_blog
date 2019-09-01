package org.zarechnev.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zarechnev.blog.repository.SectionRepository;
import org.zarechnev.blog.repository.ArticleRepository;
import org.zarechnev.blog.service.MainServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static org.zarechnev.blog.constant.ControllerPathURLs.*;
import static org.zarechnev.blog.constant.LoggingConstant.LOGGING_CLIENT_INFO;

@Slf4j
@Controller()
@RequestMapping(ADMIN_URL_PATH)
public class AdminPathController {

    @Autowired
    private MainServiceImpl mainService;

    @Autowired
    private SectionRepository articleSectionRepo;

    @Autowired
    private Environment env;

    @GetMapping()
    public String mainAdminPage(HttpServletRequest request) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());

        return "admin/administration";
    }

    @GetMapping(ARTICLE_URL_PATH)
    public String adminArticlesPage(HttpServletRequest request, Map<String, Object> model) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        model.put("articles", mainService.getListArticlesToMainPage());
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty(SITE_URL_PROPERTY));

        return "admin/administration_articles";
    }

    // TODO реализовать!!!
    @GetMapping(USER_URL_PATH)
    public String adminUsersPage(HttpServletRequest request, Map<String, Object> model) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        model.put("articles", mainService.getListArticlesToMainPage());
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty(SITE_URL_PROPERTY));

        return "admin/administration_users";
    }

}
