package hkucs.example.e_learningapp.ui.tasks.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import hkucs.example.e_learningapp.R;


public class ReminderDialog extends FullScreenDialog {

    private ReminderCallback callback;

    public ReminderDialog(Context context, long reminderTime, long deadline) {
        super(context, R.layout.reminder_dialog);

        Calendar calendar = GregorianCalendar.getInstance();
        if(reminderTime != -1) calendar.setTimeInMillis(TimeUnit.SECONDS.toMillis(reminderTime));
        else if(deadline != -1) calendar.setTimeInMillis(TimeUnit.SECONDS.toMillis(deadline)); //TODO subtract predefined reminder interval
        else calendar.setTime(Calendar.getInstance().getTime());

        DatePicker datePicker = (DatePicker) findViewById(R.id.dp_reminder);
        //datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                LinearLayout layoutDate = (LinearLayout)findViewById(R.id.ll_reminder_date);
                layoutDate.setVisibility(View.GONE);
                LinearLayout layoutTime = (LinearLayout)findViewById(R.id.ll_reminder_time);
                layoutTime.setVisibility(View.VISIBLE);
            }
        });

        TimePicker timePicker = (TimePicker) findViewById(R.id.tp_reminder);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE)+1);

        Button buttonDate = (Button) findViewById(R.id.bt_reminder_date);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutDate = (LinearLayout)findViewById(R.id.ll_reminder_date);
                layoutDate.setVisibility(View.VISIBLE);
                LinearLayout layoutTime = (LinearLayout)findViewById(R.id.ll_reminder_time);
                layoutTime.setVisibility(View.GONE);
            }
        });
        Button buttonTime = (Button) findViewById(R.id.bt_reminder_time);
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutDate = (LinearLayout)findViewById(R.id.ll_reminder_date);
                layoutDate.setVisibility(View.GONE);
                LinearLayout layoutTime = (LinearLayout)findViewById(R.id.ll_reminder_time);
                layoutTime.setVisibility(View.VISIBLE);
            }
        });

        Button buttonOkay = (Button) findViewById(R.id.bt_reminder_ok);
        buttonOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker = (DatePicker) findViewById(R.id.dp_reminder);
                TimePicker timePicker = (TimePicker) findViewById(R.id.tp_reminder);
                Calendar calendar = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());

                callback.setReminder(TimeUnit.MILLISECONDS.toSeconds(calendar.getTimeInMillis()));

                dismiss();
            }
        });

        Button buttonNoReminder = (Button) findViewById(R.id.bt_reminder_noreminder);
        buttonNoReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.removeReminder();
                dismiss();
            }
        });

    }

    public interface ReminderCallback {
        void setReminder(long deadline);
        void removeReminder();
    }

    public void setCallback(ReminderCallback callback) {
        this.callback = callback;
    }

}
