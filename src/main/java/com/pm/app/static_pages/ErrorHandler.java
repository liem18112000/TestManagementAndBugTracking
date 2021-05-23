package com.pm.app.static_pages;


import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandler implements ErrorController {

    @RequestMapping("/errors")
    public String showError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return errorHandle(status);
    }

    @NotNull
    private String errorHandle(@NotNull Object status) {

        int statusCode = Integer.parseInt(status.toString());

        if(statusCode == HttpStatus.NOT_FOUND.value()) {
            return "errors/404";
        }
        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return "errors/500";
        }

        return "errors/error";
    }
}
