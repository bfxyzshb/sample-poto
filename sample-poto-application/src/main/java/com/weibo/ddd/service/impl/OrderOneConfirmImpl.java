package com.weibo.ddd.service.impl;

import com.weibo.ddd.service.OrderConfirmInterface;

/**
 * @ClassName OrderOneConfirmImpl
 * @Description TODO
 * @Author hebiao1
 * @Date 2021/9/2 5:27 下午
 * @Version 1.0
 */
public class OrderOneConfirmImpl implements OrderConfirmInterface {
    @Override
    public String confirm(String info) {
        System.out.println("one");
        return info;
    }
}
