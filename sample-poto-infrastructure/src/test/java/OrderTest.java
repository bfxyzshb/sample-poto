import com.google.common.collect.Lists;
import com.weibo.ddd.dao.LineItemDAO;
import com.weibo.ddd.dao.OrderDAO;
import com.weibo.ddd.dao.impl.LineItemDAOImpl;
import com.weibo.ddd.dao.impl.OrderDAOImpl;
import com.weibo.ddd.domain.order.*;
import com.weibo.ddd.repository.OrderRepository;
import com.weibo.ddd.repository.impl.OrderRepositoryDiffImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName OrderTest
 * @Description TODO
 * @Author hebiao1
 * @Date 2021/9/3 6:09 下午
 * @Version 1.0
 */
public class OrderTest {
    @Test
    public void multiInsert() {
        OrderDAO dao = new OrderDAOImpl();
        LineItemDAO lineItemDAO = new LineItemDAOImpl();
        OrderRepository repo = new OrderRepositoryDiffImpl(dao, lineItemDAO);

        Order order = new Order();
        order.setId(new OrderId(1L));
        order.setAddress(new Address("", "", "北京", ""));
        List<LineItem> lineItems = Lists.newArrayList();
        lineItems.add(new LineItem(new OrderId(1L), new BigDecimal(100)));
        order.setLineItems(lineItems);
        System.out.println(order);
        repo.save(order);
        order.getLineItems().get(0).setMoney(new BigDecimal(200));
        repo.save(order);
        System.out.println(order);
    }
}
