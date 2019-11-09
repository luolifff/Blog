package com.llf.blog.service.impl;

import com.llf.blog.dao.UserRepository;
import com.llf.blog.pojo.User;
import com.llf.blog.service.UserService;
import com.llf.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
