package hkucs.example.e_learningapp.ui.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hkucs.example.e_learningapp.R;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    private final LayoutInflater mInflater;
    private List<Task> mTasks; // Cached copy of words

    TaskListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.task_entry, parent, false);
        return new TaskViewHolder(itemView);
    }

    void setTasks(List<Task> tasks){
        mTasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        if (mTasks != null) {
            Task current = mTasks.get(position);
            holder.taskNameView.setText(current.getTask());
            holder.taskDeadlineView.setText("Deadline: " + Helper.getDate(current.getDeadline()));
        } else {
            // Covers the case of data not being ready yet.
            holder.taskNameView.setText("No Word");
            holder.taskDeadlineView.setText("No Data");
        }
    }

    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }

    public Task getTaskAtPosition (int position) {
        return mTasks.get(position);
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskNameView;
        private final TextView taskDeadlineView;

        private TaskViewHolder(View itemView) {
            super(itemView);
            taskNameView = itemView.findViewById(R.id.tv_todo_list_title);
            taskDeadlineView = itemView.findViewById(R.id.tv_todo_list_next_deadline);
        }
    }
}
