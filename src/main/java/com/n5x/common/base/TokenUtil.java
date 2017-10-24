package com.n5x.common.base;

import com.n5x.common.system.Base64Utils;
import com.n5x.common.util.AES256Encryption;
import com.n5x.common.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by Administrator on 2017/10/23.
 */
public class TokenUtil {

    public static long loginday=30;
    public static String createLoginToken(String userId) {
        String yzttoken="";
        String resToken = "";
//        String[] splitArray = sysToken.split("\\|\\|");

//        resToken = "13928189263||93e5c6698359e48887163914fc251f9d||2||ANDROID||1.9.1  OnePlus|A0001|6.0.1";
        yzttoken = userId
                + "||"
                + new Date().getTime();

        try {
            byte[] key = AES256Encryption.initkey();
            byte[] data = AES256Encryption.encrypt(yzttoken.getBytes(), key);
            String strEncryp = "";
			/*
			 * for(int i = 0;i<data.length;i++){ strEncryp +=
			 * String.format("%x", data[i]); //System.out.printf("%x", data[i]);
			 * }
			 */
            strEncryp = Base64Utils.encode(data);
            yzttoken = strEncryp;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String enCodeParam = yzttoken.replace("+", "%2B").replace("/", "%2F");
        return enCodeParam;
    }

    /**
     * 判断是否登录是否过期
     * @param dateTime
     * @return
     */
    public static boolean isExpireLogin(long dateTime) throws  Exception{
        long SysTime = System.currentTimeMillis();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date  smdate=sdf.parse("2017-09-01");
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);

        dateTime  = cal.getTimeInMillis();
        long between_days=(SysTime-dateTime)/(1000*3600*24);
        if(between_days>=loginday){
            return true;
        }else{
            return false;
        }

    }

    //解密app返回的token
    public static Map<String,Object> getAppTokenDate(String token) throws Exception{
        Map<String,Object> result=new HashMap<String,Object>();
        if(!StringUtil.isBlank(token)){
            if(token.indexOf("||")==-1){
                token = token.replace("%2B", "+").replace("%2F", "/");
                byte[] aeskey = AES256Encryption.initkey();
                byte[] aesdata = AES256Encryption.decrypt(token,aeskey);
                token = new String(aesdata);
            }
            String[] datas = token.split("\\|\\|");
            String mobile = "";
            String password = "";
            String memberType = "";
            String key = "";
            String version = "";
            String childid = "";
            String crdate = "";
            if(datas.length>1){
                mobile = datas[0];
                crdate = datas[1];
            }
            result.put("userId",mobile);
            result.put("crdate",crdate);
        }
        return result;
    }


    public static void main(String[] args){
        try {
            isExpireLogin(2234324l);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String token = createLoginToken("1345");
        try {
            Map tokenMap = getAppTokenDate(token);
            System.out.print(tokenMap);
        }catch (Exception e){

        }

    }
}
