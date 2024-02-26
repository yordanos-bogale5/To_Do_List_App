package com.android.e4todolist.Controller;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.e4todolist.Model.Task;
import com.android.e4todolist.TaskListener;
import com.android.e4todolist.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class EditTaskFragment extends BottomSheetDialogFragment {

    private int pos;
    private Button btn_save;
    private EditText edited_task;
    private TaskListener taskListener;
    private ArrayList<Task> list;


    public static EditTaskFragment newInstance(ArrayList<Task> list, int pos) {
        return new EditTaskFragment(list, pos);
    }

    public EditTaskFragment(ArrayList<Task> list, int pos) {
        this.list = list;
        this.pos = pos;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Shows the fragment in screen
     *
     * @param inflater           layout
     * @param container          view
     * @param savedInstanceState bundle
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    /**
     * Asks the user the task name, configures the add button and sends the string to ListActivity
     *
     * @param view               view
     * @param savedInstanceState bundle
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edited_task = getView().findViewById(R.id.edit_task_title);
        edited_task.setText(list.get(pos).getTitle());
        btn_save = getView().findViewById(R.id.btn_save_edit);

        btn_save.setOnClickListener(v -> {
            String taskTitle = edited_task.getText().toString();
            if (!taskTitle.equals("")) {
                if (taskListener != null) {
                    taskTitle = taskTitle.substring(0, 1).toUpperCase() + taskTitle.substring(1);
                    list.get(pos).setTitle(taskTitle);
                    taskListener.sendTaskName(taskTitle, pos);
                }
            }
            this.dismiss();
        });
    }

    /**
     * To associate the fragment to a listener {@link TaskListener}
     *
     * @param context context activity
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        taskListener = (TaskListener) context;
    }

}