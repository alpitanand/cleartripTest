import Constants.ActivityEnum;
import DAO.CenterDatabase;
import DAO.UserDatabase;
import Model.Center;
import Model.User;
import Service.CenterService;
import Service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //This is my driver class
        System.out.println("Hello cleartrip :p");

        CenterDatabase centerDatabase = new CenterDatabase();
        UserDatabase userDatabase = new UserDatabase();
        List<Center> centerList = centerDatabase.getCenterList();
        List<User> userList = userDatabase.getUsers();

         // Initializing service class, Autowired would have been better choice
        CenterService centerService = new CenterService();
        UserService userService = new UserService();

        //Adding centers (this should have been enum)
        String centerName[] = {"Kormangla", "Bellandur","Sarjapur"};
        centerService.addCenter(centerName);

        List<int[]> time = new ArrayList<>();
        time.add(new int[]{6,9});
        time.add(new int[]{18,20});
        centerService.addCenterTiming("Kormangla", time);

        ArrayList<ActivityEnum> centerActivity = new ArrayList<>();
        centerActivity.add(ActivityEnum.CARDIO);
        centerActivity.add(ActivityEnum.YOGA);
        centerActivity.add(ActivityEnum.WEIGHTS);
        centerActivity.add(ActivityEnum.SWIMMING);
        centerService.addCenterActivity("Kormangla", centerActivity);

        time = new ArrayList<>();
        time.add(new int[]{7,10});
        time.add(new int[]{20,22});
        centerService.addCenterTiming("Bellandur", time);

        centerActivity = new ArrayList<>();
        centerActivity.add(ActivityEnum.CARDIO);
        centerActivity.add(ActivityEnum.YOGA);
        centerActivity.add(ActivityEnum.WEIGHTS);
        centerService.addCenterActivity("Bellandur", centerActivity);


        //Add workout and seats to location
        centerService.addCurrantDayWorkoutAndSeats("Kormangla",ActivityEnum.WEIGHTS,6,7,100);
        centerService.addCurrantDayWorkoutAndSeats("Kormangla",ActivityEnum.CARDIO,7,8,150);
        centerService.addCurrantDayWorkoutAndSeats("Kormangla",ActivityEnum.YOGA,8,9,200);

        centerService.addCurrantDayWorkoutAndSeats("Bellandur",ActivityEnum.WEIGHTS,18,19,100);
        centerService.addCurrantDayWorkoutAndSeats("Bellandur",ActivityEnum.SWIMMING,6,7,100);
        centerService.addCurrantDayWorkoutAndSeats("Bellandur",ActivityEnum.CARDIO,19,20,20);
        centerService.addCurrantDayWorkoutAndSeats("Bellandur",ActivityEnum.WEIGHTS,20,21,100);
        centerService.addCurrantDayWorkoutAndSeats("Bellandur",ActivityEnum.WEIGHTS,21,22,100);

        System.out.println("End admin operation:-======================================================");

        userService.addUser("Vaibhav");
        //Trying to mimic session
        User user = userService.getUser("Vaibhav");

        //User query
        centerService.viewWorkoutAvailableWithActivityType(ActivityEnum.WEIGHTS);
        centerService.bookSession(user, "Kormangla",ActivityEnum.WEIGHTS,6,7);
        centerService.viewWorkoutAvailableWithActivityType(ActivityEnum.WEIGHTS);
        centerService.cancelSession(user,"Kormangla",ActivityEnum.WEIGHTS,6,7);
        centerService.viewWorkoutAvailableWithActivityType(ActivityEnum.WEIGHTS);

        //Extra query
        centerService.viewWorkoutWithActivityTypeAndCenterName(ActivityEnum.WEIGHTS, "Kormangla");


    }
}