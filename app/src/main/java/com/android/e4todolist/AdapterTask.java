package com.android.e4todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.e4todolist.Controller.ListActivity;
import com.android.e4todolist.Model.Task;
import com.android.e4todolist.Model.TaskManager;

import java.util.ArrayList;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.ViewHolder> {
    /**
     * VIEW HOLDER CLASS
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task_checkbox;
        ImageButton delete_btn;
        ImageButton edit_btn;

        /**
         * Initializes checkbox and buttons finding by id
         *
         * @param view View
         */
        ViewHolder(View view) {
            super(view);
            task_checkbox = view.findViewById(R.id.itemCheck);
            delete_btn = view.findViewById(R.id.btn_delete);
            edit_btn = view.findViewById(R.id.btn_save_edit);
        }

        /**
         * Sets the Task name in the checkbox
         * Knows if a task checkbox has been checked and updates the Task boolean
         * onClickListener of delete and edit buttons
         * sets the buttons invisible  for default tasks
         *
         * @param pos position of the recycle view
         */
        public void bind(int pos) {
            ArrayList<Task> list = TaskManager.getInstance().getTaskList();
            task_checkbox.setText(list.get(pos).getTitle());
            task_checkbox.setChecked(list.get(pos).isCompleted());
            delete_btn.setOnClickListener(v -> deleteItem(pos));
            edit_btn.setOnClickListener(v -> editItem(pos));

            task_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        list.get(pos).setCompleted(isChecked);
                        TaskManager.getInstance().saveTasks();
                    }
            );

        }

        /**
         * Calls {@link TaskManager#removeTask(int)} and {@link TaskManager#saveTasks()} (int)}
         * Notifies the item removed and the range change
         *
         * @param pos task wanna delete position
         */
        public void deleteItem(int pos) {
            TaskManager.getInstance().removeTask(pos);
            TaskManager.getInstance().saveTasks();

            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, TaskManager.getInstance().getTaskList().size());
        }

        /**
         * @param pos
         */
        public void editItem(int pos) {
            activity.openEditTaskFragment(pos);
            notifyItemChanged(pos);

        }

    }


    /**
     * ADAPTER TASK CLASS
     */
    private final ListActivity activity;

    /**
     * ADAPTER CONSTRUCTOR
     *
     * @param activity main activity {@link ListActivity}
     */
    public AdapterTask(ListActivity activity) {
        this.activity = activity;
    }


    /**
     * Methods that calls an item of a task ui (fragment_task.xml)
     *
     * @param parent   Viewgroup
     * @param viewType int
     * @return itemview
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Function that manages the position of each task
     * calls {@link ViewHolder #bind(int)}
     *
     * @param holder   view
     * @param position integer of tasks
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }


    /**
     * Returns the list size
     *
     * @return size of items in list
     */
    @Override
    public int getItemCount() {
        return TaskManager.getInstance().getTaskList().size();
    }


}
