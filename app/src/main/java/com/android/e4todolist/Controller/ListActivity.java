package com.android.e4todolist.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.e4todolist.AdapterTask;
import com.android.e4todolist.Model.Task;
import com.android.e4todolist.Model.TaskManager;
import com.android.e4todolist.TaskListener;
import com.android.e4todolist.R;
import com.android.e4todolist.api.APIClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListActivity extends AppCompatActivity implements TaskListener {
    private RecyclerView tasksRecyclerView;
    private AdapterTask adapterTask;

    /**
     * Sets contentView and calls the different methods used by the app
     *
     * @param savedInstanceState bundle
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        TaskManager.getInstance(this);
        setTasks();
        FloatingActionButton buttonAdd = findViewById(R.id.fab);
        buttonAdd.setOnClickListener(v -> openCreateTaskFragment());

    }

    /**
     * Opens the {@link CreateTaskFragment} to add new tasks.   *
     */
    private void openCreateTaskFragment() {
        CreateTaskFragment fragment = CreateTaskFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "ActionBottomDialog");
    }

    /**
     * Opens the {@link EditTaskFragment} to edit a task
     *
     * @param pos task position user want to edit
     */
    public void openEditTaskFragment(int pos) {
        EditTaskFragment fragment = EditTaskFragment.newInstance(TaskManager.getInstance(this).getTaskList(), pos);
        fragment.show(getSupportFragmentManager(), "ActionBottomDialog");
    }


    /**
     * Function that adds the task to the recyclerview list
     */
    public void setTasks() {
        TaskManager.getInstance(this).saveTasks();
        tasksRecyclerView = findViewById(R.id.list);
        adapterTask = new AdapterTask(this);
        tasksRecyclerView.setAdapter(adapterTask);
    }

    /**
     * method from {@link TaskListener} interface
     * it adds to task manager new task or saves edited tasks
     *
     * @param taskName user input of the class title
     * @param pos      position in case of edit title task
     */
    @Override
    public void sendTaskName(String taskName, int pos) {
        if (pos == 0) { //NEW TASK:
            Task t = new Task(taskName);
            TaskManager.getInstance(this).addTask(t);
            addTaskApi(t);
        } else { //EDITED TASK:
            editTaskApi(TaskManager.getInstance(this).getTaskList().get(pos));
        }

        TaskManager.getInstance(this).saveTasks();
        tasksRecyclerView.setAdapter(adapterTask);
    }

    /**
     * shows a confirmed toast when a task is edited
     * @param task task
     */
    private void editTaskApi(Task task) {
        APIClient.getInstance().editTask(task, new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                Log.d("EDIT", "You connected to the api and edited the task successfully");
                Toast.makeText(getApplicationContext(),"You connected to the api and edited the task successfully",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.d("EDIT", "You had an error trying to connect to the api while editing the task");
                Toast.makeText(getApplicationContext(),"You had an error trying to connect to the api while editing the task",Toast.LENGTH_SHORT).show();

            }
        });

    }
    /**
     * shows a confirmed toast when a task is added
     * @param task task
     */
    public void addTaskApi(Task task) {
        APIClient.getInstance().addTask(task, new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                Log.d("ADD", "You connected to the api and added a task successfully");
                Toast.makeText(getApplicationContext(),"You connected to the api and added a task successfully",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.d("ADD", "You had an error trying to connect to the api while adding a task");
                Toast.makeText(getApplicationContext(),"You had an error trying to connect to the api while adding a task\"",Toast.LENGTH_SHORT).show();

            }
        });

    }
}


