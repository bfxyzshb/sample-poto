package com.weibo.ddd.domain.order;

import com.weibo.poto.entity.AbstractAggregateRoot;
import com.weibo.poto.entity.AggregateRoot;
import lombok.Data;

import java.util.List;
@Data
public class Order extends AbstractAggregateRoot<OrderId> {
    private OrderId id;
    private String title;
    private Address address;
    private List<LineItem> lineItems;

    @Override
    public OrderId getId() {
        return id;
    }

    public void setId(OrderId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public Order(OrderId id, String title, List<LineItem> lineItems) {
        this.id = id;
        this.title = title;
        this.lineItems = lineItems;
    }

    public Order(OrderId id, String title, Address address, List<LineItem> lineItems) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.lineItems = lineItems;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address=" + address +
                ", lineItems=" + lineItems +
                '}';
    }
}