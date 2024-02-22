package input.Tree;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;
/**
 * Test figure node
 * @author Jackson Tedesco, Case Riddle
 * @date 2/21/2024
 */
class FigureNodeTest {
	@Test
    void unparseTest() {
        PointNodeDatabase pointsDatabase = new PointNodeDatabase();
        PointNode a = new PointNode("A", 1.0, 3.0);
        PointNode b = new PointNode("B", 3.0, 3.0);
        PointNode c = new PointNode("C", 3.0, 1.0);
        PointNode d = new PointNode("D", 1.0, 1.0);
        
        pointsDatabase.put(a);
        pointsDatabase.put(b);
        pointsDatabase.put(c);
        pointsDatabase.put(d);

        SegmentNodeDatabase segmentDatabase = new SegmentNodeDatabase();
        segmentDatabase.addUndirectedEdge(b, a);
        segmentDatabase.addUndirectedEdge(b, c);
        segmentDatabase.addUndirectedEdge(c, d);
        segmentDatabase.addUndirectedEdge(d, a);

        FigureNode figure = new FigureNode("Sample Figure", pointsDatabase, segmentDatabase);
        StringBuilder result = new StringBuilder();

        figure.unparse(result, 0);
        System.out.println(result);
    }
	
	@Test
    void emptyUnparseTest() {
		PointNodeDatabase pointsDatabase = new PointNodeDatabase();
        SegmentNodeDatabase segmentDatabase = new SegmentNodeDatabase();

        FigureNode figure = new FigureNode("", pointsDatabase, segmentDatabase);
        StringBuilder result = new StringBuilder();

        figure.unparse(result, 0);
        System.out.println(result);
    }
	
	@Test
	void levelTest() {
        PointNodeDatabase pointsDatabase = new PointNodeDatabase();
        PointNode a = new PointNode("A", 1.0, 3.0);
        PointNode b = new PointNode("B", 3.0, 3.0);
        
        pointsDatabase.put(a);
        pointsDatabase.put(b);

        SegmentNodeDatabase segmentDatabase = new SegmentNodeDatabase();
        segmentDatabase.addUndirectedEdge(b, a);

        FigureNode figure = new FigureNode("Sample Figure", pointsDatabase, segmentDatabase);
        StringBuilder result = new StringBuilder();
        
        figure.unparse(result, 1);
        System.out.println(result);
        
        result = new StringBuilder();
        figure.unparse(result, 3);
        System.out.println(result);
        
        assertThrows(IllegalArgumentException.class, () -> {
			StringBuilder result2 = new StringBuilder();
			figure.unparse(result2, -1);
		});
    }
}