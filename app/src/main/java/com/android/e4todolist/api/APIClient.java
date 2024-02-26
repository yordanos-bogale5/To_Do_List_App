package com.android.e4todolist.api;

import com.android.e4todolist.Model.Task;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static APIClient apiClient;
    private Retrofit retrofit;
    private JsonPlaceholderService service;

    public APIClient() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create())
                .build();
        this.service = retrofit.create(JsonPlaceholderService.class);

    }

    public static APIClient getInstance() {
        if (apiClient == null) {
            apiClient = new APIClient();
        }
        return apiClient;

    }
    /*
    public void getTodo(Integer todo_id, Callback<Task> callback) {
        this.service.getTask(todo_id).enqueue(callback);
    }*/

    public void getList(Callback<List<Task>> callback) {
        this.service.getList().enqueue(callback);
    }

    public void editTask(Task task, Callback<Task> callback) {
        this.service.editTask(task).enqueue(callback);
    }

    public void addTask(Task task, Callback<Task> callback) {
        this.service.addTask(task).enqueue(callback);
    }
}
