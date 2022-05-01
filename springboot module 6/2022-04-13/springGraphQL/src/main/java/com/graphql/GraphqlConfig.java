package com.graphql;

import com.graphql.dataFetcher.VideoDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;

@Configuration
public class GraphqlConfig {


    @Value("classpath:schema.graphqls")
    Resource resource;


    @Autowired
    private VideoDataFetcher videoDataFetcher;

    @Autowired
    Map<String, SchemaDirectiveWiring> schemaDirectiveWiring;


    public RuntimeWiring runtimeWiring() {
        RuntimeWiring rw = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                        typeWiring.dataFetcher("Video", env -> videoDataFetcher.video(env))
                )
                .build();
        rw.getRegisteredDirectiveWiring().putAll(schemaDirectiveWiring);
        return rw;
    }

    public GraphQLSchema graphQLSchema() {
        TypeDefinitionRegistry typeRegistry = null;
        try {
            typeRegistry = new SchemaParser().parse(resource.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring());
        return schema;
    }

    @Bean
    public GraphQL graphQL() {
        return GraphQL.newGraphQL(graphQLSchema()).build();
    }
}
