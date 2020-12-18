package com.kwic.kwic.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kwic.kwic.entities.Url;
import com.kwic.kwic.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/kwic")
public class UrlController {
    @Autowired
    UrlService urlService;

    @PostMapping("insert")
    ResponseEntity insert(@RequestBody Url url){
        urlService.insert(url);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @JsonFormat
    @GetMapping("/search/{keywords}")
    @ResponseBody
    List<Url> search(@PathVariable String keywords){
        List<Url> found = urlService.search(keywords.toLowerCase());
        System.out.println(keywords);
        return found;
    }
}
