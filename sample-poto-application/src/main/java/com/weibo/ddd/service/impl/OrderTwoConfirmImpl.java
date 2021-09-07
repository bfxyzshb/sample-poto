package com.weibo.ddd.service.impl;

import com.weibo.ddd.service.OrderConfirmInterface;

/**
 * @ClassName OrderTwoConfirmImpl
 * @Description TODO
 * @Author hebiao1
 * @Date 2021/9/2 5:28 下午
 * @Version 1.0
 */
public class OrderTwoConfirmImpl implements OrderConfirmInterface {

    @Override
    public String confirm(String info) {
        System.out.println("two");
        return info;
    }
}
