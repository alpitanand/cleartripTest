package Model;

import Constants.ActivityEnum;

import java.util.ArrayList;
import java.util.List;

public class Workout {


    ActivityEnum workoutName;
    int startTime;
    int endTime;
    int slotsAvailable;

    List<User> notifyMe = new ArrayList<>();

    public ActivityEnum getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(ActivityEnum workoutName) {
        this.workoutName = workoutName;
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

    public int getSlotsAvailable() {
        return slotsAvailable;
    }

    public void setSlotsAvailable(int slotsAvailable) {
        this.slotsAvailable = slotsAvailable;
    }

    public List<User> getNotifyMe() {
        return notifyMe;
    }

    public void setNotifyMe(List<User> notifyMe) {
        this.notifyMe = notifyMe;
    }
}
