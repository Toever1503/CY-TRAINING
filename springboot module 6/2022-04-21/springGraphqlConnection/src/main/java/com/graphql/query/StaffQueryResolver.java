package com.graphql.query;

import com.domain.Staff;
import com.graphql.connection.*;
import com.repository.StaffRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.Edge;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class StaffQueryResolver implements GraphQLQueryResolver {

    private final StaffRepository staffRepository;

    public StaffQueryResolver(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public CustomConnection<Staff> staffs(int page, int perPage, DataFetchingEnvironment env) {
//        int current = cursor
        Page<Staff> pageV = this.staffRepository.findAll(PageRequest.of(page, perPage > 50 ? 50 : perPage));
        List<Edge<Staff>> edges = pageV
                .getContent().stream()
                .map(v -> new DefaultCustomEdge<>(v))
                .collect(Collectors.toList());

        var pageInfo = new DefaultCustomPageInfo(pageV.hasNext(),
                pageV.hasPrevious(),
                pageV.getTotalPages(),
                pageV.getNumber(),
                pageV.getSize(),
                pageV.getTotalElements());

        return new DefaultCustomConnection<Staff>(edges, pageInfo);
    }
}
