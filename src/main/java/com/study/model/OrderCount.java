package com.study.model;

import java.io.Serializable;

/**
 * @author YCKJ1409
 */
public class OrderCount implements Serializable {
    private Long id;

    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderCount{" +
                "id=" + id +
                ", count=" + count +
                '}';
    }
}
