package com.graphql.mutation;

import com.domain.Staff;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StaffMutationResolver implements GraphQLMutationResolver {

    public Staff createStaff(Date date, DataFetchingEnvironment env) {
        return null;
    }
}
