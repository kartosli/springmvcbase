package com.n5x.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n5x.mapper.UserTestMapper;
import com.n5x.mapper.user.UserLevelMapper;
import com.n5x.model.UserTest;
import com.n5x.model.example.UserTestExample;
import com.n5x.model.user.UserLevel;
import com.n5x.model.user.UserLevelExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:myspring_test.xml"})
public class ITestUserServiceImplTest {
    @Autowired
    UserTestMapper userMapper;

    @Test
    public void findUserList() throws Exception {
        PageHelper.startPage(1, 2);
        UserTestExample example = new UserTestExample();
        List<UserTest> list = userMapper.selectByExample(example);
        PageInfo<UserTest> upage = new PageInfo<UserTest>(list);
        List<UserTest> ulist = upage.getList();
    }


    @Autowired
    UserLevelMapper userLevelMapper;

    @Test
    public void findULList() throws Exception {
        PageHelper.startPage(1, 2);
        UserLevelExample example = new UserLevelExample();
        List<UserLevel> list = userLevelMapper.selectByExample(example);
        PageInfo<UserLevel> upage = new PageInfo<UserLevel>(list);
        List<UserLevel> ulist = upage.getList();
    }
}