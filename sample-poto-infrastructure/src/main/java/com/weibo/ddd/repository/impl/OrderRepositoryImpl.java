package com.weibo.ddd.repository.impl;


import com.weibo.ddd.converter.LineItemDataConverter;
import com.weibo.ddd.converter.OrderDataConverter;
import com.weibo.ddd.dao.LineItemDAO;
import com.weibo.ddd.dao.OrderDAO;
import com.weibo.ddd.data.LineItemDO;
import com.weibo.ddd.data.OrderDO;
import com.weibo.ddd.domain.order.LineItem;
import com.weibo.ddd.domain.order.Order;
import com.weibo.ddd.domain.order.OrderId;
import com.weibo.ddd.domain.order.StoreId;
import com.weibo.ddd.repository.OrderRepository;
import org.springframework.stereotype.Repository;


public class OrderRepositoryImpl implements OrderRepository {
    private  OrderDAO orderDAO; // 具体的DAO接口
    private final OrderDataConverter converter; // 转化器
    private  LineItemDAO lineItemDAO;
    private final LineItemDataConverter lineItemConverter;

    public OrderRepositoryImpl() {
        /*his.orderDAO = orderDAO;
        this.lineItemDAO = lineItemDAO;*/
        this.converter = OrderDataConverter.INSTANCE;
        this.lineItemConverter = LineItemDataConverter.INSTANCE;
    }

    @Override
    public Order find(OrderId orderId) {
        OrderDO orderDO = orderDAO.findById(orderId.getId());
        return converter.fromData(orderDO);
    }

    @Override
    public void remove(Order aggregate) {
        OrderDO orderDO = converter.toData(aggregate);
        orderDAO.delete(orderDO);
    }

    @Override
    public void save(Order aggregate) {
        if (aggregate.getId() != null && aggregate.getId().getId() > 0) {
            // 每次都将Order和所有LineItem全量更新
            OrderDO orderDO = converter.toData(aggregate);
            orderDAO.update(orderDO);
            for (LineItem lineItem : aggregate.getLineItems()) {
                save(lineItem);
            }
        } else {
            // insert
            OrderDO orderDO = converter.toData(aggregate);
            orderDAO.insert(orderDO);
            aggregate.setId(converter.fromData(orderDO).getId());
        }
    }

    private void save(LineItem lineItem) {
        LineItemDO lineItemDO = lineItemConverter.toData(lineItem);
        if (lineItem.getId() != null && lineItem.getId().getId() > 0) {
            lineItemDAO.update(lineItemDO);
        } else {
            lineItemDAO.insert(lineItemDO);
            lineItem.setId(lineItemConverter.fromData(lineItemDO).getId());
        }
    }

    @Override
    public void attach(Order aggregate) {
    }

    @Override
    public void detach(Order aggregate) {
    }

    @Override
    public Order findInStore(OrderId id, StoreId storeId) {
        return null;
    }
}
