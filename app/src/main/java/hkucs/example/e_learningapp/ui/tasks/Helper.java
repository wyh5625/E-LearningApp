
package hkucs.example.e_learningapp.ui.tasks;


import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Helper {

    public static final CharSequence DATE_FORMAT = "dd.MM.yyyy";

    public static String getDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUnit.SECONDS.toMillis(time));
        return DateFormat.format("dd.MM.yyyy", calendar).toString();
    }

    public static String getDateTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUnit.SECONDS.toMillis(time));
        return DateFormat.format("dd.MM.yyyy HH:mm", calendar).toString();
    }

    public static long getCurrentTimestamp() {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }


}

