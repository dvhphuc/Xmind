package org.mindmap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

public class CentralTopic extends Topic {
    CentralTopic(String title) {
        super(title);
        this.setX(0);
        this.setY(0);
    }

    List<Topic> floatingTopics = new ArrayList();

    public List<Topic> getFloatingTopics() {
        return floatingTopics;
    }

    @Override
    void appendChild(Topic topic) {

        topic.parentTopic = this;
        topic.order = topic.parentTopic.getChildren().size();
//        topic.positionToTheCentralTopic = "left";
//        this.children.get(this.children.size()/2).setPositionToTheCentralTopic("right");
        this.children.add(topic);
    }

    void addFloatingTopic(Topic floatingTopic) {
        this.floatingTopics.add(floatingTopic);
        floatingTopic.parentTopic = this;
    }

    void removeFloatingTopic(Topic floatingTopic) {
        List<Topic> filteredTopics = this.floatingTopics.stream()
                .filter(item -> item != floatingTopic)
                .collect(Collectors.toList());
        this.floatingTopics = filteredTopics;
    }

    void moveTopicToFloating(Topic topic) {
        topic.parentTopic.removeChildByObject(topic);
        addFloatingTopic(topic);
    }


    void moveFloatingToMainTopic(Topic floatingTopic) {
        removeFloatingTopic(floatingTopic);
        appendChild(floatingTopic);
        floatingTopic.parentTopic = this;
    }

    void moveFloatingToSubTopic(Topic floatingTopic, Topic subTopic) {
        removeFloatingTopic(floatingTopic);
        subTopic.appendChild(floatingTopic);
        floatingTopic.parentTopic = subTopic;
    }
}







//    void TraversalFloating(List<Topic> floatingTopicstopics) {
//
//        for (var item:this.getChildren()) {
//            if (floatingTopicstopics.contains(item)) {
//                this.removeChild(item);
//                //topics.remove(item);
//            }
//            item.Traversal(floatingTopicstopics);
//        }
//    }
//
//    void removeFloatingTopics(Topic... floatingTopics) {
//        var needToRemoveTopics = Arrays.stream(floatingTopics).toList();
//        for (var item : floatingTopics) {
//            item.Traversal(needToRemoveTopics);
//        }
//        this.TraversalFloating(needToRemoveTopics);
//        for (var item: floatingTopics) {
//            if (needToRemoveTopics.contains(item)) {
//                item.parentTopic.removeChild(item);
//            }
//        }
//    }



//
//    List<RelationshipArrow> relationshipArrows = new ArrayList();
//    void addRelationship(Topic _head, Topic _tail) {
//        relationshipArrows.add(new RelationshipArrow(_head, _tail));
//    }
//    void changeHeadRelationshipArrow(RelationshipArrow r,Topic _head) {
//        r.setHead(_head);
//    }
//    void changeTailRelationshipArrow(RelationshipArrow r,Topic _tail) {
//        r.setTail(_tail);
//    }

//        Topic mainTopic1 = new Topic("Main Topic 1");
//        Topic mainTopic2 = new Topic("Main Topic 2");
//        Topic mainTopic3 = new Topic("Main Topic 3");
//        Topic mainTopic4 = new Topic("Main Topic 4");
//        mainTopic1.setPositionToTheCentralTopic("right");
//        mainTopic2.setPositionToTheCentralTopic("right");
//        mainTopic3.setPositionToTheCentralTopic("left");
//        mainTopic4.setPositionToTheCentralTopic("left");
//        this.getChildren().add(mainTopic1);
//        this.getChildren().add(mainTopic2);
//        this.getChildren().add(mainTopic3);
//        this.getChildren().add(mainTopic4);