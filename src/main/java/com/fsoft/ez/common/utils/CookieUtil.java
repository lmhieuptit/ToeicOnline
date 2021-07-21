package com.fsoft.ez.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void createCookie(HttpServletResponse response, String key, String value) {
        // create a cookie
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        // add cookie to response
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String key) {
        if (request.getCookies() == null) {
            return null;
        }
        String token = "";
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(key)) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }

    public static Cookie getCookieByName(HttpServletRequest request, String key) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(key)) {
                return cookie;
            }
        }
        return null;
    }

    public static void clearAllCookies(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                cookie.setMaxAge(0);
                cookie.setValue(null);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }
}
