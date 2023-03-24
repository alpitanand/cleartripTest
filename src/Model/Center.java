package Model;

import Constants.ActivityEnum;

import java.util.ArrayList;
import java.util.List;

public class Center {

   //@Id -> Unique id
   String centerName;

   List<int[]> timings;

   List<ActivityEnum> activity = new ArrayList<>();
   List<Workout> workouts = new ArrayList<>();



   public Center(String centerName){
      this.centerName = centerName;
   }


   public String getCenterName() {
      return centerName;
   }

   public List<ActivityEnum> getActivity() {
      return activity;
   }

   public List<Workout> getWorkouts() {
      return workouts;
   }

   public List<int[]> getTimings() {
      return timings;
   }

   public void setTimings(List<int[]> timings) {
      this.timings = timings;
   }

   public void setActivity(List<ActivityEnum> activity) {
      this.activity = activity;
   }
}
