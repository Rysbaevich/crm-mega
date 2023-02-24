package model;

import enums.Format;
import enums.FormatOldVersion;

public class CourseFormat extends BaseEntity {
    private Format format;
    private int durationInWeek;
    private boolean isOnline;
    private int lessonDuration;
    private int lessonCountPerWeek;

    @Override
    public String toString() {
        return "CourseFormat{" +
                "id=" + id +
                ", format=" + format.getA() +
                ", durationInWeek=" + durationInWeek +
                ", isOnline=" + isOnline +
                ", lessonDuration=" + lessonDuration +
                ", lessonCountPerWeek=" + lessonCountPerWeek +
                ", dateCreated=" + dateCreated +
                '}';
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public int getDurationInWeek() {
        return durationInWeek;
    }

    public void setDurationInWeek(int durationInWeek) {
        this.durationInWeek = durationInWeek;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getLessonDuration() {
        return lessonDuration;
    }

    public void setLessonDuration(int lessonDuration) {
        this.lessonDuration = lessonDuration;
    }

    public int getLessonCountPerWeek() {
        return lessonCountPerWeek;
    }

    public void setLessonCountPerWeek(int lessonCountPerWeek) {
        this.lessonCountPerWeek = lessonCountPerWeek;
    }
}
