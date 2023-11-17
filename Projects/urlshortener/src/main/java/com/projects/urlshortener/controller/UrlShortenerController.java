package com.projects.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("urls")
public class UrlShortenerController {
    @GetMapping("{longUrl}")
    public String getUrl(@PathVariable String longUrl){
        return longUrl;

    }
}
