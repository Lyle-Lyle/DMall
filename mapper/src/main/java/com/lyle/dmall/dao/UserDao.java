package com.lyle.dmall.dao;

import com.lyle.dmall.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    //Register
    public int insertUser(User user);

    public User queryUserByName(String name);
}
