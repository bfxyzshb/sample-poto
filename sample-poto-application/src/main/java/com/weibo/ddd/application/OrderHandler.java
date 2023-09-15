package com.weibo.ddd.application;

import com.google.common.collect.Lists;
import com.weibo.ddd.commands.OrderCreateCommand;
import com.weibo.ddd.domain.order.Address;
import com.weibo.ddd.domain.order.LineItem;
import com.weibo.ddd.domain.order.Order;
import com.weibo.ddd.domain.order.OrderId;
import com.weibo.ddd.repository.OrderRepository;
import com.weibo.ddd.service.OrderConfirmInterface;
import com.weibo.poto.bus.command.annotation.CommandHandler;
import com.weibo.poto.bus.common.CommonMessage;
import com.weibo.poto.bus.event.EventBus;
import com.weibo.poto.bus.event.EventMessage;
import com.weibo.poto.spi.ExtensionExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName OrderHandler
 * @Description 应用层负责服务的编排
 * @Author hebiao1
 * @Date 2021/9/1 5:44 下午
 * @Version 1.0
 */
@Component
public class OrderHandler {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ExtensionExecutor extensionExecutor;

    @Autowired
    EventBus eventBus;

    @CommandHandler
    public String handleOrderCreateCommand(OrderCreateCommand orderCreateCommand) {
        Order order = new Order();
        order.setId(new OrderId(1L));
        order.setAddress(new Address("", "", orderCreateCommand.getCity(), ""));
        List<LineItem> lineItems = Lists.newArrayList();
        lineItems.add(new LineItem(new OrderId(1L), orderCreateCommand.getMoney()));
        order.setLineItems(lineItems);
        //扩展执行
        extensionExecutor.execute(OrderConfirmInterface.class, orderCreateCommand.getProtocol(), o -> o.confirm(""));
        orderRepository.save(order);
        //发送事件eg:通知相关物流人员下单成功准备发货
        EventMessage eventMessage= CommonMessage.asEventMessage("");
        eventBus.dispatch(eventMessage);

        return "success";
    }
}
