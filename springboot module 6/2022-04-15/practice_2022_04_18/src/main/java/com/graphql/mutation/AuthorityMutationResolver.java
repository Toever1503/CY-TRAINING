package com.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.domain.model.AuthorityModel;
import com.service.AuthorityService;
import org.springframework.stereotype.Component;

@Component
public class AuthorityMutationResolver implements GraphQLMutationResolver {
    private final AuthorityService authorityService;

    public AuthorityMutationResolver(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    public AuthorityModel createAuthority(AuthorityModel model) {
        return authorityService.saveOrUpdate(model);
    }

    public AuthorityModel updateAuthority(Integer id, AuthorityModel model) {
        model.setId(id);
        return authorityService.saveOrUpdate(model);
    }

    public boolean deleteAuthority(Integer id) {
        return authorityService.deleteById(id);
    }

}
