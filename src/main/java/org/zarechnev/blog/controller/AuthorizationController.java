package org.zarechnev.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static org.zarechnev.blog.constant.ControllerPathURLs.AUTHORIZE_URL_PATH;
import static org.zarechnev.blog.constant.LoggingConstant.loggingClientInfo;

/**
 * The controller for login-logout path.
 */
@Slf4j
@Controller
public class AuthorizationController {
    @Autowired
    private Environment env;

    /**
     * Authorization page.
     *
     * @param request the request
     * @return string
     */
    @GetMapping(AUTHORIZE_URL_PATH)
    public String authPage(HttpServletRequest request){
        log.info(loggingClientInfo, request.getRemoteAddr(), request.getRequestURL());
        return "authorization";
    }
}
