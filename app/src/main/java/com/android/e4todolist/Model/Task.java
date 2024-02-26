package com.android.e4todolist.Model;

import java.io.Serializable;

public class Task implements Serializable {
    private Integer userId;
    private Integer id;
    private String title;
    private boolean completed;


    public Task(String title) {
        this.title = title;
        this.completed = false;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


}
