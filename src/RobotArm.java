import java.util.*;
import java.util.Map.Entry;

/**
 * I added auxilary methods and some note as how the method works.
 * 
 * The content location is represented with <tt>contentMap</tt> that consists of 
 * list of contents filled up either COLUMN_WISE or ROW_WISE.
 * 
 * @author Yergalem
 * @since 12/15/ 2016
 */

public class RobotArm {

	private Map<Integer, List<Content>> contentMap;
	private Integer contentListCapacity;
	private Integer contentEntries;

	public RobotArm(Integer numberOfRows, Integer numberOfColumns) {
		contentMap = new LinkedHashMap<>(numberOfRows);
		contentEntries = numberOfRows;
		contentListCapacity = numberOfColumns;
	}

	/**
	 * Stores a content at the specified location (row and column)
	 * 
	 * @param content
	 *            Content which should be stored in the location
	 * @param row
	 *            the row the content should be stored at
	 * @param column
	 *            the column the content should be stored at
	 * @throws SomethingWentWrongException
	 */
	public void storeItemAtLocation(Content content, Integer row, Integer column) throws SomethingWentWrongException {

		final int mapIndex = row - 1, lstIndex = column - 1;

		ensureLocationPointFound(row, column);

		if (lstIndex >= contentListCapacity)
			throw new SomethingWentWrongException("Size is beyond Content List Capacity");

		List<Content> contentList = contentMap.get(mapIndex);

		if ( null == contentList ) {
			List<Content> newContentList = new LinkedList<>();
			if (lstIndex > 0)
				fillContentListWithDefaultValues(newContentList, 0, lstIndex);

			newContentList.add(content);
			contentMap.put( mapIndex, newContentList);

		} else {
			final int SIZE = contentList.size();
			if (lstIndex < SIZE && null != contentList.get(lstIndex))
				contentList.set(lstIndex, content);
			else {
				if (lstIndex >= SIZE)
					fillContentListWithDefaultValues(contentList, SIZE, lstIndex);
				contentList.add(content);

			}

			contentMap.put(mapIndex, contentList);
		}

	}
/**
 *  The method Fills up empty content locations with null so that it\'s possible to add
 *  at any given column or row without exception.
 *  
 * @param contentList
 * @param start  
 * @param prevLastIndex
 *
 */
	private void fillContentListWithDefaultValues(List<Content> contentList, Integer start, Integer prevLastIndex) {

		for (int i = start; i < prevLastIndex; i++)
			contentList.add(null);
	}
/**
 * Checks the whether the location points are valid
 * 
 */
	private void ensureLocationPointFound(Integer row, Integer column) {

		if (row == null || column == null || row <= 0 || column <= 0) {

			throw new SomethingWentWrongException("Wrong Location Point");
		}
	}

	/**
	 * Retrieves the content previously stored at the given row and column. The
	 * content should not be present in the location after it has been retrieved
	 *
	 * @param row
	 *            the row the content is currently located
	 * @param column
	 *            the column the content is currently located
	 * @return
	 * @throws SomethingWentWrongException
	 *             if anything goes wrong during the retrieval throw this
	 *             exception with a clear reason why this exception was thrown
	 */
	public Content retrieveItemAtLocation(Integer row, Integer column) throws SomethingWentWrongException {

		final Content contentElement;
		final List<Content> contentEntry;

		ensureLocationPointFound(row, column);
		if (contentMap.size() == 0)
			throw new SomethingWentWrongException("Empty Location");

		contentEntry = contentMap.get(row - 1);

		if (null == contentEntry)
			throw new SomethingWentWrongException("No Content Found at row " + row);

		contentElement = contentEntry.get(column - 1);

		if (null == contentElement) {
			throw new SomethingWentWrongException(" Empty Content Found!");
		}

		return contentElement;
	}

	/**
	 * Should remove any content currently stored in this location
	 */
	public void removeAllContentFromLocation() {

		contentMap.clear();
	}

	/**
	 * Should retrieve the content with the given barcode. You are allowed to
	 * assume that barcodes will be unique. The content should not be present in
	 * the location after it has been retrieved
	 *
	 * @param barcode
	 *            the barcode for the sample
	 * @return
	 * @throws SomethingWentWrongException
	 *             if anything goes wrong during the retrieval throw this
	 *             exception with a clear reason why this exception was thrown
	 */
	public Content retrieveItemWithBarcode(String barcode) throws SomethingWentWrongException {

		List<Content> contentList = null;
		Content removedItem = null;
		if (null == barcode)
			throw new SomethingWentWrongException("Invalid Barcode");

		for (Map.Entry<Integer, List<Content>> entry : contentMap.entrySet()) {

			contentList = entry.getValue();
			removedItem = removeItemFromContentList(contentList, barcode);
			if (removedItem != null)
				break;

		}

		if (removedItem == null)
			throw new SomethingWentWrongException("Item with Barcode " + barcode + " doesn't exist.");

		return removedItem;
	}

