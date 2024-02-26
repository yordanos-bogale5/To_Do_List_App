package com.android.e4todolist.api;


import com.android.e4todolist.Model.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceholderService {
    @GET("todos/{todoId}")
    Call<Task> getTask(@Path("todoId") Integer id);

    @GET("todos")
    Call<List<Task>> getList();

    @PUT("todos")
    Call<Task> editTask(@Body Task task);

    @POST("todos")
    Call<Task> addTask(@Body Task task);

}
