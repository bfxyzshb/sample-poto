import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.weibo.ddd.domain.order.*;
import com.weibo.poto.repository.objectDiff.diff.Diff;
import com.weibo.poto.repository.objectDiff.diff.DiffList;
import com.weibo.poto.repository.objectDiff.diff.DiffUtils;
import com.weibo.poto.repository.objectDiff.diff.EntityDiff;

import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.PropertyChange;
import org.javers.core.diff.changetype.container.ListChange;
import org.javers.core.diff.changetype.container.ValueRemoved;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.javers.core.diff.ListCompareAlgorithm.LEVENSHTEIN_DISTANCE;


public class ObjectDiff {

    @Test
    public void test1() {
/*
        Model working = new Model(new ModelId(1L), "name", Lists.newArrayList(new LineItem1(new ItemId(1L), "name")));
        Model base = new Model(new ModelId(1L), "name", Lists.newArrayList(new LineItem1(new ItemId(1L), "name1")));

        Javers javers = JaversBuilder.javers()
                .withListCompareAlgorithm(LEVENSHTEIN_DISTANCE)
                .build();
        org.javers.core.diff.Diff diff = javers.compare(base, working);

        final List<PropertyChange> propertyChanges = diff.getPropertyChanges("items");
        final PropertyChange propertyChange = propertyChanges.get(0);
        System.err.println(propertyChange.getChangeType());
        System.err.println(propertyChange.isPropertyRemoved());
        System.err.println(propertyChange.isPropertyAdded());
        System.err.println(propertyChange.isPropertyValueChanged());
        final Optional<Object> affectedObject = propertyChange.getAffectedObject();
        System.err.println(diff);*/

    }

}
