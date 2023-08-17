package org.mindmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {
    @Test
    void testInitCoordinate()
    {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        Topic mainTopic1 = new Topic("Main Topic 1");
        Topic mainTopic2 = new Topic("Main Topic 2");
        Topic mainTopic3 = new Topic("Main Topic 3");
        Topic mainTopic4 = new Topic("Main Topic 4");

        centralTopic.appendChildren(mainTopic1, mainTopic2, mainTopic3, mainTopic4);
        //////DOING
        assertEquals(100,mainTopic1.getX());
        assertEquals(200,mainTopic1.getY());

        assertEquals(100,mainTopic1.getX());
        assertEquals(400,mainTopic1.getY());

        assertEquals(400,mainTopic1.getX());
        assertEquals(600,mainTopic1.getY());

        assertEquals(400,mainTopic1.getX());
        assertEquals(800,mainTopic1.getY());
    }
}
