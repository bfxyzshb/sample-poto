package com.weibo.ddd.domain.order;

import com.weibo.poto.entity.Identifier;

public class StoreId extends Identifier<Long> {
    public Long id;

    @Override
    public Long getId() {
        return id;
    }

}
