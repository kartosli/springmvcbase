package com.n5x.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n5x.Mapper.UserTestMapper;
import com.n5x.Model.UserTest;
import com.n5x.Model.example.UserTestExample;
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
@ContextConfiguration(locations={"classpath:myspring_test.xml"})
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