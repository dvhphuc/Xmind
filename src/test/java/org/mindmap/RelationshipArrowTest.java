package org.mindmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipArrowTest {
    private RelationshipArrow createRelationship() {
        var centralTopic = new CentralTopic("Central Topic");
        var mainTopic1 = new Topic("Main Topic 1");
        var mainTopic2 = new Topic("Main Topic 2");

        centralTopic.appendChild(mainTopic1);
        centralTopic.appendChild(mainTopic2);

        var relationshipMaintopic1Maintopic2 = new RelationshipArrow(mainTopic1, mainTopic2);

        return relationshipMaintopic1Maintopic2;
    }

    @Test
    void testCreateRelationship() {
        RelationshipArrow relationshipArrowMaintopic1Maintopic2 = createRelationship();
        assertEquals("Main Topic 1", relationshipArrowMaintopic1Maintopic2.getHead().getTitle());
        assertEquals("Main Topic 2", relationshipArrowMaintopic1Maintopic2.getTail().getTitle());
    }


    @Test
    void testSetNameRelationship() {
        RelationshipArrow relationshipArrowMaintopic1Maintopic2 = createRelationship();

        relationshipArrowMaintopic1Maintopic2.setName("topic1topic2");

        assertEquals("topic1topic2", relationshipArrowMaintopic1Maintopic2.getName());
    }


    @Test
    void testChangeRelationship() {
        var centralTopic = new CentralTopic("Central Topic");
        var mainTopic1 = new Topic("Main Topic 1");
        var mainTopic2 = new Topic("Main Topic 2");
        var mainTopic3 = new Topic("Main Topic 3");

        centralTopic.appendChild(mainTopic1);
        centralTopic.appendChild(mainTopic2);
        centralTopic.appendChild(mainTopic3);

        var relationshipExample = new RelationshipArrow(mainTopic1, mainTopic2);
        relationshipExample.setHead(mainTopic3);

        assertEquals("Main Topic 3", relationshipExample.getHead().getTitle());

        relationshipExample.setTail(mainTopic2);

        assertEquals("Main Topic 2", relationshipExample.getTail().getTitle());
    }

    @Test
    void testRelationshipArrowToCentral() {
        var centralTopic = new CentralTopic("Central Topic");
        var mainTopic1 = new Topic("Main Topic 1");
        var mainTopic2 = new Topic("Main Topic 2");

        centralTopic.appendChildren(mainTopic1, mainTopic2);

        var r = new RelationshipArrow(mainTopic1, mainTopic2);

        centralTopic.addRelationshipArrow(r);

        assertEquals(1, centralTopic.getRelationshipArrows().size());

        centralTopic.removeRelationshipArrow(r);

        assertEquals(0, centralTopic.getRelationshipArrows().size());

    }

}
