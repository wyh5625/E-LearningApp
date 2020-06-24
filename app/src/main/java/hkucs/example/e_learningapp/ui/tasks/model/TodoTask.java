/*
 This file is part of Privacy Friendly To-Do List.

 Privacy Friendly To-Do List is free software:
 you can redistribute it and/or modify it under the terms of the
 GNU General Public License as published by the Free Software Foundation,
 either version 3 of the License, or any later version.

 Privacy Friendly To-Do List is distributed in the hope
 that it will be useful, but WITHOUT ANY WARRANTY; without even
 the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Privacy Friendly To-Do List. If not, see <http://www.gnu.org/licenses/>.
 */

package hkucs.example.e_learningapp.ui.tasks.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


/**
 *
 * Created by Sebastian Lutz on 12.03.2018.
 *
 * Class to set up To-Do Tasks and its parameters.
 */

public class TodoTask extends BaseTodo{

    private static final String TAG = TodoTask.class.getSimpleName();
    public static final String PARCELABLE_KEY = "key_for_parcels";


    public enum Priority {
        HIGH(0), MEDIUM(1), LOW(2); // Priority steps must be sorted in the same way like they will be displayed

        private final int value;

        Priority(final int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }

        public static Priority fromInt(int i) {
            for (Priority p : Priority.values()) {
                if (p.getValue() == i) {
                    return p;
                }
            }
            throw new IllegalArgumentException("No such priority defined.");
        }
    }

    public enum DeadlineColors {
        BLUE,
        ORANGE,
        RED
    }

    private boolean inTrash;
    private boolean done;
    private int progress;
    private Priority priority;
    private long reminderTime = -1; // absolute timestamp
    protected long deadline; // absolute timestamp

    private boolean reminderTimeChanged = false; // important for the reminder service
    private boolean reminderTimeWasInitialized = false;

    private String listName;


    public TodoTask() {
        super();
        done = false;
        inTrash = false;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public boolean hasDeadline() {
        return deadline > 0;
    }

    public TodoTask(Parcel parcel) {
        name = parcel.readString();
        description = parcel.readString();
        done = parcel.readByte() != 0;

        deadline = parcel.readLong();
        reminderTime = parcel.readLong();

    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }



    public boolean getDone() {
        return done;
    }


    public void setDone(boolean done) {
        this.done = done;
    }


    public int describeContents() {
        return 0;
    }



    public long getReminderTime() {
        return reminderTime;
    }


    public void setReminderTime(long reminderTime) {

        if(reminderTime > deadline && deadline > 0) {
            Log.i(TAG, "Reminder time must not be greater than the deadline.");
        }
        else {
            this.reminderTime = reminderTime;
        }

        // check if reminder time was already set and now changed -> important for reminder service

        if(reminderTimeWasInitialized)
            reminderTimeChanged = true;

        reminderTimeWasInitialized = true;
    }


}
