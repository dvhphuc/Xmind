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

    public void updatePositionChild() {
        this.children.get(this.children.size()/2).setY(  this.y - this.width/2 + GlobalProterties.width/2          );
        for (int i=this.children.size()/2-1 ; i >= 0; ++i) {
            int indexNext = i + 1;
            this.children.get(i).setY(this.children.get(indexNext).getY() + GlobalProterties.space + GlobalProterties.width);
        }

        for (int i=this.children.size()/2+1; i<this.children.size(); ++i) {
            int indexPre = i-1;
            this.children.get(i).setY(this.children.get(indexPre).getY() - GlobalProterties.space - GlobalProterties.width);
        }

        for (var item:children) {
            item.setX( this.getX() - GlobalProterties.space - GlobalProterties.width);
        }

    }

    private int width;
    private int height;

    protected int order;

    public String getPositionToTheCentralTopic() {
        return positionToTheCentralTopic;
    }

    protected String positionToTheCentralTopic = "";

    Topic(String title) {
        this.title = title;
        this.parentTopic = null;
        this.id = UUID.randomUUID().toString();
    }

    void appendChild(Topic topic) {
        this.children.add(topic);
        topic.parentTopic = this;
        topic.order = topic.parentTopic.getChildren().size();
        topic.positionToTheCentralTopic = this.positionToTheCentralTopic;
    }

    void appendChildById(Topic topic) {
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
        for (var item : this.children) {
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
        int minIndex = Math.min(this.order, topic.order);
        int maxIndex = Math.max(this.order, topic.order);
        List<Integer> orders = new ArrayList<Integer>();
        for (var item : this.parentTopic.getChildren()) {
            orders.add(item.order);
        }

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

    public Topic findChildObjectById(String Id) {
        for (var item : this.children) {
            if (item.getId() == Id) return item;
            item.findChildObjectById(Id);
        }
        return null;
    }

    void removeAllChidrensById(List<String> listIds) {
        for (var id : listIds) {
            var objectFindById = findChildObjectById(id);
            GlobalProterties.topicsNeedToRemove.add(objectFindById);
        }
        this.traversal();
        GlobalProterties.topicsNeedToRemove = null;
    }

//    void removeAllChildrenByObject(List<Topic> topics) { //Remove all children and multi-level subchildren of a Topic
//        for (var topic : topics) {
//            GlobalProterties.topicsNeedToRemove.add(topic);
//        }
//        //GlobalProterties.topicsNeedToRemove = Arrays.stream(topics).toList(); ////If using is converting, the topicsNeedToRmove will be a fixed-array, so cannot remove any elemtn from it
//        this.traversal();
//        GlobalProterties.topicsNeedToRemove = null;
//    }

    void traversal() {
        for (var item:this.children) {
            if (GlobalProterties.topicsNeedToRemove.contains(item)) {
                this.removeChildByObject(item);
                GlobalProterties.topicsNeedToRemove.remove(item);
            }
            item.traversal();
        }
        return;
    }

}