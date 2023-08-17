package org.mindmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralTopicTest {
    @Test
    void testCreateCentralTopic() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        assertEquals("Central Topic", centralTopic.getTitle());
    }

    @Test
    void testCreateTopic() {
        Topic mainTopic = new Topic("Main Topic");

        assertEquals("Main Topic", mainTopic.getTitle());
    }

    @Test
    void testAppendTopicToCentralTopic() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");
        Topic mainTopic1 = new Topic("Main Topic 1");
        Topic mainTopic2 = new Topic("Main Topic 2");
        Topic mainTopic3 = new Topic("Main Topic 3");
        Topic mainTopic4 = new Topic("Main Topic 4");
        centralTopic.appendChildren(mainTopic1, mainTopic2, mainTopic3, mainTopic4);

       // System.out.print(mainTopic1.positionToTheCentralTopic + mainTopic2.positionToTheCentralTopic + mainTopic3.positionToTheCentralTopic + mainTopic4.positionToTheCentralTopic);
        assertEquals(4, centralTopic.getChildren().size());
    }

    @Test
    void testAppendMultiTopic() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");
        Topic mainTopic1 = new Topic("Main Topic 1");
        Topic mainTopic2 = new Topic("Main Topic 2");
        Topic mainTopic3 = new Topic("Main Topic 3");

        centralTopic.appendChildren(mainTopic1, mainTopic2, mainTopic3);

        assertEquals(3, centralTopic.getChildren().size());
    }

    @Test
    void testAppendSubTopic() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        Topic mainTopic1 = new Topic("Main Topic 1");
        Topic subTopic1 = new Topic("Sub Topic 1");
        Topic subTopic2 = new Topic("Sub Topic 2");
        Topic subTopic3 = new Topic("Sub Topic 3");

        centralTopic.appendChild(mainTopic1);
        mainTopic1.appendChild(subTopic1);
        mainTopic1.appendChild(subTopic2);
        mainTopic1.appendChild(subTopic3);

        assertEquals(3, mainTopic1.getChildren().size());
    }

    @Test
    void testAppendSubTopic1() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        Topic mainTopic1 = new Topic("Main Topic 1");
        Topic subTopic1 = new Topic("Sub Topic 1");
        Topic subTopic2 = new Topic("Sub Topic 2");
        Topic subTopic3 = new Topic("Sub Topic 3");

        Topic mainTopic2 = new Topic("Main Topic 2");
        Topic subTopic4 = new Topic("Sub Topic 4");
        Topic subTopic5 = new Topic("Sub Topic 5");
        Topic subTopic6 = new Topic("Sub Topic 6");

        centralTopic.appendChild(mainTopic1);
        centralTopic.appendChild(mainTopic2);
        mainTopic1.appendChild(subTopic1);
        mainTopic1.appendChild(subTopic2);
        mainTopic1.appendChild(subTopic3);

        mainTopic2.appendChild(mainTopic1);
        assertEquals(3, mainTopic1.getChildren().size());
    }

    @Test
    void testRemoveChild() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        Topic mainTopic1 = new Topic("Main Topic 1");

        centralTopic.appendChild(mainTopic1);

        assertEquals(1, centralTopic.getChildren().size());

        centralTopic.removeChildByObject(mainTopic1);

        assertEquals(0, centralTopic.getChildren().size());
    }

    @Test
    void testRemoveSubTopic() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        Topic mainTopic1 = new Topic("Main Topic 1");

        Topic subTopic1 = new Topic("Sub Topic 1");
        Topic subTopic2 = new Topic("Sub Topic 2");
        Topic subTopic3 = new Topic("Sub Topic 3");

        centralTopic.appendChild(mainTopic1);

        mainTopic1.appendChild(subTopic1);
        mainTopic1.appendChild(subTopic2);
        mainTopic1.appendChild(subTopic3);

        assertEquals(3, mainTopic1.getChildren().size()); // Check number of children Topic before remove

        mainTopic1.removeChildByObject(subTopic1);

        assertEquals(2, mainTopic1.getChildren().size()); // Check number of children Topic after remove
    }

    @Test
    void testMoveTopicToTopic() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        Topic mainTopic1 = new Topic("Main Topic 1");
        Topic mainTopic2 = new Topic("Main Topic 2");

        Topic subTopic1 = new Topic("Sub Topic 1");
        Topic subTopic2 = new Topic("Sub Topic 2");
        Topic subTopic3 = new Topic("Sub Topic 3");

        centralTopic.appendChild(mainTopic1);
        centralTopic.appendChild(mainTopic2);

        mainTopic1.appendChild(subTopic1);
        mainTopic1.appendChild(subTopic2);
        mainTopic1.appendChild(subTopic3);

        assertEquals(3, mainTopic1.getChildren().size());
        assertEquals(0, mainTopic2.getChildren().size());

        subTopic3.moveToTopic(mainTopic2);

        assertEquals(2, mainTopic1.getChildren().size());
        assertEquals(1, mainTopic2.getChildren().size());
    }

    @Test
    void testMoveSubtopicToCentralTopic() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        Topic mainTopic1 = new Topic("Main Topic 1");

        Topic subTopic1 = new Topic("Sub Topic 1");
        Topic subTopic2 = new Topic("Sub Topic 2");

        centralTopic.appendChild(mainTopic1);

        mainTopic1.appendChild(subTopic1);
        mainTopic1.appendChild(subTopic2);

        assertEquals(1, centralTopic.getChildren().size());

        subTopic2.moveToTopic(centralTopic);
        assertEquals(2, centralTopic.getChildren().size());
    }

    @Test
    void testMoveTopic2Toloating() {
        var centralTopic = new CentralTopic("Central Topic");

        var mainTopic1 = new Topic("Main Topic 1");

        centralTopic.appendChild(mainTopic1);

        assertEquals(1, centralTopic.getChildren().size());

        centralTopic.moveTopicToFloating(mainTopic1);

        assertEquals(0, centralTopic.getChildren().size());

        assertEquals(1, centralTopic.getFloatingTopics().size());
    }

    @Test
    void testMoveFloating2MainTopic() {
        var centralTopic = new CentralTopic("Central Topic");

        var mainTopic1 = new Topic("Main Topic 1");

        var floatingTopic = new Topic("Floating Topic");

        centralTopic.appendChild(mainTopic1);
        centralTopic.addFloatingTopic(floatingTopic);

        assertEquals(1, centralTopic.getFloatingTopics().size());
        assertEquals(1, centralTopic.getChildren().size());

        centralTopic.moveFloatingToMainTopic(floatingTopic);

        assertEquals(2, centralTopic.getChildren().size());
        assertEquals(0, centralTopic.getFloatingTopics().size());
    }

    @Test
    void testMoveFloating2SubTopic() {
        var centralTopic = new CentralTopic("Central Topic");

        var mainTopic1 = new Topic("Main Topic 1");

        var subTopic1 = new Topic("Sub Topic 1");

        var floatingTopic = new Topic("Floating Topic");

        centralTopic.appendChild(mainTopic1);
        centralTopic.addFloatingTopic(floatingTopic);

        assertEquals(1, centralTopic.getChildren().size());

        mainTopic1.appendChild(subTopic1);

        centralTopic.moveFloatingToSubTopic(floatingTopic,subTopic1);

        assertEquals(0, centralTopic.getFloatingTopics().size());

        assertEquals(1, subTopic1.getChildren().size());
    }

    @Test
    void testChangeOrderTopicToAboveTopic() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        var mainTopic = new Topic("Main Topic");

        centralTopic.appendChild(mainTopic);

        var subTopic1 = new Topic("Sub Topic 1");
        var subTopic2 = new Topic("Sub Topic 2");
        var subTopic3 = new Topic("Sub Topic 3");
        var subTopic4 = new Topic("Sub Topic 4");
        var subTopic5 = new Topic("Sub Topic 5");
        var subTopic6 = new Topic("Sub Topic 6");
        var subTopic7 = new Topic("Sub Topic 7");
        var subTopic8 = new Topic("Sub Topic 8");
        var subTopic9 = new Topic("Sub Topic 9");
        var subTopic10 = new Topic("Sub Topic 10");

        mainTopic.appendChildren(subTopic1, subTopic2, subTopic3, subTopic4, subTopic5, subTopic6, subTopic7, subTopic8, subTopic9, subTopic10);

        subTopic5.changeOrderToAbove(subTopic2);

        assertEquals(2, subTopic5.order);
        assertEquals(3, subTopic2.order);

    }

//    @Test
//    void testLeftRightTopic() {
//        CentralTopic centralTopic = new CentralTopic("Central Topic");
//
//
//
//        var mainTopic1 = new Topic("Main Topic 1");
//        var mainTopic2 = new Topic("Main Topic 2");
//        var mainTopic3 = new Topic("Main Topic 3");
//        var mainTopic4 = new Topic("Main Topic 4");
//        var mainTopic5 = new Topic("Main Topic 5");
//        var mainTopic6 = new Topic("Main Topic 6");
//
//        centralTopic.appendChilds(mainTopic1, mainTopic2, mainTopic3, mainTopic4, mainTopic5, mainTopic6);
//
//        assertEquals("left",mainTopic6.positionToTheCentralTopic);
//    }
}