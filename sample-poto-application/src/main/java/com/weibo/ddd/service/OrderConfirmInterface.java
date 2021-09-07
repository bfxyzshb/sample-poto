package com.weibo.ddd.service;

import com.weibo.poto.spi.annotation.SPI;

/**
 * @ClassName OrderConfirmInterface
 * @Description TODO
 * @Author hebiao1
 * @Date 2021/9/2 5:26 下午
 * @Version 1.0
 */
@SPI
public interface OrderConfirmInterface {

    public String confirm(String info);
}
