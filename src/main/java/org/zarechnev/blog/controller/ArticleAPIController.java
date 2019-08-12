package org.zarechnev.blog.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zarechnev.blog.entity.Article;
import org.zarechnev.blog.repository.ArticleRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

import static org.zarechnev.blog.constant.Answer.SUCCESSFUL_ANSWER;
import static org.zarechnev.blog.constant.Answer.UNSUCCESSFUL_ANSWER;
import static org.zarechnev.blog.constant.ControllerPathURLs.*;
import static org.zarechnev.blog.constant.LoggingConstant.*;

/**
 * The controller for API.
 */
@Slf4j
@RestController
@RequestMapping(API_URL_PATH + ARTICLE_URL_PATH)
public class ArticleAPIController {
    @Autowired
    private ArticleRepository articleRepo;

    /**
     * Возвращаем все статьи в виде json-объектов
     *
     * @param request - HttpServletRequest request
     * @return - all articles in json
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> allArticlesInJson(HttpServletRequest request) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        return new ResponseEntity<>(articleRepo.findAll(), HttpStatus.OK);
    }

    /**
     * Возвращаем статью в виде json-объекта
     *
     * @param request - HttpServletRequest request
     * @param id      - article id
     * @return - article as json-object
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> articleByIdInJson(HttpServletRequest request, @PathVariable("id") long id) {
        Optional<Article> article = articleRepo.findById(id);
        if (!article.isPresent()) {
            log.warn(LOGGING_MISSING_PAGE_WARN, request.getRemoteAddr(), request.getRequestURL());
            return new ResponseEntity<>(new Object(), HttpStatus.BAD_REQUEST);
        }
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    /**
     * Создаём объект "Статья" по переданному json'у
     * Пример запроса:
     * {
     * "author": "Author",
     * "title": "Title",
     * "article": "Article body"
     * }
     *
     * @param request       - HttpServletRequest
     * @param articleInJson - Статья в формате json
     * @return - Ответ в виде "сырой" строки
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addArticle(HttpServletRequest request, @RequestBody String articleInJson) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(articleInJson);
            Article article = new Article(
                    jsonNode.get("author").asText(),
                    jsonNode.get("title").asText(),
                    jsonNode.get("article").asText()
            );
            this.articleRepo.save(article);
        } catch (IOException | NullPointerException e) {
            log.error(LOGGING_API_PROCESSING_ERROR, articleInJson, request.getRequestURI(), request.getMethod(),
                    request.getRemoteAddr(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UNSUCCESSFUL_ANSWER);
        }
        return ResponseEntity.status(HttpStatus.OK).body(SUCCESSFUL_ANSWER);
    }

    /**
     * Обновляем статью по переданному json-объекту
     * Пример запроса:
     * {
     * "author": "Author",
     * "title": "Title",
     * "article": "Article body"
     * }
     *
     * @param request - HttpServletRequest request
     * @return - response code depending result
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String updateArticle(HttpServletRequest request, @PathVariable("id") long id, @RequestBody String articleInJson) {
        log.info(LOGGING_CLIENT_INFO, request.getRemoteAddr(), request.getRequestURL());
        JsonNode jsonNode;
        String author;
        String title;
        String articleBody;

        try {
            jsonNode = new ObjectMapper().readTree(articleInJson);
            author = jsonNode.get("author").asText();
            title = jsonNode.get("title").asText();
            articleBody = jsonNode.get("article").asText();
        } catch (IOException | NullPointerException e) {
            log.error(LOGGING_API_PROCESSING_ERROR, articleInJson, request.getRequestURI(), request.getMethod(),
                    request.getRemoteAddr(), e);
            return UNSUCCESSFUL_ANSWER;
        }

        try {
            Optional<Article> articleModel = articleRepo.findById(id);
            if (articleModel.isPresent()) {
                Article article = articleModel.get();
                article.setAuthor(author);
                article.setArticleBody(articleBody);
                article.setArticleTitle(title);
                this.articleRepo.save(article);
            } else throw new NullPointerException(String.format(LOGGING_API_ARTICLE_DOESNT_EXIST, id));

        } catch (Exception e) {
            log.error(LOGGING_API_PROCESSING_ERROR, articleInJson, request.getRequestURI(), request.getMethod(),
                    request.getRemoteAddr(), e);
            return UNSUCCESSFUL_ANSWER;
        }

        return SUCCESSFUL_ANSWER;
    }
}
