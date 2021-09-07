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
import com.weibo.poto.repository.DbRepositorySupport;
import com.weibo.poto.repository.objectDiff.diff.Diff;
import com.weibo.poto.repository.objectDiff.diff.DiffList;
import com.weibo.poto.repository.objectDiff.diff.DiffType;
import com.weibo.poto.repository.objectDiff.diff.EntityDiff;
import org.springframework.stereotype.Repository;

/**
 * 支持跟踪的 OrderRepository 实现类
 */
@Repository
public class OrderRepositoryDiffImpl extends DbRepositorySupport<Order, OrderId> implements OrderRepository {

    private OrderDAO orderDAO; // 具体的DAO接口
    private OrderDataConverter converter; // 转化器
    private LineItemDAO lineItemDAO;
    private LineItemDataConverter lineItemConverter;

    public OrderRepositoryDiffImpl(OrderDAO orderDAO, LineItemDAO lineItemDAO) {
        super(Order.class);
        /*this.orderDAO = orderDAO;
        this.lineItemDAO = lineItemDAO;*/
        this.converter = OrderDataConverter.INSTANCE;
        this.lineItemConverter = LineItemDataConverter.INSTANCE;
    }

    public OrderRepositoryDiffImpl() {
        super(Order.class);
        this.converter = OrderDataConverter.INSTANCE;
        this.lineItemConverter = LineItemDataConverter.INSTANCE;
    }

    @Override
    protected void onInsert(Order aggregate) {
        OrderDO orderDO = converter.toData(aggregate);
        orderDAO.insert(orderDO);
        aggregate.setId(converter.fromData(orderDO).getId());
    }

    @Override
    protected Order onSelect(OrderId orderId) {
        OrderDO orderDO = orderDAO.findById(orderId.getId());
        return converter.fromData(orderDO);
    }

    @Override
    protected void onUpdate(Order aggregate, EntityDiff diff) {
        if (diff.isSelfModified()) {
            OrderDO orderDO = converter.toData(aggregate);
            orderDAO.update(orderDO);
        }

        Diff lineItemDiffs = diff.getDiff("lineItems");
        /*if (lineItemDiffs instanceof DiffList) {
            DiffList diffList = (DiffList) lineItemDiffs;
            for (Diff itemDiff : diffList) {
                LineItem line = (LineItem) itemDiff.getOldVlaue();
                LineItemDO lineDO = lineItemConverter.toData(line);
                lineItemDAO.delete(lineDO);
            }
        }*/
        if (lineItemDiffs instanceof DiffList) {
            DiffList diffList = (DiffList) lineItemDiffs;
            for (Diff itemDiff : diffList) {
                if (itemDiff.getType() == DiffType.Removed) {
                    LineItem line = (LineItem) itemDiff.getOldVlaue();
                    LineItemDO lineDO = lineItemConverter.toData(line);
                    //lineItemDAO.delete(lineDO);
                }
                if (itemDiff.getType() == DiffType.Added) {
                    LineItem line = (LineItem) itemDiff.getNewValue();
                    LineItemDO lineDO = lineItemConverter.toData(line);
                    //lineItemDAO.insert(lineDO);
                }
                if (itemDiff.getType() == DiffType.Modified) {
                    LineItem line = (LineItem) itemDiff.getNewValue();
                    LineItemDO lineDO = lineItemConverter.toData(line);
                    //lineItemDAO.update(lineDO);
                }
            }
        }
    }

    @Override
    protected void onDelete(Order aggregate) {
        OrderDO orderDO = converter.toData(aggregate);
        orderDAO.delete(orderDO);
    }

    @Override
    public Order findInStore(OrderId id, StoreId storeId) {
        OrderDO orderDO = orderDAO.findInStore(id.getId(), storeId.getId());
        return converter.fromData(orderDO);
    }
}
