package com.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.domain.model.AuthorityModel;
import com.service.AuthorityService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorityQueryResolver implements GraphQLQueryResolver {
    private final AuthorityService authorityService;

    public AuthorityQueryResolver(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    public List<AuthorityModel> getAuthorities(String q, int first, int offset) {
        return authorityService.search(q, PageRequest.of(first, offset)).getContent();
    }

    public List<AuthorityModel> getAuthorities(int first, int offset) {
        return authorityService.findAll(PageRequest.of(first, offset)).getContent();
    }

    public AuthorityModel getAuthority(Integer id) {
        return authorityService.findById(id);
    }
}
