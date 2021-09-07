package com.weibo.ddd.dao;

import com.weibo.ddd.data.OrderDO;

import java.util.List;

public interface OrderDAO {

    OrderDO findById(long longValue);

    int delete(OrderDO orderDO);

    int update(OrderDO orderDO);

    int insert(OrderDO orderDO);


    OrderDO findInStore(Long value, Long value1);

}
