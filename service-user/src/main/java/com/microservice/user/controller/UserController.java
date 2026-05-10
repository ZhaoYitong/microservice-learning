package com.microservice.user.controller;

import com.microservice.common.response.ApiResponse;
import com.microservice.user.entity.User;
import com.microservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        return ApiResponse.success("获取用户列表成功", userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ApiResponse.success("获取用户信息成功", user))
                .orElse(ApiResponse.error(404, "用户不存在"));
    }

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ApiResponse.success("用户创建成功", savedUser);
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setEmail(userDetails.getEmail());
                    user.setRealName(userDetails.getRealName());
                    user.setPhone(userDetails.getPhone());
                    return ApiResponse.success("用户更新成功", userRepository.save(user));
                })
                .orElse(ApiResponse.error(404, "用户不存在"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ApiResponse.success("用户删除成功", null);
        }
        return ApiResponse.error(404, "用户不存在");
    }
}
