
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;

package input.Tree;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;

class FigureNodeTest {

    @Test
    void unparseTest() {
        PointNodeDatabase pointsDatabase = new PointNodeDatabase();
        pointsDatabase.put(new PointNode("A", 1.0, 2.0));
        pointsDatabase.put(new PointNode("B", 3.0, 4.0));

        SegmentNodeDatabase segmentDatabase = new SegmentNodeDatabase();
        segmentDatabase.addUndirectedEdge(new PointNode("A", 1.0, 2.0), new PointNode("B", 3.0, 4.0));
    }
	@Test
	void unparseTest() {
		
		/*String s = "hi joe";
		PointNodeDatabase point = new PointNodeDatabase();
		PointNode node1 = new PointNode("A", 4, 7);
		PointNode node2 = new PointNode("B", 4, 7);
		PointNode node3 = new PointNode("C", 4, 7);
		point.put(node1);
		point.put(node2);
		point.put(node3);
		
		SegmentNodeDatabase segment = new SegmentNodeDatabase();
		segment.addUndirectedEdge(node1, node2); 
		segment.addUndirectedEdge(node2, node3);
		segment.addUndirectedEdge(node1, node3);
		
		FigureNode node = new FigureNode(s, point, segment);
		StringBuilder st = new StringBuilder();
		System.out.(node.unparse(st, 0));*/
	}
	
        FigureNode figure = new FigureNode("Sample Figure", pointsDatabase, segmentDatabase);

        StringBuilder result = new StringBuilder();

        figure.unparse(result, 0);

        String expectedOutput = "Description : \"Sample Figure\"\n" +
                                "Points:\n" +
                                "    Point(A)(1.0, 2.0)\n" +
                                "    Point(B)(3.0, 4.0)\n" +
                                "Segments:\n" +
                                "    Segment(A, B)\n";

        assertEquals(expectedOutput, result.toString());
    }
}