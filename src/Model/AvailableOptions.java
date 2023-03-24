package Model;

import Constants.ActivityEnum;

public class AvailableOptions {
    String centerName;
    ActivityEnum anEnum;
    int startTime;
    int endTime;
    int slots;

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public ActivityEnum getAnEnum() {
        return anEnum;
    }

    public void setAnEnum(ActivityEnum anEnum) {
        this.anEnum = anEnum;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }
}
