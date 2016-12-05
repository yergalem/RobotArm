import java.util.*;

/**
 * Created by roger on 02.12.16.
 */
public class RobotArm {

    public RobotArm(Integer numberOfRows, Integer numberOfColumns) {
        throw new UnsupportedOperationException();
    }


    public StatusMessage storeItemAtLocation(Item item, Integer row, Integer column) {
        throw new UnsupportedOperationException();
    }


    public Item retrieveItemAtLocation(Integer row, Integer column) {
        throw new UnsupportedOperationException();
    }

    public Item retrieveItemWithBarcode(String barcode) {
        throw new UnsupportedOperationException();
    }

    public StatusMessage reorder() {
        throw new UnsupportedOperationException();
    }

    public StatusMessage fillFreezerColumnWiseWithItems(List<Item> items) {
        throw new UnsupportedOperationException();
    }

    public StatusMessage fillFreezerRowWiseWithItems(List<Item> items) {
        throw new UnsupportedOperationException();

    }

    public Order minimalCostForVolumeAndType(Integer volume, ItemType type) {
        throw new UnsupportedOperationException();

    }


    public String showFridgeContent(){
        throw new UnsupportedOperationException();
    }

}
