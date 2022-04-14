package com.service.impl;

import com.domain.User;
import com.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    private static Long increment = 1l;
    private List<User> users;

    public UserServiceImpl() {
        this.users = new ArrayList<User>();
        users.add(new User(increment++, "admin", "1234", true));
        users.add(new User(increment++, "user", "1234", false));
        users.add(new User(increment++, "shiki", "1234", true));
    }


    @Override
    public User addUser(User u) {
        u.setId(increment++);
        users.add(u);
        return u;
    }

    @Override
    public User updateUser(User u) {
        if(u.getId() == 1) return null;
        this.users = this.users.stream().map(u1-> u1.getId() == u.getId() ? u : u1).collect(Collectors.toList());
        return u;
    }

    @Override
    public boolean deleteById(Long id) {
        if(id== 1) return false;
        return this.users.removeIf(u-> u.getId() == id);
    }

    @Override
    public User findById(Long id) {
        return this.users.stream().filter(u-> u.getId() == id).findFirst().orElse(null);
    }

    @Override
    public User findByUserName(String name) {
        return this.users.stream().filter(u-> u.getUsername().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @Override
    public List<User> findAll(Integer page) {
        if(page == null) return this.users;
        return this.users.stream().skip(page * 5).limit(5).collect(Collectors.toList());
    }

    @Override
    public List<User> searchUser(String q) {
        return this.users.stream().filter(u-> u.getUsername().contains(q)).collect(Collectors.toList());
    }

    @Override
    public Integer getTotalPage() {
        return this.users.size() / 5;
    }
}
