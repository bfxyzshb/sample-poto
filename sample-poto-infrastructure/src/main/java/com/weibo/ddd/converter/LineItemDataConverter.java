package com.weibo.ddd.converter;

import com.weibo.ddd.data.LineItemDO;
import com.weibo.ddd.domain.order.LineItem;

public class LineItemDataConverter {

    public static final LineItemDataConverter INSTANCE = new LineItemDataConverter();

    public LineItemDO toData(LineItem lineItem) {
        return null;
    }

    public LineItem fromData(LineItemDO lineItemDO) {
        return null;
    }

}
