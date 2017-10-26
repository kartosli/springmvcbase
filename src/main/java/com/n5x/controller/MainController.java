package com.n5x.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n5x.Model.User;
import com.n5x.Model.UserTest;
import com.n5x.Service.ITestUserService;
import com.n5x.common.base.JsonModel;
import com.n5x.common.base.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController extends BaseController{
    @Autowired
    ITestUserService iTestUserService;

//    private IUserService service = new UserServiceImpl();

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/nice", method = RequestMethod.GET)
    public String nice(Model model) {

        model.addAttribute("result", "hahaha 第一个参数");
        return "nice";
    }


    @RequestMapping(value = "/toJsonListIU")
    @ResponseBody
    public JsonModel toJsonItestU(User user) {
//        service.addUser(user); //一起测试了
        String test = request.getParameter("test");
        List list = iTestUserService.findUserList();

        if(null==list){
            //没有登录
//            return getResultError(ResponseCode.Common_NotLogIn);
            return getResultError(ResponseCode.Common_NotLogIn,"登录了嘛？小骚");
        }
        return getResult(list);
//        return list;
    }
}