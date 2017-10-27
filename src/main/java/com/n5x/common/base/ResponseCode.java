package com.n5x.common.base;

import com.n5x.common.util.StringUtil;

/**
 * 响应代码定义
 * Created by Simon on 2016/4/29.
 */
public class ResponseCode {

    /**
     * C开头代码表示系统共用
     */
    public static final String Common_Success = "C000";  //处理成功

    public static final String Common_Fail = "C500"; //处理失败

    public static final String Common_Error = "C505"; //系统报错

    public static final String Common_NotLogIn = "C501"; //未登陆

    public static final String Common_ParamError = "C504"; //参数错误

    public static final String Common_NoAccess = "C506"; //无权限

    public static final String Common_DataNotFound = "C404"; //无数据

    /**
     * S开头代码表示接口自定义(此处不作定义,由各个接口独立定义)
     */

    public static String getMessage(String code) {
        if (!StringUtil.isEmpty(code)) {
            if (Common_Success.equals(code))
                return ResponseMsg.Common_Success;
            else if (Common_Fail.equals(code))
                return ResponseMsg.Common_Fail;
            else if (Common_Error.equals(code))
                return ResponseMsg.Common_Error;
            else if (Common_NotLogIn.equals(code))
                return ResponseMsg.Common_NotLogIn;
            else if (Common_ParamError.equals(code))
                return ResponseMsg.Common_ParamError;
            else if (Common_DataNotFound.equals(code))
                return ResponseMsg.Common_DataNotFound;
        }
        return "";
    }


//    public static String getCommon_NoAccess() {
//        return ResponseMsg.Common_NoAccess;
//    }
}
