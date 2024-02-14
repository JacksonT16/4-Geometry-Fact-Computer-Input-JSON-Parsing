package input.components;

import java.util.Set;

import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;

/**
 * A basic figure consists of points, segments, and an optional description
 * 
 * Each figure has distinct points and segments (thus unique database objects).
 * 
 */
public class FigureNode implements ComponentNode
{
	protected String              _description;
	protected PointNodeDatabase   _points;
	protected SegmentNodeDatabase _segments;

	public String              getDescription()    { return _description; }
	public PointNodeDatabase   getPointsDatabase() { return _points; }
	public SegmentNodeDatabase getSegments()       { return _segments; }
	
	public FigureNode(String description, PointNodeDatabase points, SegmentNodeDatabase segments)
	{
		_description = description;
		_points = points;
		_segments = segments;
	}

	@Override
    public void unparse(StringBuilder sb, int level) {
        appendIndented(sb, level, "Description : \"" + _description + "\"");

        appendIndented(sb, level, "Points:");
        _points.unparse(sb, level + 1);

        appendIndented(sb, level, "Segments:");
        _segments.unparse(sb, level + 1);
    }

    private void appendIndented(StringBuilder sb, int level, String content) {
        for (int i = 0; i < level; i++) {
            sb.append("    ");
        }
        sb.append(content);
        sb.append("\n");
    }
}
}