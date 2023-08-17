package org.mindmap;

import java.util.UUID;

public class RelationshipArrow {
    public Topic getHead() {
        return head;
    }

    public void setHead(Topic head) {
        this.head = head;
    }

    public Topic getTail() {
        return tail;
    }

    public void setTail(Topic tail) {
        this.tail = tail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Topic head, tail;
    private String name;
    private String id;

    RelationshipArrow(Topic _head, Topic _tail) {
        this.head = _head;
        this.tail = _tail;
        this.name = null;
        this.id = UUID.randomUUID().toString();
    }

}
