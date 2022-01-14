package Models.Timetable;

import java.sql.Time;

public class Timetable {
    private String teacher ;
    private String  subject;
    private String day;
    private String time_start;
    private String time_end;
    private int class_id;

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

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }
}
