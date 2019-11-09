package com.llf.blog.service;

import com.llf.blog.pojo.User;

public interface UserService {

    User checkUser(String username, String password);
}
