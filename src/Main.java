import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        RobotArm robotArm = new RobotArm(10, 10);

        robotArm.storeItemAtLocation(new Content("P0001", "Plasma_0001" , 'x', 70, 2, ContentType.PLASMA), 1,1);
        
        System.out.println(robotArm.showLocationContent());
    }

}
