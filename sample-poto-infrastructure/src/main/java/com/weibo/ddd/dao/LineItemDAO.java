package com.weibo.ddd.dao;

import com.weibo.ddd.data.LineItemDO;

public interface LineItemDAO {

    int update(LineItemDO lineItemDO);

    int insert(LineItemDO lineItemDO);

    int delete(LineItemDO lineDO);

}
