import java.util.ArrayList;
import java.util.List;
/**
 * Use RobotArmTest.java unit test to interact with RobotArm
 * 
 * @author Yergalem
 *
 */
public class Main {

    public static void main(String[] args) {
        try {
            RobotArm robotArm = new RobotArm(10, 10);
        } catch (Exception e) {
            System.out.println("You probably should start by implementing the constructor :)");

        }
        System.out.println("Use the Unit Test( RobotArmTest.java) to check the workability of the methods i");

    }

}
