package Service;

import Constants.ActivityEnum;
import DAO.CenterDatabase;
import Model.AvailableOptions;
import Model.Center;
import Model.User;
import Model.Workout;

import java.util.*;

public class CenterService {

    CenterDatabase centerDatabase = new CenterDatabase();
    List<Center> centerList = centerDatabase.getCenterList();

    public void addCenterActivity(String centerName, List<ActivityEnum> activityEnumList){
        for(Center center: centerList){
            if(center.getCenterName().equals(centerName)){
                center.setActivity(activityEnumList);
            }
        }
    }
    public void addCenter(String centerName[]){
        for(String i: centerName){
            centerList.add(new Center(i));
        }
    }

    public void addCenterTiming(String centerName, List<int[]> time ) {
        for(Center center: centerList){
            if(center.getCenterName().equals(centerName)){
                center.setTimings(time);
            }
        }
    }

    public void addCurrantDayWorkoutAndSeats(String centerName, ActivityEnum workoutType, int startTime, int endTime, int slots){
        System.out.println("Trying to add "+ centerName +" "+ workoutType+" "+startTime+" "+endTime+" "+ slots);
        for(Center center: centerList){
            if(center.getCenterName().equals(centerName)){

                if(!center.getActivity().contains(workoutType)){
                    System.out.println("Error:-" +" "+workoutType+" This workout not present");
                    break;
                }

                if(!insideCenterTimings(center, startTime, endTime)){
                    System.out.println("Error:- OutSide "+centerName+" timings, cant add");
                    break;
                }

                List<Workout> workoutList = center.getWorkouts();
                Workout workout = new Workout();
                workout.setWorkoutName(workoutType);
                workout.setStartTime(startTime);
                workout.setEndTime(endTime);
                workout.setSlotsAvailable(slots);
                workoutList.add(workout);

                break;
            }
        }
    }

    public void bookSession(User user, String centerName, ActivityEnum activityEnum, int startTime, int endTime){

        for(Center center: centerList){
            if(center.getActivity().contains(activityEnum) && center.getCenterName().equals(centerName)){
                List<Workout> workoutList = center.getWorkouts();
                for(Workout workout: workoutList){
                    if(workout.getWorkoutName().equals(activityEnum)){
                        //Multiple user booking the same slot
                        if(workout.getSlotsAvailable()>0) workout.setSlotsAvailable(workout.getSlotsAvailable()-1);
                        else{
                            List<User> notifyMe = workout.getNotifyMe();
                            notifyMe.add(user);
                            workout.setNotifyMe(notifyMe);
                            System.out.println("Workout not available, adding you to notify list");
                            break;
                        }
                    }
                }

            }
        }

    }


    //This is a good place to implement filter pattern, wasn't able to do it due to lack of time.
    public void viewWorkoutAvailableWithActivityType(ActivityEnum activityEnum){
        List<AvailableOptions> options = new ArrayList<>();

        for(Center center: centerList){
            if(center.getActivity().contains(activityEnum)){
                List<Workout> workoutList = center.getWorkouts();
                for(Workout workout: workoutList){
                    if(workout.getWorkoutName().equals(activityEnum)){
                        AvailableOptions availableOptions = new AvailableOptions();
                        availableOptions.setCenterName(center.getCenterName());
                        availableOptions.setStartTime(workout.getStartTime());
                        availableOptions.setEndTime(workout.getEndTime());
                        availableOptions.setSlots(workout.getSlotsAvailable());
                        options.add(availableOptions);
                    }
                }

            }
        }

        printAvailableOptions(options);

    }

    public void viewWorkoutWithActivityTypeAndCenterName(ActivityEnum activityEnum, String centerName){
        List<AvailableOptions> options = new ArrayList<>();
        for(Center center: centerList){
            if( center.getCenterName().equals(centerName) && center.getActivity().contains(activityEnum)){
                List<Workout> workoutList = center.getWorkouts();
                for(Workout workout: workoutList){
                    if(workout.getWorkoutName().equals(activityEnum)){
                        AvailableOptions availableOptions = new AvailableOptions();
                        availableOptions.setCenterName(center.getCenterName());
                        availableOptions.setStartTime(workout.getStartTime());
                        availableOptions.setEndTime(workout.getEndTime());
                        availableOptions.setSlots(workout.getSlotsAvailable());
                        options.add(availableOptions);
                    }
                }
                break;
            }
        }

        printAvailableOptions(options);

    }






    private boolean insideCenterTimings(Center center, int startTime, int endTime) {
        List<int[]> time = center.getTimings();
        for(int i[]: time){
            if(i[0]<=startTime && i[1]>=endTime){
                return true;
            }
        }
        return false;
    }

    public void cancelSession(User user, String centerName, ActivityEnum activityEnum, int startTime, int endTime) {
        for(Center center: centerList){
            if(center.getActivity().contains(activityEnum) && center.getCenterName().equals(centerName)){
                List<Workout> workoutList = center.getWorkouts();
                for(Workout workout: workoutList){
                    if(workout.getWorkoutName().equals(activityEnum)){
                        workout.setSlotsAvailable(workout.getSlotsAvailable()+1);
                    }
                }

            }
        }
    }

    public void printAvailableOptions(List<AvailableOptions> options){
        //Sorting on basis of time
        Collections.sort(options, Comparator.comparingInt(AvailableOptions::getStartTime));
        //Should have been in driver in case of API calls
        for(AvailableOptions availableOptions: options){
            System.out.println(availableOptions.getCenterName()+" "+availableOptions.getStartTime()+" "+availableOptions.getEndTime()+" "+availableOptions.getSlots());
        }
    }
}
