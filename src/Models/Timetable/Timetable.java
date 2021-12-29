package Models.Timetable;

import java.sql.Time;

public class Timetable {
    private String teacher ;
    private String  subject;
    private String day;
    private Time time_start;
    private Time time_end;

    public String getSubject() {
        return subject;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDay() {
        return day;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Time getTime_end() {
        return time_end;
    }

    public void setTime_end(Time time_end) {
        this.time_end = time_end;
    }

    public Time getTime_start() {
        return time_start;
    }

    public void setTime_start(Time time_start) {
        this.time_start = time_start;
    }

}
