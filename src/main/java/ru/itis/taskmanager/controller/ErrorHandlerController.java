package ru.itis.taskmanager.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlerController implements ErrorController {

    @RequestMapping(value = "/error")
    public String error(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("status", "404 Not Found");
                model.addAttribute("message", "Request resource not found");
            }

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("status", "403 Forbidden");
                model.addAttribute("message", "Access denied");

            }

            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("status", "500 Internal Server");
                model.addAttribute("message", "Service doesn't work temporarily");
            }

        } else {
            model.addAttribute("status", "520 Unknown");
            model.addAttribute("message", "");
        }
        return "exception_page";

    }
}