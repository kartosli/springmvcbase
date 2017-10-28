package com.n5x.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n5x.Model.UserTest;
import com.n5x.Model.example.UserTestExample;
import com.n5x.Service.ITestUserService;
import com.n5x.Mapper.UserTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
@Service
@Transactional
public class ITestUserServiceImpl implements ITestUserService {
    @Autowired
    UserTestMapper userMapper;

    public List<UserTest> findUserList() {
        PageHelper.startPage(1, 2);
        UserTestExample example = new UserTestExample();
        List<UserTest> list = userMapper.selectByExample(example);
        PageInfo<UserTest> upage = new PageInfo<UserTest>(list);
        List<UserTest> ulist = upage.getList();
        return ulist;
    }
}
