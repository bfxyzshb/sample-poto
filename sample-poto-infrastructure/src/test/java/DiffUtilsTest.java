import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.weibo.ddd.domain.order.*;
import com.weibo.poto.repository.objectDiff.diff.Diff;
import com.weibo.poto.repository.objectDiff.diff.DiffList;
import com.weibo.poto.repository.objectDiff.diff.DiffUtils;
import com.weibo.poto.repository.objectDiff.diff.EntityDiff;
import com.weibo.poto.repository.util.SnapshotUtils;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiffUtilsTest {
    @Test
    public void diff() throws IllegalAccessException, IOException, ClassNotFoundException {
        // 实时对象
        Order order = new Order();
        order.setId(new OrderId(1L));
        order.setTitle("old title");

        // 快照对象
        Order snapshotObj = SnapshotUtils.snapshot(order);
        snapshotObj.setId(new OrderId(2L));
        snapshotObj.setTitle("old title");

        EntityDiff diff = DiffUtils.diff(order, snapshotObj);


        assert diff.isSelfModified() == true;
        //assert diff.getDiffs().size() == 1;

        System.out.println("========非集合类型 Diff 校验通过========");
    }

    @Test
    public void diff1() throws IOException, ClassNotFoundException {
        // 实时对象
        Order order = new Order();
        order.setId(new OrderId(1L));
        order.setTitle("title");
        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(new LineItem(new OrderId(1L), new BigDecimal(111)));
        lineItems.add(new LineItem(new OrderId(2L), new BigDecimal(222)));
        order.setLineItems(lineItems);
        // 快照对象
        Order snapshotObj = new Order();//SnapshotUtils.snapshot(order);
        snapshotObj.setId(new OrderId(1L));
        snapshotObj.setTitle("title");
        List<LineItem> lineItems1 = new ArrayList<>();
        lineItems1.add(new LineItem(new OrderId(1L), new BigDecimal(111)));
        lineItems1.add(new LineItem(new OrderId(2L), new BigDecimal(2223)));
        snapshotObj.setLineItems(lineItems1);
        EntityDiff diff = DiffUtils.diff(snapshotObj, order);


        Diff diffs = diff.getDiff("lineItems");

        if (diffs != null && diffs instanceof DiffList) {
            System.out.println(((DiffList) diffs).size());
            ((DiffList) diffs).forEach(s -> {
                System.out.println(s.getType() + "  " + s.getNewValue() + "  " + s.getOldVlaue());
                //System.out.println(s.getOldVlaue());
            });
        }
    }

    @Test
    public void test() {
        Order base = new Order(new OrderId(1l), "name", null, Lists.newArrayList(new LineItem(new OrderId(1l), new BigDecimal(111))));
        Order working = new Order(new OrderId(1l), "name", null, Lists.newArrayList(new LineItem(new OrderId(1l), new BigDecimal(111))));
        final EntityDiff entityDiff = DiffUtils.diff(base, working);
        final Diff diff = entityDiff.getDiff("lineItems");
        if (diff instanceof DiffList) {
            DiffList dl = (DiffList) diff;
            for (Diff d : dl) {
                System.err.println(d.getType());
                System.err.println(d.getNewValue());
                System.err.println(d.getOldVlaue());
            }
        }
    }
}