package com.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.domain.User;
import com.domain.dto.UserDto;
import com.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {
    private final UserService userService;

    public UserQueryResolver(UserService userService) {
        this.userService = userService;
    }

    public List<UserDto> getUsers(int first, int offset) {
        return userService.findAll(PageRequest.of(offset, first)).getContent();
    }
    public List<UserDto> getUsers(String q, int first, int offset) {
        return userService.search(q, PageRequest.of(offset, first)).getContent();
    }

    public UserDto getUser(Long id){
        return userService.findById(id);
    }

}
