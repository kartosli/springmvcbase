package com.n5x.Mapper;

import com.n5x.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */
public interface  UserMapper {
    @Select("select * from user_nothing where id = #{id}")
    User findUserById(int id);

    @Insert("insert into user_nothing(username,password) values(#{username},#{password})")
    void addUser(User user);

    @Select("select * from user_nothing")
    List<User> getAllUsers();

}
