package hkucs.example.e_learningapp.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hkucs.example.e_learningapp.MainActivity;
import hkucs.example.e_learningapp.R;
import hkucs.example.e_learningapp.ui.tasks.dialog.ProcessTodoTaskDialog;
import hkucs.example.e_learningapp.ui.tasks.model.BaseTodo;
import hkucs.example.e_learningapp.ui.tasks.model.TodoCallback;
import hkucs.example.e_learningapp.ui.tasks.model.TodoTask;

public class TaskFragment extends Fragment {

    public static TaskViewModel mTaskViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_task, container, false);

        // recycler view
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        final TaskListAdapter adapter = new TaskListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Item Touch event listener
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Task mTask = adapter.getTaskAtPosition(position);
                        Toast.makeText(getContext(), "Deleting " +
                                mTask.getTask(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        mTaskViewModel.deleteTask(mTask);
                    }
                });

        helper.attachToRecyclerView(recyclerView);

        initFab(root);

        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {
                // Update the cached copy of the words in the adapter.
                adapter.setTasks(tasks);
            }
        });
        return root;
    }

    private void initFab(View rootView){
        // float button: add task
        FloatingActionButton fab = rootView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessTodoTaskDialog addListDialog = new ProcessTodoTaskDialog(getActivity());
                addListDialog.setDialogResult(new TodoCallback() {
                    @Override
                    public void finish(Task b) {
                        mTaskViewModel.insert(b);
                            // write new task to the database
                            //saveNewTasks();
                            // notify the change
                            //taskAdapter.notifyDataSetChanged();
                    }
                });
                addListDialog.show();

            }
        });
        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewTaskActivity.class);
                getActivity().startActivityForResult(intent, MainActivity.NEW_TASK_ACTIVITY_REQUEST_CODE);
            }
        });
         */
    }

}