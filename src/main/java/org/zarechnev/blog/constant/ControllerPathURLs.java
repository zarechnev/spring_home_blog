package org.zarechnev.blog.constant;

public class ControllerPathURLs {

    private ControllerPathURLs() {
    }

    public static final String ROOT_URL_PATH    = "/";
    public static final String ARTICLE_URL_PATH = "/article";
    public static final String ARTICLE_URL_PATH_PATTERN = ARTICLE_URL_PATH + "/**";
    public static final String USER_URL_PATH    = "/user";
    public static final String ADMIN_URL_PATH   = "/admin";
    public static final String LOGIN_URL_PATH = "/login";
    public static final String LOGOUT_URL_PATH = "/logout";
    public static final String API_URL_PATH     = "/api";
    public static final String CSS_URL_PATH_PATTERN = "/css/**";
    public static final String JS_URL_PATH_PATTERN = "/js/**";
    public static final String SITE_URL_PROPERTY = "site.url";

}
