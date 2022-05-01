package com.graphql;

import com.graphql.directivewring.ConnectionSelectionDirectiveWring;
import com.graphql.query.ConnectionQuery;
import graphql.kickstart.autoconfigure.tools.SchemaDirective;
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
}
