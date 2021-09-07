package com.weibo.ddd.repository;

import com.weibo.ddd.domain.order.Order;
import com.weibo.ddd.domain.order.OrderId;
import com.weibo.ddd.domain.order.StoreId;

public interface OrderRepository extends Repository<Order, OrderId> {

    // 自定义有多个条件的查询接口
    Order findInStore(OrderId id, StoreId storeId);
}
