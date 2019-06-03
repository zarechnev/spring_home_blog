package org.zarechnev.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zarechnev.blog.repository.ArticleRepository;
import org.zarechnev.blog.repository.SectionRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.zarechnev.blog.constant.ControllerPathURLs.*;
import static org.zarechnev.blog.constant.LoggingConstant.LOGGING_CLIENT_INFO;

/**
 * The controller for API.
 */
@Slf4j
@Controller("/api")
public class APIController {
    @Autowired
    private ArticleRepository msgRepo;

    @Autowired
    private SectionRepository articleSectionRepo;

    @Autowired
    private Environment env;

}
