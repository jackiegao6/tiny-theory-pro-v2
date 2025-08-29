package com.gzc.infrastructure.dao;

import com.gzc.infrastructure.dao.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {

    User queryUserByPhone(String phone);

    void createUser(User user);

}
