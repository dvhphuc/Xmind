package org.mindmap;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Topic {
    public List<Topic> getChildren() {
        return children;
    }

    public List<Topic> getRelationshipedTopic() {
        return relationshipedTopic;
    }

    public Topic getParentTopic() {
        return parentTopic;
    }

    public void setParentTopic(Topic parentTopic) {
        this.parentTopic = parentTopic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected List<Topic> children = new ArrayList();

    //protected List<String> childrenId = new ArrayList();
    protected List<Topic> relationshipedTopic = new ArrayList();
    protected Topic parentTopic;
    protected String title;

    public String getId() {
        return id;
    }

    private String id;

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    private int width;
    private int height;

    protected int order;

    public void setPositionToTheCentralTopic(String postion) {
        this.positionToTheCentralTopic = postion;
    }


    public String getPositionToTheCentralTopic() {
        return positionToTheCentralTopic;
    }

    protected String positionToTheCentralTopic = "";

    Topic(String title) {
        this.title = title;
        this.parentTopic = null;
        this.height = 200;
        this.id = UUID.randomUUID().toString();
    }

    void appendChild(Topic topic) {
        this.children.add(topic);
        topic.parentTopic = this;
        topic.order = topic.parentTopic.getChildren().size();
        topic.positionToTheCentralTopic = this.positionToTheCentralTopic;
    }

    void appendChildren(Topic... topics) {
        for (var item : topics) {
            appendChild(item);
        }
    }

    void removeChildByObject(Topic topic) {
        List<Topic> filteredTopics = children.stream()
                .filter(item -> item != topic)
                .collect(Collectors.toList());
        this.children = filteredTopics;
        topic.setParentTopic(null);
        for (var item : this.children) { // Reorder all of children after remove a child
            if (item.order > topic.order)
                item.order -= 1;
        }
    }

    void removeChild(String childId) {
        for (var item : this.children) {
            if (item.getId() == childId) {
                this.removeChildByObject(item);
                break;
            }
        }
    }

    void moveToTopic(Topic destinationTopic) {
        this.parentTopic.getChildren().remove(this);
        destinationTopic.getChildren().add(this);
        this.parentTopic = destinationTopic;
    }


    void changeOrderToAbove(Topic topic) {
        List<Integer> orders = new ArrayList<Integer>();
        for (var item : this.parentTopic.getChildren()) {
            orders.add(item.order);
        }

        int minIndex = Math.min(this.order, topic.order);
        int maxIndex = Math.max(this.order, topic.order);

        if (topic.order < this.order) {
            this.order = topic.order;
            for (int i = minIndex - 1; i < maxIndex - 1; ++i) {
                this.parentTopic.getChildren().get(i).order += 1;
            }
        } else {
            this.order = topic.order - 1;
            for (int i = minIndex; i < maxIndex - 1; ++i) {
                this.parentTopic.getChildren().get(i).order -= 1;
            }
        }
    }


    public void removeChildrenById(List<String> idSet) {
        for (var child : this.children) {
            var childId = child.getId();
            if ( idSet.contains(childId) ) {
                child.getParentTopic().removeChild( childId );
                idSet.remove(childId);
            }
            child.removeChildrenById(idSet);
        }
    }

    void updatePositionOfChildren() {
        for (var item : this.children) {
            item.setPositionToTheCentralTopic(this.positionToTheCentralTopic);
        }
        return;
    }

    double caculateHeightOfLeafChild() {
        double height = 0;
        if (this.children.size() == 0) height = this.getHeight();
        for (var item : this.children)
            if (item.getChildren().size() == 0)
                height += 70;
            else height += item.caculateHeightOfLeafChild();
        return height;
    }

}