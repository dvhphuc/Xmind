package org.mindmap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class TopicTest {

    @Test
    void removeChildById() {
        var centralTopic = new CentralTopic("Central Topic");
        var mainTopic1 = new Topic("Main Topic 1");

        centralTopic.appendChild(mainTopic1);

        var subTopic1 = new Topic("Sub Topic 1");

        mainTopic1.appendChild(subTopic1);

        mainTopic1.removeChild(subTopic1.getId());

        assertEquals(0, mainTopic1.getChildren().size());
    }

    @Test
    void removeChidrensById() {
        var centralTopic = new CentralTopic("Central Topic");
        var mainTopic1 = new Topic("Main Topic 1");
        var mainTopic2 = new Topic("Main Topic 2");

        var subTopic1 = new Topic("Sub topic 1");

        mainTopic1.appendChild(subTopic1);

        centralTopic.appendChild(mainTopic1);
        centralTopic.appendChild(mainTopic2);

        centralTopic.removeChildren(subTopic1.getId(), mainTopic2.getId());

        assertEquals(0, mainTopic1.getChildren().size());
        assertEquals(1, centralTopic.getChildren().size());
    }
}

