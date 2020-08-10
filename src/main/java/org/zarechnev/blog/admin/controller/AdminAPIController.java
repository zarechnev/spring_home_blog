package org.zarechnev.blog.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zarechnev.blog.admin.dto.ArticleFullDTO;
import org.zarechnev.blog.admin.service.AdminAPIService;

import static org.zarechnev.blog.constant.ControllerPathURLs.API_URL_PATH;
import static org.zarechnev.blog.constant.ControllerPathURLs.ARTICLE_URL_PATH;

@Slf4j
@RestController
@RequestMapping(API_URL_PATH)
public class AdminAPIController {

    @Autowired
    private AdminAPIService adminService;

    @GetMapping(ARTICLE_URL_PATH + "/{id}")
    public ArticleFullDTO getArticle(@PathVariable("id") Long id) {
        return adminService.getArticle(id);
    }

    @PostMapping(ARTICLE_URL_PATH)
    public ArticleFullDTO addArticle(@RequestBody ArticleFullDTO articleFullDTO) {
        return adminService.addArticle(articleFullDTO);
    }

    @PutMapping(ARTICLE_URL_PATH + "/{id}")
    public ArticleFullDTO updateArticle(@RequestBody ArticleFullDTO articleFullDTO) {
        return adminService.updateArticle(articleFullDTO);
    }

}