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

package hkucs.example.e_learningapp.ui.tasks.dialog;

import android.content.Context;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import hkucs.example.e_learningapp.R;
import hkucs.example.e_learningapp.ui.tasks.Helper;
import hkucs.example.e_learningapp.ui.tasks.Task;
import hkucs.example.e_learningapp.ui.tasks.model.TodoTask;


public class ProcessTodoTaskDialog extends FullScreenDialog {


    private TextView deadlineTextView;
    private TextView reminderTextView;

    private long deadline = -1;
    private long reminderTime = -1;

    private EditText taskName;
    private EditText taskDescription;

    public ProcessTodoTaskDialog(final Context context) {
        super(context, R.layout.add_task_dialog);
        initGui();
    }



    private void initGui() {

        // TODO: edit ok button event

        // initialize buttons
        Button okayButton = findViewById(R.id.bt_new_task_ok);
        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = taskName.getText().toString();
                String description = taskDescription.getText().toString();


                if(name.equals("")) {
                    Toast.makeText(getContext(), getContext().getString(R.string.todo_name_must_not_be_empty), Toast.LENGTH_SHORT).show();
                } /* else if (listName.equals(getContext().getString(R.string.click_to_choose))) {
                    Toast.makeText(getContext(), getContext().getString(R.string.to_choose_list), Toast.LENGTH_SHORT).show();
                } */
                else {
                    callback.finish(new Task(name, description, deadline, reminderTime));
                    ProcessTodoTaskDialog.this.dismiss();
                }
            }
        });

        Button cancelButton = (Button) findViewById(R.id.bt_new_task_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ProcessTodoTaskDialog.this.dismiss();
            }
        });

        // initialize textviews to get deadline and reminder time
        deadlineTextView = (TextView) findViewById(R.id.tv_todo_list_deadline);
        deadlineTextView.setTextColor(okayButton.getCurrentTextColor());
        deadlineTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeadlineDialog deadlineDialog = new DeadlineDialog(getContext(), deadline);
                deadlineDialog.setCallback(new DeadlineDialog.DeadlineCallback() {
                    @Override
                    public void setDeadline(long d) {
                        deadline = d;
                        deadlineTextView.setText(Helper.getDate(deadline));
                    }

                    @Override
                    public void removeDeadline() {
                        deadline = -1;
                        deadlineTextView.setText(getContext().getResources().getString(R.string.deadline));
                    }
                });
                deadlineDialog.show();
            }
        });

        reminderTextView = (TextView) findViewById(R.id.tv_todo_list_reminder);
        reminderTextView.setTextColor(okayButton.getCurrentTextColor());
        reminderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReminderDialog reminderDialog = new ReminderDialog(getContext(), reminderTime, deadline);
                reminderDialog.setCallback(new ReminderDialog.ReminderCallback() {
                    @Override
                    public void setReminder(long r) {

                        /*if(deadline == -1) {
                            Toast.makeText(getContext(), getContext().getString(R.string.set_deadline_before_reminder), Toast.LENGTH_SHORT).show();
                            return;
                        }*/

                        if(deadline != -1 && deadline < r) {
                            Toast.makeText(getContext(), getContext().getString(R.string.deadline_smaller_reminder), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        reminderTime = r;
                        reminderTextView.setText(Helper.getDateTime(reminderTime));
                    }

                    @Override
                    public void removeReminder() {
                        reminderTime = -1;
                        TextView reminderTextView = (TextView) findViewById(R.id.tv_todo_list_reminder);
                        reminderTextView.setText(getContext().getResources().getString(R.string.reminder));
                    }
                });
                reminderDialog.show();
            }
        });

        taskName = (EditText) findViewById(R.id.et_new_task_name);
        taskDescription = (EditText) findViewById(R.id.et_new_task_description);
    }



}
