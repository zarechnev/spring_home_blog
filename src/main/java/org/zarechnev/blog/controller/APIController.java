package org.zarechnev.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zarechnev.blog.entity.ArticleModel;
import org.zarechnev.blog.repository.ArticleRepository;
import org.zarechnev.blog.repository.SectionRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static org.zarechnev.blog.constant.ControllerPathURLs.*;
import static org.zarechnev.blog.constant.LoggingConstant.LOGGING_CLIENT_INFO;
import static org.zarechnev.blog.constant.LoggingConstant.LOGGING_MISSING_PAGE_WARN;

/**
 * The controller for API.
 */
@Slf4j
@Controller()
@RequestMapping(API_URL_PATH + ARTICLE_URL_PATH)
public class APIController {
    @Autowired
    private ArticleRepository msgRepo;

    @Autowired
    private SectionRepository articleSectionRepo;

    @Autowired
    private Environment env;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> allArticlesInJson(HttpServletRequest request) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        return new ResponseEntity<>(msgRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> articleByIdInJson(HttpServletRequest request, @PathVariable("id") long id) {
        Optional<ArticleModel> article = msgRepo.findById(id);
        if (!article.isPresent()) {
            log.warn(LOGGING_MISSING_PAGE_WARN, request.getRemoteAddr(), request.getRequestURL());
            return new ResponseEntity<>(new Object(), HttpStatus.BAD_REQUEST);
        }
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    // TODO Реализовать добавление статьи
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> addArticle(HttpServletRequest request, @RequestPart("article") String articleInJson) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO Реализовать изменение статьи
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> updateArticle(HttpServletRequest request, @PathVariable("id") long id) {
        Optional<ArticleModel> article = msgRepo.findById(id);
        if (!article.isPresent()) {
            log.warn(LOGGING_MISSING_PAGE_WARN, request.getRemoteAddr(), request.getRequestURL());
            return new ResponseEntity<>(new Object(), HttpStatus.BAD_REQUEST);
        }
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
