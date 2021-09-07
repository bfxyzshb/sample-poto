package com.weibo.ddd.web;

import com.weibo.ddd.commands.OrderCreateCommand;
import com.weibo.poto.bus.command.DefaultCommandBus;
import com.weibo.poto.bus.command.Response;
import com.weibo.poto.spi.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName indexController
 * @Author hebiao1
 * @Date 2021/9/1 4:10 下午
 * @Version 1.0
 */
@RestController
public class indexController {

    @Autowired
    DefaultCommandBus commandBus;

    @RequestMapping("/order/save")
    @ResponseBody
    public void saveOrder() {
        OrderCreateCommand orderCreateCommand = new OrderCreateCommand("测试", "北京", new BigDecimal(100));
        Protocol protocol = new DefProtocl();
        protocol.getExtensionName();
        orderCreateCommand.setProtocol(protocol);
        Response response = commandBus.dispatch(orderCreateCommand);
        System.out.println(response);
    }

    class DefProtocl implements Protocol{

        @Override
        public Set<String> getParameters() {
            return new HashSet<>();
        }

        @Override
        public String getExtensionName() {
            return "taskOne";
        }
    }
}
