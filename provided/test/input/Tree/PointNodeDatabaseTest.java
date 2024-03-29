package input.Tree;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.Test;

import input.components.point.NotInDatabaseException;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;

/**
 * Test PointNodeDatabaseTest
 * @author Jackson Tedesco, Case Riddle
 * @date 2/21/2024
 */
class PointNodeDatabaseTest {
	@Test
	void putAndContainsTest() throws NotInDatabaseException {
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
	void getNameTest() throws NotInDatabaseException {
		PointNodeDatabase data = new PointNodeDatabase();
		PointNode node = new PointNode("A", 2, 5);
		PointNode MyPointNode = new PointNode(3, 5);
		data.put(node);
		data.put(MyPointNode);

		assertEquals("A", data.getName(node));
		assertEquals("__UNNAMED", data.getName(MyPointNode));

		assertEquals("A", data.getName(2.0, 5.0));
		assertEquals("__UNNAMED", data.getName(3.0, 5.0));

		assertThrows(NotInDatabaseException.class, () -> {data.getName(15.0, 40.0);});
		assertThrows(NotInDatabaseException.class, () -> {data.getName(new PointNode(15, 40));});
	}

	@Test
	void getPointTest() throws NotInDatabaseException {
		PointNodeDatabase data = new PointNodeDatabase();
		PointNode node = new PointNode("A", 2, 5);
		data.put(node);

		assertEquals(node, data.getPoint(2.0, 5.0));
		assertEquals(node, data.getPoint(node));
		assertThrows(NotInDatabaseException.class, () -> {data.getPoint(3.0, 9.0);});
		PointNode node2 = new PointNode("B", 6, 5);
		assertThrows(NotInDatabaseException.class, () -> {data.getPoint(node2);});
	}
	
	@Test
	void testUnparse() {
		PointNodeDatabase database = new PointNodeDatabase();
		StringBuilder sb = new StringBuilder();
		database.unparse(sb, 0);
		System.out.println(sb);
		
		database.put(new PointNode("A", 1.0, 2.0));
		database.put(new PointNode("B", 3.0, 4.0));
		database.put(new PointNode("C", 5.0, 6.0));
		database.put(new PointNode("D", 8.0, 1.0));
		
		sb = new StringBuilder();
		database.unparse(sb, 0);
		System.out.println(sb);
		
		sb = new StringBuilder();
		database.unparse(sb, 1);
		System.out.println(sb);
		
		sb = new StringBuilder();
		database.unparse(sb, 3);
		System.out.println(sb);
	}
}
