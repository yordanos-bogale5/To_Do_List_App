package com.android.e4todolist.Controller;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.e4todolist.TaskListener;
import com.android.e4todolist.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreateTaskFragment extends BottomSheetDialogFragment {

    private Button btn_add;
    private EditText new_title;
    private TaskListener taskListener;

    public static CreateTaskFragment newInstance() {
        return new CreateTaskFragment();
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_task, container, false);
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
        new_title = getView().findViewById(R.id.new_task_title);
        btn_add = getView().findViewById(R.id.btn_save);

        btn_add.setOnClickListener(v -> {
            String taskTitle = new_title.getText().toString();
            if (!taskTitle.equals("")) {
                if (taskListener != null) {
                    taskTitle = taskTitle.substring(0, 1).toUpperCase() + taskTitle.substring(1);
                    taskListener.sendTaskName(taskTitle, 0);
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