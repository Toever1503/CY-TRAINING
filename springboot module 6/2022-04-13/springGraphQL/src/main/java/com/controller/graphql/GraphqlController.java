package com.controller.graphql;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/graphql")
public class GraphqlController {
    @Autowired
    private GraphQL graphQL;

    @PostMapping
    public ExecutionResult graphql(@RequestBody Map<String, String> q) {
        return graphQL.execute(q.get("query"));
    }

}
