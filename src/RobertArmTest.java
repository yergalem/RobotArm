
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
/**
 * Unit Tests the Robert Arm methods. Sample data are given in each test units.
 * Some of them show content of the location to see the effect while others only
 * ensure the correctness of the operation.
 * 
 * @author Yergalem
 *
 */
public class RobertArmTest {

	RobotArm robotArm;

	@Before
	public void setUp() {
		robotArm = new RobotArm(10, 10);

	}

	@Test 
	public void testStoreAtElementLocation() {

		Content content = new Content("BUF", "SampleBuffer", 'B', 200, 200.5, ContentType.BUFFER);
		Content anothercontent = new Content("BLD", "SampleBlood", 'B', 1000, 150.5, ContentType.BLOOD);

		robotArm.storeItemAtLocation(content, 1, 1);
		robotArm.storeItemAtLocation(anothercontent, 3, 1);

	}

	@Test //(expected = SomethingWentWrongException.class)
	public void testRetrieveElementAtLocation() {

		Content content = new Content("BUF", "SampleBuffer", 'B', 200, 200.5, ContentType.BUFFER);
		Content anothercontent = new Content("BLD", "SampleBlood", 'B', 1000, 150.5, ContentType.BLOOD);

		robotArm.storeItemAtLocation(content, 1, 1);
		robotArm.storeItemAtLocation(anothercontent, 3, 1);

		Content contentR1C1 = robotArm.retrieveItemAtLocation(1, 1);
		Content contentR3C1 = robotArm.retrieveItemAtLocation(3, 1);

		assertNotNull(contentR1C1);
		assertNotNull(contentR3C1);

		int volume = contentR1C1.getVolume();
		int volume2 = contentR3C1.getVolume();

		assertEquals(200, volume);
		assertEquals(1000, volume2);
	}
/**
 *  Unit Tests the operation of filling Location Content either COLUMN_WISE or ROW_WISE.
 *  Ensures if items are properly filled up by retrieving content inserted at particular position.
 *  Displays the contents filled in the location.
 */
	@Test //(expected = SomethingWentWrongException.class)
	public void testfillLocationWithItems() {

		Content content = new Content("BUF", "SampleBuffer", 'B', 200, 200.5, ContentType.BUFFER);
		Content anothercontent = new Content("BLD", "SampleBlood", 'B', 1000, 150.5, ContentType.BLOOD);
		List<Content> contents = new ArrayList<>();
		contents.add(content);
		contents.add(anothercontent);
		robotArm.fillLocationWithItems(contents, FillingStrategy.COLUMN_WISE);
		Content contentR1C2 = robotArm.retrieveItemAtLocation(2, 1);
		
		robotArm.fillLocationWithItems(contents, FillingStrategy.ROW_WISE);
		
		int volume = contentR1C2.getVolume();
		System.out.printf(" \n <-- Testing Content Filling to a Location --> \n");
		robotArm.showLocationContent();
		assertEquals(1000, volume);
		
	}


	@Test
	public void testReorderContent() {

		Content content = new Content("BUF", "SampleBuffer", 'B', 200, 200.5, ContentType.BUFFER);
		Content anothercontent = new Content("BLD", "SampleBlood", 'B', 1000, 150.5, ContentType.BLOOD);
		List<Content> contents = new ArrayList<>();
		contents.add(content);
		contents.add(anothercontent);
		robotArm.fillLocationWithItems(contents, FillingStrategy.ROW_WISE );
		
		robotArm.reorder(FillingStrategy.COLUMN_WISE);
		System.out.printf(" \n <-- Testing Content Ordering to a Location --> \n");
		robotArm.showLocationContent();

		Content contentR1C2 = robotArm.retrieveItemAtLocation(1, 1);

		int volume = contentR1C2.getVolume();

		assertEquals( 1000, volume);

	}
	
	@Test
	public void testFulfilOrderWithMinimalCostForVolumeAndType() {
		
		Content content = new Content("BUF", "SampleBuffer", 'B', 200, 200.5, ContentType.BUFFER);
		Content anothercontent = new Content("BLD", "SampleBlood", 'B', 1000, 1250.5, ContentType.BLOOD);
		Content mincontent = new Content("RBC", "SampleBlood", 'B', 100, 150.5, ContentType.BLOOD);
		Content secondcontent = new Content("WBC", "SampleBlood", 'B', 500, 750.0, ContentType.BLOOD);
		List<Content> contents = new ArrayList<>();
		contents.add(content);   contents.add(secondcontent);
		contents.add(anothercontent);  contents.add(mincontent);
		
		robotArm.fillLocationWithItems(contents, FillingStrategy.ROW_WISE);
       
		Order order = robotArm.fulfilOrderWithMinimalCostForVolumeAndType(150, ContentType.BLOOD );
		
		double modifiedSecondContentPrice  = robotArm.retrieveItemAtLocation(1, 2).getPrice();
		assertNotNull(order);        

		assertEquals( 2 , order.getContentBarcodesUsed().size());
		assertEquals("RBC",  order.getContentBarcodesUsed().get(0));
		
		
		assertEquals(  450 ,  modifiedSecondContentPrice  , modifiedSecondContentPrice - 450 );
		assertEquals("RBC",  order.getContentBarcodesUsed().get(0));
		assertEquals( 225.5,  order.getPrice(), order.getPrice() - 225.5 );
	}
	
	
	@Test
	public void testRetrieveItemWithBarcode() {
		
		Content content = new Content("BUF", "SampleBuffer", 'B', 200, 200.5, ContentType.BUFFER);
		Content anothercontent = new Content("BLD", "SampleBlood", 'B', 1000, 1250.5, ContentType.BLOOD);
		Content mincontent = new Content("RBC", "Red Blood Cells", 'B', 100, 150.5, ContentType.BLOOD);
		Content secondcontent = new Content("WBC", "White Blood Cells", 'B', 500, 750.32, ContentType.BLOOD);
		List<Content> contents = new ArrayList<>();
		contents.add(content);   contents.add(secondcontent);
		contents.add(anothercontent);  contents.add(mincontent);
		
		robotArm.fillLocationWithItems(contents, FillingStrategy.ROW_WISE);
		
		Content contentRetrievd = robotArm.retrieveItemWithBarcode("BLD");
		
		assertNotNull(contentRetrievd);
		
		assertEquals( "SampleBlood", contentRetrievd.getName() );
		
	}
	
	@Test ( expected = SomethingWentWrongException.class )
	public void testRemoveAllContentFromLocation() {
		
		Content content = new Content("BUF", "SampleBuffer", 'B', 200, 200.5, ContentType.BUFFER);
		Content anothercontent = new Content("BLD", "SampleBlood", 'B', 1000, 1250.5, ContentType.BLOOD);
		Content mincontent = new Content("RBC", "Red Blood Cells", 'B', 100, 150.5, ContentType.BLOOD);
		Content secondcontent = new Content("WBC", "White Blood Cells", 'B', 500, 750.32, ContentType.BLOOD);
		
		List<Content> contents = new ArrayList<>();
		contents.add(content);   contents.add(secondcontent);
		contents.add(anothercontent);  contents.add(mincontent);
		
		robotArm.fillLocationWithItems(contents, FillingStrategy.ROW_WISE);
		Content item = robotArm.retrieveItemAtLocation(1, 3);
		
		assertEquals("BLD",  item.getBarcode() );
		
	    robotArm.removeAllContentFromLocation();
	    Content itemNonExisting = robotArm.retrieveItemAtLocation(1, 3);
	    
	    assertNull( itemNonExisting.getBarcode() );
	}	
	

}
