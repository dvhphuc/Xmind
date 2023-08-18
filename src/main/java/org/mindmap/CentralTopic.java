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

    public List<RelationshipArrow> getRelationshipArrows() {
        return relationshipArrows;
    }

    public void setRelationshipArrows(List<RelationshipArrow> relationshipArrows) {
        this.relationshipArrows = relationshipArrows;
    }

    List<RelationshipArrow> relationshipArrows = new ArrayList();

    public List<Topic> getFloatingTopics() {
        return floatingTopics;
    }

    @Override
    void appendChild(Topic topic) {

        topic.parentTopic = this;
        topic.order = topic.parentTopic.getChildren().size();
        arrange(topic);
        this.children.add(topic);
    }

    //void arrange //
    void arrange(Topic topic) {
        if (this.children.size() > 4) {
            topic.positionToTheCentralTopic = "left";
            this.children.get(this.children.size() / 2).setPositionToTheCentralTopic("right");
        }
        else if (this.children.size() < 3)
            topic.setPositionToTheCentralTopic("right");
        else
            topic.setPositionToTheCentralTopic("left");
    }

    @Override
    void updatePositionOfChildren() {
        double heightLeftSide = 0;
        double heightRightSide = 0;
        for (var item : this.children) {
            if (item.getPositionToTheCentralTopic() == "left")
                heightLeftSide += item.caculateHeightOfLeafChild() + item.getHeight();
            else
                heightRightSide += item.caculateHeightOfLeafChild() + item.getHeight();
        }
        if (heightRightSide - heightLeftSide > 200) {
            for (int i = this.children.size() - 1; i >= 0; i--) {
                var child = this.children.get(i);
                if (child.getPositionToTheCentralTopic() == "right") {
                    child.setPositionToTheCentralTopic("left");
                    child.updatePositionOfChildren();
                    break;
                }
            }
        }
        if (heightLeftSide - heightRightSide > 200) {
            for (var item : this.children) {
                if (item.getPositionToTheCentralTopic() == "left") {
                    item.setPositionToTheCentralTopic("right");
                    item.updatePositionOfChildren();
                    break;
                }
            }
        }
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

    void addRelationshipArrow(RelationshipArrow r) {
        this.relationshipArrows.add(r);
    }

    void removeRelationshipArrow(RelationshipArrow r) {
        this.relationshipArrows.remove(r);
    }


}