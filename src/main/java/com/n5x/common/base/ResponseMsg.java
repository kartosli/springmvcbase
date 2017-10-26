package com.n5x.common.base;

/**
 * 响应代码定义
 * Created by Simon on 2016/4/29.
 */
public class ResponseMsg {

    /**
     * C开头代码表示系统共用
     */
    public static String Common_Success = "处理成功";  //C000

    public static String Common_Fail = "处理失败"; //C500

    public static String Common_Error = "系统报错"; //C505

    public static String Common_NotLogIn = "未登陆"; //C501

    public static String Common_NoSchoolAndGrade = "未设置学校和年级"; //C502

    public static String Common_NoVersion = "未设置教材版本"; //C503

    public static String Common_ParamError = "参数错误"; //C504

    public static String Common_NoAccess = "无权限"; //C506

    public static String Common_DataNotFound = "无数据"; //C404

    /**
     * S开头代码表示接口自定义(此处不作定义,由各个接口独立定义)
     */



}