	private Content removeItemFromContentList(List<Content> contentList, String barcode) {

		ListIterator<Content> lstItr = contentList.listIterator();
		Content barCodedItem = null;

		while (lstItr.hasNext()) {
			Content content = lstItr.next();

			if (content.getBarcode().equals(barcode)) {
				barCodedItem = content;
				lstItr.remove();
				break;
			}
		}

		return barCodedItem;
	}

	/**
	 * This method should fill up the location with the given contents depending
	 * on the 'FillingStrategy'.
	 *
	 * If the filling strategy is ROW_WISE you should start with the first
	 * available space in the top left location and fill up every column until
	 * the current row is filled. Then move to the next row.
	 *
	 * If the filling strategy is COLUMN_WISE you should start by filling it
	 * column wise, meaning take the first column and place an item in each row
	 * until the column is filled.
	 *
	 *
	 * @param contents
	 *            list of contents to be inserted into the location
	 * @param strategy
	 *            the strategy of how the location should be filled up
	 * @throws SomethingWentWrongException
	 */

	public void fillLocationWithItems(List<Content> contents, FillingStrategy strategy)
			throws SomethingWentWrongException {

		int currentRow = 1, currentColumn = 1, rowCount = 1;

		if (contentMap.size() != 0)
			currentRow = getNextAvailablePositionOfContentMap();

		switch (strategy) {

		case COLUMN_WISE:
			rowCount = currentRow; // Last entry , In case location was filled
									// with certain entries

			for (Content content : contents)
				if (currentRow < contentEntries)
					storeItemAtLocation(content, currentRow++, currentColumn);
				else if (currentRow % contentEntries == 0) {
					currentRow = rowCount;
					storeItemAtLocation(content, currentRow, ++currentColumn);
				}

			break;

		case ROW_WISE:
			for (Content content : contents) {
				if (currentColumn % contentListCapacity == 0) {
					currentColumn = 1;
					currentRow++;
				}
				storeItemAtLocation(content, currentRow, currentColumn++);
			}
			break;

		}

	}

	/**
	 * The method returns the last available row of content location to continue
	 * filling-up from.
	 * 
	 * It\'s before the content Map initial capacity and used to store at an arbitrary
	 * row.
	 *   
	 * @return  the last row of content map where in content list gets added.
	 * @see fillLocationWithItems
	 *
	 */
	private int getNextAvailablePositionOfContentMap() {

		return contentMap.size() + 1;

	}

	/**
	 * This method should reorder all the contents currently stored in the
	 * location. The filling is either column wise or row wise, depending what
	 * type of 'FillingStrategy' was passed as a parameter.
	 *
	 * The order should be the volume of the content in a decreasing order
	 * (highest volume first), if two contents have the same volume order them
	 * alphabetically after their barcode.
	 *
	 * @param strategy
	 *            The strategy how the items should be recorded (row or column
	 *            wise)
	 */
	public void reorder(FillingStrategy strategy) {

		List<Content> contentsLongList = null;
		if (contentMap.size() == 0)
			throw new SomethingWentWrongException("Empty Location");
        
		contentsLongList = sortContentMapByVolumeAndBarcode(contentMap);
		removeAllContentFromLocation();

		switch (strategy) {
		case ROW_WISE:		
			fillLocationWithItems(contentsLongList, FillingStrategy.ROW_WISE);
			break;
		case COLUMN_WISE:
			fillLocationWithItems(contentsLongList, FillingStrategy.COLUMN_WISE);
			break;
		}

	}
/**
 * The method performs the sorting of content location based on Volume and Barcode.
 * Arranges the contents according to their volume in decreasing order and alphabetically sorts
 * using barcode for matching volume.
 * 
 * @param unOrderderdContentMap content Location containing unsorted contents
 * @return
 */
	private List<Content> sortContentMapByVolumeAndBarcode(Map<Integer, List<Content>> unOrderderdContentMap) {

		List<Content> contentsLongList = new LinkedList<>();

		for (Map.Entry<Integer, List<Content>> entry : unOrderderdContentMap.entrySet())
			for (Content content : entry.getValue())
				contentsLongList.add(content);

		Collections.sort(contentsLongList, (c1, c2) -> {
			Integer c1Volume = c1.getVolume();
			Integer c2Volume = c2.getVolume();
			String c1Barcode = c1.getBarcode();
			String c2Barcode = c2.getBarcode();

			return c2Volume - c1Volume != 0 ? c2Volume - c1Volume : c1Barcode.compareTo(c2Barcode);
		});

		return contentsLongList;
	}
/**
 * The method performs the sorting of content location according to ContentType and Price.
 * Arranges the price ordering and sorts based on Content Type for contents with same price.
 * 
 * @param unOrderderdContentMap content Location containing unsorted contents
 * @return
 *
 */
	private List<Content> sortContentMapByContentTypeAndPrice(Map<Integer, List<Content>> unOrderderdContentMap) {

		List<Content> contentsLongList = new LinkedList<>();

		for (Map.Entry<Integer, List<Content>> entry : unOrderderdContentMap.entrySet())
			for (Content content : entry.getValue())
				contentsLongList.add(content);

		Collections.sort(contentsLongList, (c1, c2) -> {
			Double c1Price = c1.getPrice();
			Double c2Price = c2.getPrice();
			ContentType c1Type = c1.getType();
			ContentType c2Type = c2.getType();

			return c1Type.compareTo(c2Type) != 0 ? c1Type.compareTo(c2Type) : (int) (c1Price - c2Price);
		});

		return contentsLongList;
	}

