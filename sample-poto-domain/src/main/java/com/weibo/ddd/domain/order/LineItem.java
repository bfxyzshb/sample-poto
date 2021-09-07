package com.weibo.ddd.domain.order;

import com.weibo.poto.entity.AbstractDomainObject;
import com.weibo.poto.entity.DomainObject;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LineItem extends AbstractDomainObject<OrderId> {
    private OrderId id;
    private BigDecimal money;

    public LineItem(OrderId orderId, BigDecimal money) {
        this.id = orderId;
        this.money = money;
    }

    @Override
    public OrderId getId() {
        return id;
    }

    public void setId(OrderId id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }
}