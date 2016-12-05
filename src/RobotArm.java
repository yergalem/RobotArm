import java.util.*;

/**
 * Your task is to implement the RobotArm API which consists of the methods given below.
 *
 * You are free to choose how you implement it, from the way you store the content to the way it will be retrieved afterwards.
 * Feel free to add any properties or auxiliary methods as you see fit. The only requirement is that you do not
 * modify the given API methods and return the correct items.
 *
 * Handle anything that fails with the same type of exception 'SomethingWentWrongException' and add a reasonable
 * description of what went wrong.
 */

public class RobotArm {

    /**
     * Constructor for the RobotArm, it takes the size of the location the robot will interact with. Each location can
     * be thought of as a matrix of sub-locations, each location is a rectangle in size and will always contain the same number
     * of columns per row
     * @param numberOfRows number of rows present in the location
     * @param numberOfColumns number of columns of the location
     */
    public RobotArm(Integer numberOfRows, Integer numberOfColumns) {
        throw new UnsupportedOperationException();
    }


    /**
     * Stores a content at the specified location (row and column)
     * @param content Content which should be stored in the location
     * @param row the row the content should be stored at
     * @param column the column the content should be stored at
     * @throws SomethingWentWrongException
     */
    public void storeItemAtLocation(Content content, Integer row, Integer column) throws SomethingWentWrongException {
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieves the content previously stored at the given row and column. The content should not be present in the
     * location after it has been retrieved
     *
     * @param row the row the content is currently located
     * @param column the column the content is currently located
     * @return
     * @throws SomethingWentWrongException if anything goes wrong during the retrieval throw this exception with a
     * clear reason why this exception was thrown
     */
    public Content retrieveItemAtLocation(Integer row, Integer column) throws SomethingWentWrongException {
        throw new UnsupportedOperationException();
    }

    /**
     * Should remove any content currently stored in this location
     */
    public void removeAllContentFromLocation(){
        throw new UnsupportedOperationException();
    }

    /**
     * Should retrieve the content with the given barcode. You are allowed to assume that barcodes will be unique. The content should not be present in the
     * location after it has been retrieved
     *
     * @param barcode the barcode for the sample
     * @return
     * @throws SomethingWentWrongException if anything goes wrong during the retrieval throw this exception with a
     * clear reason why this exception was thrown
     */
    public Content retrieveItemWithBarcode(String barcode) throws SomethingWentWrongException {
        throw new UnsupportedOperationException();
    }

    /**
     * This method should fill up the location with the given contents depending on the 'FillingStrategy'.
     *
     * If the filling strategy is ROW_WISE you should start with the first available space in the top left location
     * and fill up every column until the current row is filled. Then move to the next row.
     *
     * If the filling strategy is COLUMN_WISE you should start by filling it column wise, meaning take the first
     * column and place an item in each row until the column is filled.
     *
     *
     * @param contents list of contents to be inserted into the location
     * @param strategy the strategy of how the location should be filled up
     * @throws SomethingWentWrongException
     */

    public void fillLocationWithItems(List<Content> contents, FillingStrategy strategy) throws SomethingWentWrongException {
        throw new UnsupportedOperationException();
    }

    /**
     * This method should reorder all the contents currently stored in the location. The filling is either column wise or
     * row wise, depending what type of 'FillingStrategy' was passed as a parameter.
     *
     * The order should be the volume of the content in a decreasing order (highest volume first), if two contents have
     * the same volume order them alphabetically after their barcode.
     *
     * @param strategy The strategy how the items should be recorded (row or column wise)
     */
    public void reorder(FillingStrategy strategy) {
        throw new UnsupportedOperationException();
    }


    /**
     * This method should gather the given volume of the type defined by 'ContentType'. The aim is to minimize the
     * cost of the required volume. The deducted volume of each content to fulfill the order should be directly deducted
     * from each content used. If the given volume can't be retrieved due to the lack of content of the given type,
     * nothing should be deducted from any item.
     *
     * Therefore this method should only modify any content in the location if enough volume is present of the specified
     * type.
     *
     * Partial removal of volume from a content is allowed to fulfil the order.
     *
     * The order returned should account for any content used (through its barcode) even if the content was only
     * partially used in that order.
     *
     * @param volume
     * @param type
     * @return
     * @throws SomethingWentWrongException
     */
    public Order minimalCostForVolumeAndType(Integer volume, ContentType type) throws SomethingWentWrongException {
        throw new UnsupportedOperationException();

    }


    /**
     * This method is a little bonus, you should print out (in the console) the current content stored in the location.
     * How you decide to print it out is up to you.
     *
     * @return
     */
    public String showLocationContent(){
        throw new UnsupportedOperationException();
    }

}
