import java.util.Calendar;

public class ShiftDay {
    Calendar startTime;
    Calendar endTime;
    Calendar day;

    ShiftDay(){
        startTime = Calendar.getInstance();
        endTime = Calendar.getInstance();
        day = Calendar.getInstance();
    }
}
