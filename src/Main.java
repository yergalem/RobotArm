import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        Robot robot = new RobotArm(10, 10);

        RobotArm robotArm = new RobotArm(10, 10);
        List<Content> contents = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            contents.add(new Content("blood" + i, "blood" + i, 'b', i, i, ContentType.BLOOD));

        }
        robotArm.storeItemAtLocation(new Content("blood", "blood" , 'x', 70, 2, ContentType.PLASMA), 1,1);


//        System.out.println(status.getStatus() + ": " +  status.getMessage());
        System.out.println(robotArm.showFridgeContent());
    }

}
