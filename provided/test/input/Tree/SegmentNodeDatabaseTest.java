import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;

package input.components.segment;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import input.components.point.PointNode;
package.input.tree;
/**
 * 
 */
class SegmentNodeDatabaseTest
{
	public SegmentNodeDatabase build()
	{
		//      A                                 
		//     / \                                
		//    B___C                               
		//   / \ / \                              
		//  /   X   \ 
		// D_________E
		//
		//
		PointNode a = new PointNode("A", 3, 6);
		PointNode b = new PointNode("B", 2, 4);
		PointNode c = new PointNode("C", 4, 4);

		PointNode d = new PointNode("D", 0, 0);
		PointNode e = new PointNode("E", 6, 0);
		PointNode x = new PointNode("X", 3, 3);

		SegmentNodeDatabase db = new SegmentNodeDatabase();

		db.addUndirectedEdge(a, b);
		db.addUndirectedEdge(a, c);
		db.addUndirectedEdge(b, c);
		db.addUndirectedEdge(b, x);
		db.addUndirectedEdge(b, d);
		db.addUndirectedEdge(c, x);
		db.addUndirectedEdge(c, e);
		db.addUndirectedEdge(x, d);
		db.addUndirectedEdge(x, e);
		db.addUndirectedEdge(d, e);

		return db;
	}

	@Test
	void testConstructors()
	{

		SegmentNodeDatabase blank = new SegmentNodeDatabase();
		assertEquals(0,blank.numUndirectedEdges());

	}

	@Test
	void testNumUndirectedEdges()
	{
		SegmentNodeDatabase db = build();

		assertEquals(10, db.numUndirectedEdges());
	}

	@Test
	void exceptionTest() {
		SegmentNodeDatabase db = new SegmentNodeDatabase();
		assertThrows(NullPointerException.class, () -> {db.addUndirectedEdge(null, null);});


	}

	@Test
	void addAdjacencyListTest() {
		SegmentNodeDatabase db = new SegmentNodeDatabase();

		PointNode a = new PointNode("A", 3, 6);
		PointNode b = new PointNode("B", 2, 4);
		PointNode x = new PointNode("X", 3, 3);

		List<PointNode> list = Arrays.asList(b, x);

		db.addAdjacencyList(a, list);
		List<SegmentNode> list1 = db.asSegmentList();
		assertEquals(4, list1.size());
		assertTrue(list1.contains(new SegmentNode(a, b)));
		assertTrue(list1.contains(new SegmentNode(a, x)));

		PointNode c = new PointNode("C", 4, 4);
		PointNode d = new PointNode("D", 0, 0);

		List<PointNode> list2 = Arrays.asList(c, d);
		db.addAdjacencyList(b, list2);

		List<SegmentNode> list3 = db.asUniqueSegmentList();

		//Checks if all unique segments are represented correctly.
		assertEquals(4, list3.size());
		assertTrue(list3.contains(new SegmentNode(a, b)));
		assertTrue(list3.contains(new SegmentNode(a, x)));
		assertTrue(list3.contains(new SegmentNode(b, c)));
		assertTrue(list3.contains(new SegmentNode(b, d)));
	}

	@Test
	void asSegmentListAndAddTest() {
		SegmentNodeDatabase db = new SegmentNodeDatabase();
		PointNode a = new PointNode("A", 1, 2);
		PointNode b = new PointNode("B", 1, 4);
		PointNode c = new PointNode("C", 4, 4);
		PointNode d = new PointNode("D", 4, 2);

		SegmentNode _a = new SegmentNode(a,b);
		SegmentNode _b = new SegmentNode(c,d);
		SegmentNode _c = new SegmentNode(b,c);
		SegmentNode _d = new SegmentNode(a,d);
		SegmentNode _e = new SegmentNode(b,a);
		SegmentNode _f = new SegmentNode(d,c);
		SegmentNode _g = new SegmentNode(c,b);
		SegmentNode _h = new SegmentNode(d,a);
		List<SegmentNode> test = new LinkedList<>(Arrays.asList(_a, _d, _e, _c, _b,  _g, _f, _h));

		db.addUndirectedEdge(a, b);
		db.addUndirectedEdge(c, d);
		db.addUndirectedEdge(b, c);
		db.addUndirectedEdge(a, d);	
		List<SegmentNode> SegmentList = db.asSegmentList();

		assertEquals(test, SegmentList);
	}

	@Test
	void asUniqueSegmentListTest() {
		SegmentNodeDatabase db = new SegmentNodeDatabase();
		PointNode a = new PointNode("A", 1, 2);
		PointNode b = new PointNode("B", 1, 4);
		PointNode c = new PointNode("C", 4, 4);
		PointNode d = new PointNode("D", 4, 2);
		PointNode e = new PointNode("E", 2, 4);


		SegmentNode _a = new SegmentNode(a,b);
		SegmentNode _b = new SegmentNode(c,d);
		SegmentNode _c = new SegmentNode(b,c);
		SegmentNode _d = new SegmentNode(a,d);
		SegmentNode _e = new SegmentNode(e,d);

		List<SegmentNode> test = new LinkedList<>(Arrays.asList(_a, _d, _c, _b,_e));

		db.addUndirectedEdge(a, b);
		db.addUndirectedEdge(c, d);
		db.addUndirectedEdge(b, c);
		db.addUndirectedEdge(a, d);	
		db.addUndirectedEdge(e, d);	

		List<SegmentNode> SegmentList = db.asUniqueSegmentList(); 	
		assertEquals(test, SegmentList);

		//System.out.print(db.asUniqueSegmentList());
	}
	
	@Test
	void unparse() {
		
	}

}
