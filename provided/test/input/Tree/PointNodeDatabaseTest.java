package input.components.point;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Jackson Tedesco
 * @author Case Riddle
 * @date 1/23/2024
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

		assertThrows(NotInDatabasException.class, () -> {data.getName(15.0, 40.0);});
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
	void getPointFromNameTest() throws NotInDatabaseException {
		PointNodeDatabase data = new PointNodeDatabase();
		assertThrows(NotInDatabaseException.class, () -> {data.getPoint("F");});

		PointNode node1 = new PointNode("A", 2, 5);
		PointNode node2 = new PointNode("B", 4, 5);
		PointNode node3 = new PointNode("C", 2, 9); 
		PointNode node4 = new PointNode("D", 5, 9);
		PointNode node5 = new PointNode("D", 8, 12);

		data.put(node3);
		data.put(node2);
		data.put(node4);
		data.put(node1);
		data.put(node5);

		assertEquals(node1, data.getPoint("A"));
		assertEquals(node2, data.getPoint("B"));
		assertEquals(node3, data.getPoint("C"));
		assertEquals(node4, data.getPoint("D"));

		assertEquals(5, data.getPoint("D").getX());
		assertEquals(9, data.getPoint("D").getY());

		assertThrows(NotInDatabaseException.class, () -> {data.getPoint("F");});
	}

	@Test
	void testUnparse() {
		List<PointNode> points = new ArrayList<>();
		points.put(new PointNode(1.0, 2.0, "Point1"));
		points.put(new PointNode(3.0, 4.0, "Point2"));
		points.put(new PointNode(5.0, 6.0, "Point3"));
		PointNodeDatabase database = new PointNodeDatabase(points);

		StringBuilder sb = new StringBuilder();

		database.unparse(sb, 0);

		String expectedOutput = "Point1: (1.0, 2.0)" +
				"Point2: (3.0, 4.0)" +
				"Point3: (5.0, 6.0)";

		assertEquals(expectedOutput, sb.toString());
	}
}

