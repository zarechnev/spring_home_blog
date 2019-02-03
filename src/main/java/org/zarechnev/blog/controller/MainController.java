package org.zarechnev.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zarechnev.blog.model.ArticleModel;
import org.zarechnev.blog.repository.BlogArticleRepository;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.zarechnev.blog.constant.ControllerPath.ADMIN_URL_PATH;
import static org.zarechnev.blog.constant.ControllerPath.ARTICLE_URL_PATH;

/**
 * The type Main controller.
 */
@Slf4j
@Controller
public class MainController {
    @Autowired
    private BlogArticleRepository msgRepo;

    @Autowired
    private Environment env;

    /**
     * Main WEB-Page.
     *
     * @param request the request
     * @param model   the model
     * @return string
     */
    @RequestMapping(value = "/", method = GET)
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
     * @param model   the model
     * @return string
     */
    @RequestMapping(value = ARTICLE_URL_PATH + "/{id}", method = GET)
    public String articleById(HttpServletRequest request, @PathVariable("id") long id, Map<String, Object> model) {
        ArticleModel article;

        if (msgRepo.findById(id).isPresent()) {
            article = msgRepo.findById(id).get();
        }
        else {
            log.warn(request.getRequestURL() + " does not exist!");
            return "redirect:" + env.getProperty("site.url");
        }

        log.info("someone come to us with URL: " + request.getRequestURL());
        model.put("article", article);
        model.put("articleUrlPath", ARTICLE_URL_PATH);
        model.put("siteUrl", env.getProperty("site.url"));

        return "oneArticle";
    }

    /**
     * Admin page.
     *
     * @param request the request
     * @return string
     */
    @RequestMapping(value = ADMIN_URL_PATH, method = GET)
    public String adminPage(HttpServletRequest request){
        log.info("Someone come to us with URL: " + request.getRequestURL());
        return "administration";
    }

    /**
     * Foundation template for HTML markup.
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping(value = "/foundation", method = GET)
    public String foundation(HttpServletRequest request) {
        log.info("Someone come to us with URL: " + request.getRequestURL());
        return "foundation";
    }
}
