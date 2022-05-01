package com.resource;

import com.entity.dto.ResponseData;
import com.entity.model.UserModel;
import com.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAllUsers(@RequestParam(name = "q", required = false, defaultValue = "") String q, Pageable page) {
        if (q != null || !q.isBlank()){
            return ResponseData.get(userService.search(q, page));
        }
        return ResponseData.get(userService.findAll(page));
    }
    @GetMapping("{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id) {
        return ResponseData.get(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserModel model) {
        return ResponseData.update(userService.saveOrUpdate(model));
    }

    @PatchMapping("{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody UserModel model) {
        model.setId(id);
        return ResponseData.update(userService.saveOrUpdate(model));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        return ResponseData.delete(userService.deleteBYId(id));
    }
}
