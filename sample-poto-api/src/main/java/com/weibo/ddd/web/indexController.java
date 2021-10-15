package com.weibo.ddd.web;

import com.google.common.eventbus.EventBus;
import com.weibo.ddd.commands.OrderCreateCommand;
import com.weibo.poto.bus.command.CommandBus;
import com.weibo.poto.bus.command.DefaultCommandBus;
import com.weibo.poto.bus.command.Response;
import com.weibo.poto.bus.command.annotation.CommandHandler;
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
    CommandBus commandBus;

    @RequestMapping("/order/save")
    @ResponseBody
    public void saveOrder() {
        /**
         * （1）通用的大Map，也可以保证接口兼容性，但需要在代码里解决Map解析和异常处理的问题。
         * （2）固定参数，但如果未来的需求变更，可能需要接口变更，造成可能的接口不兼容。
         * 我们通过对所有的入参做强类型显性化包装，可以确保
         * 1.接口的兼容
         * 2.参数的可拓展性。同时，通过对多个参数的显性封装。
         * 3.我们能更明确的理解该方法的“目的性”，不需要再仅仅通过方法名去理解方法的目的。
         */
        OrderCreateCommand orderCreateCommand = new OrderCreateCommand("测试", "北京", new BigDecimal(100));
        Protocol protocol = new DefProtocl();
        protocol.getExtensionName();
        orderCreateCommand.setProtocol(protocol);
        Response response = commandBus.dispatch(orderCreateCommand);
        System.out.println(response);
    }

    class DefProtocl implements Protocol {

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
