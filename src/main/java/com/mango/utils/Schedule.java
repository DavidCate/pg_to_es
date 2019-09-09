package com.mango.utils;

public class Schedule {
    private String schedule;

    public long getMillions() {
        return millions;
    }

    public void setMillions(long millions) {
        this.millions = millions;
    }

    private long millions=0l;

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Schedule(Configuration configuration) {
        this.setSchedule(configuration.getSchedule());
        String[] strings=schedule.split("/*");

        for (String string:strings){
            millions+=Long.getLong(string);
        }
    }

    public long getSleepTime() {
        return millions;
    }
}
