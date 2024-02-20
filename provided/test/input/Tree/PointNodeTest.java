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
		List<PointNode> points = new ArrayList<>();
		points.add(new PointNode("Point1", 1.0, 2.0));
		points.add(new PointNode("Point2", 3.0, 4.0));
		points.add(new PointNode("Point3", 5.0, 6.0));
		PointNodeDatabase database = new PointNodeDatabase(points);

		StringBuilder sb = new StringBuilder();

		database.unparse(sb, 0);

		String expectedOutput = "Point1: (1.0, 2.0)" +
				"Point2: (3.0, 4.0)" +
				"Point3: (5.0, 6.0)";

		assertEquals(expectedOutput, sb.toString());
	}

}
