package com.graphql;

import com.graphql.directivewring.ConnectionSelectionDirectiveWring;
import com.graphql.query.ConnectionQuery;
import com.graphql.scalar.ScalarTypeUtils;
import graphql.GraphQLContext;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import graphql.kickstart.autoconfigure.tools.SchemaDirective;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GraphqlConfiguration {

    private final ConnectionQuery connectionQuery;

    public GraphqlConfiguration(ConnectionQuery connectionQuery) {
        this.connectionQuery = connectionQuery;
    }

    @Bean
    public SchemaDirective connectionSelectSchemaDirective() {
        return new SchemaDirective(
                "ConnectionSelect",
                new ConnectionSelectionDirectiveWring(connectionQuery)
        );
    }

    @Bean
    public Instrumentation instrumentationState() {
        return new TracingInstrumentation();
    }

    @Bean
    public GraphQLScalarType longScalar() {
        return ScalarTypeUtils.longScalar();
    }

    @Bean
    public GraphQLScalarType dateScalar() {
        return ScalarTypeUtils.dateScalar();
    }

}
