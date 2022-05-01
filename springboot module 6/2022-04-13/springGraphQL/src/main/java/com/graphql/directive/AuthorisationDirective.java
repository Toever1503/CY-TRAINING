package com.graphql.directive;

import com.filter.SecurityFilterTest;
import graphql.language.StringValue;
import graphql.schema.*;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.springframework.stereotype.Component;


@Component("auth")
public class AuthorisationDirective implements SchemaDirectiveWiring {


    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {

        String targetAuthRole = ((StringValue) environment.getDirectives().get("auth").getArgument("role").getArgumentValue().getValue()).getValue();

        GraphQLFieldDefinition field = environment.getElement();
        GraphQLFieldsContainer parentType = environment.getFieldsContainer();
//        //
//        // build a data fetcher that first checks authorisation roles before then calling the original data fetcher
//        //
        DataFetcher originalDataFetcher = environment.getCodeRegistry().getDataFetcher(parentType, field);
        DataFetcher authDataFetcher = new DataFetcher() {
            @Override
            public Object get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
                if (SecurityFilterTest.CURRENT_ROLE == null) return null;
                else if (SecurityFilterTest.CURRENT_ROLE.equalsIgnoreCase("manager"))
                    return originalDataFetcher.get(dataFetchingEnvironment);
                return null;
            }
        };
//        //
//        // now change the field definition to have the new authorising data fetcher
        environment.getCodeRegistry().dataFetcher(parentType, field, authDataFetcher);
        return field;
    }
}
