package org.mindmap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class TopicTest {
    @Test
    void removeChidrensById() {
        var centralTopic = new CentralTopic("Central Topic");
        var mainTopic1 = new Topic("Main Topic 1");
        var mainTopic2 = new Topic("Main Topic 2");

        var subTopic1 = new Topic("Sub topic 1");

        mainTopic1.appendChild(subTopic1);

        List<String> needToRemoveTopic = new ArrayList();
        needToRemoveTopic.add(subTopic1.getId());
        needToRemoveTopic.add(mainTopic2.getId());
        centralTopic.removeAllChidrensById( needToRemoveTopic );

        assertEquals(0, mainTopic1.getChildren().size());
        assertEquals(1, centralTopic.getChildren().size());
    }
    @Test
    void removeChildById() {
        var centralTopic = new CentralTopic("Central Topic");
        var mainTopic1 = new Topic("Main Topic 1");

        centralTopic.appendChild(mainTopic1);

        var subTopic1 = new Topic("Sub Topic 1");

        mainTopic1.appendChild(subTopic1);

        System.out.println(subTopic1.getId());

        mainTopic1.removeChild(subTopic1.getId());

        assertEquals(0, mainTopic1.getChildren().size());
    }

//    @Test
//    void removeMultiTopic() {
//        var centralTopic = new CentralTopic("Central Topic");
//        var mainTopic1 = new Topic("Main Topic 1");
//        var mainTopic2 = new Topic("Main Topic 2");
//
//        centralTopic.appendChildren(mainTopic1,mainTopic2);
//
//        var subTopic1 = new Topic("Sub Topic 1");
//        var subTopic2 = new Topic("Sub Topic 2");
//
//        mainTopic1.appendChildren(subTopic1,subTopic2);
//
//        centralTopic.removeAllChildrenById(subTopic1,mainTopic2);
//
//        assertEquals(1, centralTopic.children.size());
//        assertEquals(1, mainTopic1.children.size());
//    }

}