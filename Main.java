import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        Robot robot = new RobotArm(10, 10);

        RobotArm robotArm = new RobotArm(10, 10);
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            items.add(new Item("blood" + i, "blood" + i, 'b', i, price, type));

        }
        robotArm.storeItemAtLocation(new Item("blood", "blood" , 'x', 70, price, type), 1,1);
        robotArm.fillFreezerWithItems(items, FillStrategy.COLUMN_WISE);


//        System.out.println(status.getStatus() + ": " +  status.getMessage());
        System.out.println(robotArm.showFridgeContent());
    }

}
