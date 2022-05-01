package com.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.domain.dto.UserDto;
import com.domain.model.UserModel;
import com.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserMutationResolver implements GraphQLMutationResolver {
    private final UserService userService;

    public UserMutationResolver(UserService userService) {
        this.userService = userService;
    }

    public UserDto createUser(UserModel model) {
        return userService.saveOrUpdate(model);
    }

    public UserDto updateUser(Long id, UserModel model) {
        model.setId(id);
        return userService.saveOrUpdate(model);
    }

    public boolean deleteUser(Long id) {
        return userService.deleteById(id);
    }
}
