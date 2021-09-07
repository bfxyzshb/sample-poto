package com.weibo.ddd.converter;


import com.weibo.ddd.data.OrderDO;
import com.weibo.ddd.domain.order.Order;

public class OrderDataConverter {
  public static final OrderDataConverter INSTANCE = new OrderDataConverter();

  public Order fromData(OrderDO orderDO) {
    return null;
  }

  public OrderDO toData(Order aggregate) {
    return null;
  }

}
