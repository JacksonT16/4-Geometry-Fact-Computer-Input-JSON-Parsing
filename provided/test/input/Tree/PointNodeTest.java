package input.Tree;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Test pointNode
 * @date 1/22/2024
 * @author Jackson Tedesco
 * @author Tony Song
 * @author Case Riddle
 */
class PointNodeTest {
	@Test
	void testEquals() {
		PointNode myPointNode= new PointNode(1,2);
		PointNode mySecondPointNode= new PointNode(1,2);

		assertTrue(myPointNode.equals(mySecondPointNode));
		mySecondPointNode= new PointNode(3,2);
		assertFalse(myPointNode.equals(mySecondPointNode));

		myPointNode = new PointNode(0.99999999999,0.999999999999);
		mySecondPointNode = new PointNode(1,1);
		assertTrue(myPointNode.equals(mySecondPointNode));
	}

	@Test
	void testToString() {
		PointNode myPointNode= new PointNode(1,2);
		assertEquals(("__UNNAMED(1.0, 2.0)"), myPointNode.toString());

		myPointNode= new PointNode("joe", 1,2);
		assertEquals(("joe(1.0, 2.0)"), myPointNode.toString());
	}

	@Test
	void testUnparse() {
		List<PointNode> p1 = new ArrayList<>();
		p1.add(new PointNode("Point1", 1.0, 2.0));
		p1.add(new PointNode("Point2", 3.0, 4.0));
		p1.add(new PointNode("Point3", 5.0, 6.0));
		PointNodeDatabase d1 = new PointNodeDatabase(p1);

		StringBuilder s1 = new StringBuilder();

		d1.unparse(s1, 0);

		String e1 = "Point1: (1.0, 2.0)" +
				"Point2: (3.0, 4.0)" +
				"Point3: (5.0, 6.0)";

		assertEquals(e1, s1.toString());

		List<PointNode> p2 = new ArrayList<>();
		p2.add(new PointNode("Point1", 0.0, 0.0));
		p2.add(new PointNode("Point2", 3.0, 4.0));
		p2.add(new PointNode("Point3", 5.0, 6.0));
		PointNodeDatabase d2 = new PointNodeDatabase(p2);

		StringBuilder s2 = new StringBuilder();

		d2.unparse(s2, 0);

		String e2 = "Point1: (0.0, 1.0)" +
				"Point3: [3.0, 4.0]" +
				"Point2: (5.0, 6.0)";

		assertNotEquals(e2, s2.toString());
	}

}
