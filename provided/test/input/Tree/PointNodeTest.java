package input.Tree;
import input.components.point.PointNode;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test pointNode
 * @date 2/22/2024
 * @author Jackson Tedesco
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
		assertEquals("__UNNAMED(1.0, 2.0)", myPointNode.toString());

		myPointNode= new PointNode("joe", 1,2);
		assertEquals("joe(1.0, 2.0)", myPointNode.toString());
	}

	@Test
	void testUnparse() {
		StringBuilder result1 = new StringBuilder();
		PointNode node = new PointNode("A", 2, 3);	
		node.unparse(result1, 0);
		System.out.println(result1.toString());

		node = new PointNode(2, 3);
		result1 = new StringBuilder();
		node.unparse(result1, 0);
		System.out.println(result1);

		node = new PointNode(2.69, 3.56);
		result1 = new StringBuilder();
		node.unparse(result1, 0);
		System.out.println(result1);

		node = new PointNode(2, 3);
		result1 = new StringBuilder();
		node.unparse(result1, 1);
		System.out.println(result1);

		result1 = new StringBuilder();
		node.unparse(result1, 3);
		System.out.println(result1);

		assertThrows(IllegalArgumentException.class, () -> {
			PointNode eNode = new PointNode(2, 6);
			StringBuilder result2 = new StringBuilder();
			eNode.unparse(result2, -1);
		});
	}
}