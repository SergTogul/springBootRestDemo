package com.socialnetwork.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class ErrorController {

    @GetMapping("/error")
    public String showErrorPage() {
        return "There's an error";
    }

}
