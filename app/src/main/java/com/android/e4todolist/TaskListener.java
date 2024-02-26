package com.android.e4todolist;

/**
 * INTERFACE Task Listener
 */
public interface TaskListener {
    /**
     * To send informetion from fragment to {@link com.android.e4todolist.Controller.ListActivity}
     *
     * @param taskName user input of the class title
     * @param pos      position in case of edit title task
     */
    void sendTaskName(String taskName, int pos);
}
