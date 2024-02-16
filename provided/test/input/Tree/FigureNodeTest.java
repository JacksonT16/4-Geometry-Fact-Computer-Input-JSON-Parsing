import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FigureNodeTest {

    @Test
    void unparseTest() {
        PointNodeDatabase pointsDatabase = new PointNodeDatabase();
        pointsDatabase.put(new PointNode("A", 1.0, 2.0));
        pointsDatabase.put(new PointNode("B", 3.0, 4.0));

        SegmentNodeDatabase segmentDatabase = new SegmentNodeDatabase();
        segmentDatabase.addUndirectedEdge(new PointNode("A", 1.0, 2.0), new PointNode("B", 3.0, 4.0));

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