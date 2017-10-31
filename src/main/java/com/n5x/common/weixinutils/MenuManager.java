package com.n5x.common.weixinutils;

import com.n5x.common.weixinutils.model.Button;
import com.n5x.common.weixinutils.model.CommonButton;
import com.n5x.common.weixinutils.model.ComplexButton;
import com.n5x.common.weixinutils.model.Menu;

/**
 * Created by Administrator on 2017/10/24.
 */
public class MenuManager {




    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {

        CommonButton btn11 = new CommonButton();
        btn11.setName("天气预报");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("公交查询");
        btn12.setType("click");
        btn12.setKey("12");

        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("生活助手");
        //一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12 });


        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("休闲驿站");
        mainBtn2.setSub_button(new CommonButton[] {btn11, btn12});


        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new CommonButton[] {btn11, btn12  });
        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }

    public  static  void main(String[] args) {
        // 第三方用户唯一凭证
        String appId = "000000000000000000";
        // 第三方用户唯一凭证密钥
        String appSecret = "00000000000000000000000000000000";

        // 调用接口获取access_token
        WeixinToken at = CommonUtil.getToken();

        if (null != at) {
            // 调用接口创建菜单
            int result = CommonUtil.createMenu(getMenu(), at.getAccessToken());

            // 判断菜单创建结果
            if (0 == result){}
//                log.info("菜单创建成功！");
//            else
//                log.info("菜单创建失败，错误码：" + result);
        }
    }
}
