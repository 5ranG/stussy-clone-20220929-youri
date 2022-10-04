package com.stussy.stussyclone20220929youri.repository;

import com.stussy.stussyclone20220929youri.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {
    public int save(User user);
    public User findUserByEmail(String Email);
}
