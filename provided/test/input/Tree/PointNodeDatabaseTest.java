package input.components.point;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Jackson Tedesco
 * @author	Tony Song
 * @author Case Riddle
 * @date 1/23/2024
 */
class PointNodeDatabaseTest {
	
	@Test
	void putAndContainsTest() throws NotInDatabase {
		PointNodeDatabase data = new PointNodeDatabase();
		
		
		
		assertFalse(data.contains(new PointNode(5, 5 )));
		PointNode node = new PointNode("A", 2, 5);
		data.put(node);
		
		assertEquals("A", data.getName(2.0, 5.0));
		
		assertTrue(data.contains(2.0, 5.0));
		assertFalse(data.contains(5.0, 2.0));
		assertFalse(data.contains(3.0, 7.0));
		
		assertTrue(data.contains(node));
		node = new PointNode("B", 5, 5);
		assertFalse(data.contains(node));
	}
	
	@Test
	void getNameTest() throws NotInDatabase {
		PointNodeDatabase data = new PointNodeDatabase();
		PointNode node = new PointNode("A", 2, 5);
		PointNode MyPointNode = new PointNode(3, 5);
		data.put(node);
		data.put(MyPointNode);
		
		assertEquals("A", data.getName(node));
		assertEquals("__UNNAMED", data.getName(MyPointNode));
		
		assertEquals("A", data.getName(2.0, 5.0));
		assertEquals("__UNNAMED", data.getName(3.0, 5.0));
		
		assertThrows(NotInDatabase.class, () -> {data.getName(15.0, 40.0);});
		assertThrows(NotInDatabase.class, () -> {data.getName(new PointNode(15, 40));});
	}
	
	@Test
	void getPointTest() throws NotInDatabase {
		PointNodeDatabase data = new PointNodeDatabase();
		PointNode node = new PointNode("A", 2, 5);
		data.put(node);
		
		assertEquals(node, data.getPoint(2.0, 5.0));
		assertEquals(node, data.getPoint(node));
		assertThrows(NotInDatabase.class, () -> {data.getPoint(3.0, 9.0);});
		PointNode node2 = new PointNode("B", 6, 5);
		assertThrows(NotInDatabase.class, () -> {data.getPoint(node2);});
	}
	
	@Test
	void unparseTest() {
		
	}
}
