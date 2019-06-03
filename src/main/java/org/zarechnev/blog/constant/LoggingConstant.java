package org.zarechnev.blog.constant;

public class LoggingConstant {
    /**
     * Disable instantiate class.
     */
    private LoggingConstant() {
    }

    public final static String LOGGING_CLIENT_INFO = "Client with IP {} come to to URL {}";
    public final static String LOGGING_MISSING_PAGE_WARN = "Client with IP {} try to open missing page with URL {}";
}
