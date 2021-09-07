package com.weibo.ddd.domain.order;


import com.weibo.poto.entity.Identifier;

public class OrderId extends Identifier<Long> {
    public Long id;

    public OrderId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderId{" +
                "id=" + id +
                '}';
    }
}
