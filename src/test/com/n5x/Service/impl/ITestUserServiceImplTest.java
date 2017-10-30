package com.n5x.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n5x.mapper.UserTestMapper;
import com.n5x.model.UserTest;
import com.n5x.model.example.UserTestExample;
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
}