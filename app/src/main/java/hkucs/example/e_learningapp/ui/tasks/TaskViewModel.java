package hkucs.example.e_learningapp.ui.tasks;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllTasks;

    public TaskViewModel (Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks() { return mAllTasks; }

    public void insert(Task task) { mRepository.insert(task); }

    public void deleteTask(Task task) {mRepository.deleteTask(task);}
}
