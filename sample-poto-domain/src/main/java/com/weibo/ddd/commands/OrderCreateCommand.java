package com.weibo.ddd.commands;

import com.weibo.poto.bus.command.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName OrderCreateCommand
 * @Description TODO
 * @Author hebiao1
 * @Date 2021/9/1 5:35 下午
 * @Version 1.0
 */
@Data
public class OrderCreateCommand extends Command {
    @NotNull
    private String title;
    private String city;
    private BigDecimal money;

    public OrderCreateCommand(String title, String city, BigDecimal money) {
        this.title = title;
        this.city = city;
        this.money = money;
    }
}
