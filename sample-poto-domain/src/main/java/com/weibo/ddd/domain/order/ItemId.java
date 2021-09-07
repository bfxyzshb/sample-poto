package com.weibo.ddd.domain.order;

import com.weibo.poto.entity.Identifier;

/**
 * @ClassName ItemId
 * @Description TODO
 * @Author hebiao1
 * @Date 2021/9/6 8:04 下午
 * @Version 1.0
 */
public class ItemId extends Identifier<Long> {
    public Long id;

    public ItemId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
