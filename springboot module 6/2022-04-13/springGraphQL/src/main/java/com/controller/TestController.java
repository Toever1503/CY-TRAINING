package com.controller;


import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private GraphQL graphQL;

    @PostMapping("test")
    public Object test(@RequestBody String q) {
        return graphQL.execute(q);
    }

}
