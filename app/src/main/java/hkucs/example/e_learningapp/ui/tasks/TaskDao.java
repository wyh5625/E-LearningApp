package hkucs.example.e_learningapp.ui.tasks;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TaskDao {



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("SELECT * from task_table ORDER BY task ASC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * from task_table LIMIT 1")
    Task[] getAnyTask();

    @Delete
    void deleteTask(Task task);
}