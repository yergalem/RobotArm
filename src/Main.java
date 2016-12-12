import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            RobotArm robotArm = new RobotArm(10, 10);
        } catch (Exception e) {
            System.out.println("You probably should start by implementing the constructor :)");

        }
        System.out.println("Congratulations, you run the application");

    }

}
