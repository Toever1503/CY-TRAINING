package com.controller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Order(10)
public class Controller {

//    @Autowired
//    private GraphQLSchema graphQLSchema;


//    @PostMapping("/graphql")
//    public ResponseEntity<Object> query(@RequestBody Map<String, Object> request) {
//        ExecutionResult executionResult = GraphQL.newGraphQL(graphQLSchema).build().execute((String) request.get("query"));
//        return ResponseEntity.ok(executionResult);
//    }
}
