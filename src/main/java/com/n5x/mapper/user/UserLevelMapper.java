package com.n5x.mapper.user;

import com.n5x.model.user.UserLevel;
import com.n5x.model.user.UserLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLevelMapper {
    int countByExample(UserLevelExample example);

    int deleteByExample(UserLevelExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserLevel record);

    int insertSelective(UserLevel record);

    List<UserLevel> selectByExample(UserLevelExample example);

    UserLevel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserLevel record, @Param("example") UserLevelExample example);

    int updateByExample(@Param("record") UserLevel record, @Param("example") UserLevelExample example);

    int updateByPrimaryKeySelective(UserLevel record);

    int updateByPrimaryKey(UserLevel record);
}