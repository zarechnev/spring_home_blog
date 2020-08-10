package org.zarechnev.blog.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zarechnev.blog.admin.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.zarechnev.blog.constant.ControllerPathURLs.*;
import static org.zarechnev.blog.constant.LoggingConstant.LOGGING_CLIENT_INFO;

@Slf4j
@Controller
@RequestMapping(ADMIN_URL_PATH)
public class AdminController {

    @Autowired
    private AdminService adminService;
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
        model.put("articles", adminService.getArticles());
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty(SITE_URL_PROPERTY));
        return "admin/administration_articles";
    }

}