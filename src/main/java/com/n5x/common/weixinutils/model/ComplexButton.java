package com.n5x.common.weixinutils.model;

/**
 * Created by Administrator on 2017/10/24.
 */
public class ComplexButton extends Button  {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
