package org.mindmap;

public class Main {
    public static void main(String[] args) {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        Topic mainTopic5  = new Topic("Main Topic 5");
        Topic mainTopic6 = new Topic("Main Topic 6");
        centralTopic.appendChildren(mainTopic5, mainTopic6);
        //System.out.print(centralTopic.subTopic.size());
        for (var item:centralTopic.children) {
            System.out.println(item.title + item.positionToTheCentralTopic);
        }
    }
}