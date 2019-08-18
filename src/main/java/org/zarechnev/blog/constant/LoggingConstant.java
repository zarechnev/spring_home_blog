package org.zarechnev.blog.constant;

public class LoggingConstant {

    private LoggingConstant() {
    }

    public static final String LOGGING_CLIENT_INFO = "Client with IP {} come to to URL {}";
    public static final String LOGGING_MISSING_PAGE_WARN = "Client with IP {} try to open missing page with URL {}";
    public static final String LOGGING_API_PROCESSING_ERROR = "Error while process article from json: {}\n. URL: {}, type {}, from client {}.\n With error: ";
    public static final String LOGGING_API_ARTICLE_DOESNT_EXIST = "Article with number %d doesn't exist!";

}