	/**
	 * This method will be used when the lab receives an order. A customer might
	 * request to the lab to deliver a certain volume of a specified
	 * 'ContentType'. The robot should be able to retrieve the required volume
	 * from the location and this method should find the minimal cost to the lab
	 * which will fulfil the order.
	 *
	 * Even 'Content' of the same 'ContentType' might have different prices
	 * inside the location. To collect the required volume, deduct it from each
	 * 'Content' you used to fulfil the order (again try to minimize the total
	 * cost).
	 *
	 * If the given volume can't be retrieved due to the lack of content of the
	 * given type, nothing should be deducted from any item.
	 *
	 * Therefore this method should only modify any content in the location if
	 * enough volume is present of the specified type.
	 *
	 * Partial removal of volume from a content is allowed to fulfil the order.
	 *
	 * The order returned should account for any content used (through its
	 * barcode) even if the content was only partially used in that order.
	 *
	 * Even if all the volume of a 'Content' is used for an order you do not
	 * need to remove it from the location.
	 *
	 * @param volume
	 * @param type
	 * @return
	 * @throws SomethingWentWrongException
	 */
	public Order fulfilOrderWithMinimalCostForVolumeAndType(Integer volume, ContentType type)
			throws SomethingWentWrongException {

		List<Content> contentsLongList = null;
		if (contentMap.size() == 0)
			throw new SomethingWentWrongException("Empty Location");

		contentsLongList = sortContentMapByContentTypeAndPrice(contentMap);
		Integer enoughVolume = 0;
		for (Content content : contentsLongList)
			if (content.getType().equals(type))
				enoughVolume += content.getVolume();

		if (enoughVolume < volume)
			throw new SomethingWentWrongException("Insufficient volume of Content");

		Order customerOrder = retrieveContentOrder(contentsLongList, volume, type);

		return customerOrder;
	}
	/**
	 *  The method arranges Content Map to single long list. As the content list is sorted 
	 *  it keeps updating the content volume while deducting the volume required from a given
	 *  content.
	 *  
	 *  Sums the price based on the content's price for a content from which the volume gets
	 *  deducted to fill-up the requested amount.
	 *  
	 * @param contentsLongList  Long list of the Contents in the Location Map 
	 * @param volume the content volume customer orders
	 * @param type  the content type ordered
	 * @return the order requested by a customer or <tt> null </tt> if the content volume can\'t 
	 *        be found.
	 *
	 */

	private Order retrieveContentOrder(List<Content> contentsLongList, Integer volume, ContentType type) {

		List<String> contentsUsedForOrder = new ArrayList<>();
		Double price = 0.0;
		Integer contentVol = 0;

		for (Content content : contentsLongList)
			if (content.getType().equals(type)) {
				contentVol = content.getVolume();
				if (contentVol <= volume) {
					content.setVolume(0);
					volume -= contentVol;
					price += content.getPrice();
					contentsUsedForOrder.add(content.getBarcode());

				} else if (volume == 0)	 break;
				  else {
					content.setVolume( contentVol - volume);
					price += volume * content.getPrice() / contentVol;
					volume = 0;
					contentsUsedForOrder.add(content.getBarcode());
				}
			}
		
		return  contentsUsedForOrder.size() !=0 ?
			    new Order(contentsUsedForOrder, price, volume, type) : null;
	}

	/**
	 * This method is a little bonus, you should print out (in the console) the
	 * current content stored in the location. How you decide to print it out is
	 * up to you.
	 *
	 * @return
	 */
	public String showLocationContent() {
		Content content = null;
        printHeader();
        
		for (Map.Entry<Integer, List<Content>> entry : contentMap.entrySet()) {
			List<Content> contentLst = entry.getValue();

			for (int i = 0; i < contentListCapacity; i++) {
				if (contentLst == null)
					printEmptyPlaceHolder(contentListCapacity);
				else {
					content = i < contentLst.size() ? contentLst.get(i) : null;
					if (content == null)
						System.out.printf("%5s","");
					else
						System.out.printf("%5s",content.getBarcode() );
				}
			}
			System.out.println();
		}

		return " Smart Robot Arm! ";

	}

	private void printHeader() {
		System.out.println("----------------------------------------------------------------------");
        System.out.println("                ROBOT ARM LOCATION CONTENT                            ");
        System.out.println("----------------------------------------------------------------------");
        
        for (int i = 0; i < contentListCapacity; i++)
			System.out.printf("%5d",i+1);
        
        System.out.println();
		
	}

	private void printEmptyPlaceHolder(Integer contentListCapacity) {

		for (int i = 0; i < contentListCapacity; i++)
			System.out.printf("%5s","");

	}

	public Map<Integer, List<Content>> getContentMap() {
		return contentMap;
	}

	public void setContentMap(Map<Integer, List<Content>> map) {
		contentMap = map;
	}

}
