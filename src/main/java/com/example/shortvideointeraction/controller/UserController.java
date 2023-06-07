package com.example.shortvideointeraction.controller;

import com.example.shortvideointeraction.jsonRequest.PageJsonRequest;
import com.example.shortvideointeraction.model.User;
import com.example.shortvideointeraction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(path="/svi")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping(path = "/add/user")
    public @ResponseBody String addUser(@RequestParam Integer userId, @RequestParam String name)
    {
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setAddTime(new Date());
        userRepository.save(user);
        return "User added";
    }

    @DeleteMapping(path = "/delete/user")
    public @ResponseBody String deleteUser(@RequestParam Integer userId) {
        userRepository.deleteById(userId);
        return "User deleted";
    }

    @GetMapping(path = "/all/user")
    public @ResponseBody Page<User> allUser(@RequestBody PageJsonRequest pageJsonRequest)
    {
        Integer page = pageJsonRequest.getPage();
        Integer size = pageJsonRequest.getSize();
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }
}
