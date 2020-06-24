package hkucs.example.e_learningapp.ui.tasks;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "task")
    private String mTask;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "deadline")
    private Long deadline;

    @ColumnInfo(name = "remindertime")
    private Long reminderTime;

    public Task(@NonNull String task, String description, Long deadline, Long reminderTime ) {
        this.mTask = task;
        this.description = description;
        this.deadline = deadline;
        this.reminderTime = reminderTime;
    }

    public String getTask(){return this.mTask;}

    public String getDescription() {
        return description;
    }

    public Long getDeadline() {
        return deadline;
    }

    public Long getReminderTime() {
        return reminderTime;
    }
}
