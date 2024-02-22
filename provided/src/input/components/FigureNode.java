package input.components;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;

/**
 * A basic figure consists of points, segments, and an optional description
 * Each figure has distinct points and segments (thus unique database objects).
 * @author Jackson Tedesco, Case Riddle
 * @date 2/20/2024
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

	/**
	 * Recursively creates a format that matches the provided JSON format.
	 * @param sb: String builder that the unparse adds too
	 * @param level: the level of indentation 
	 * @exception IllegalArgumentException
	 */
	@Override
    public void unparse(StringBuilder sb, int level) throws IllegalArgumentException{
		if(level < 0) throw new IllegalArgumentException("level is negtive number");
		
		appendIndented(sb, level, "Figure:");
		++level;
		
		appendIndented(sb, level, "Description : \"" + _description + "\"");
		
		//adds points to point node database
        appendIndented(sb, level, "Points:");
        appendIndented(sb, level, "{");
        _points.unparse(sb, level + 1);
        appendIndented(sb, level, "}");
        
        //adds segments to segmentNodeDataBase
        appendIndented(sb, level, "Segments:");
        appendIndented(sb, level, "{");
        _segments.unparse(sb, level + 1);
        appendIndented(sb, level, "}");
    }
	
	/**
	 * indents string to proper indentation and add string to string builder
	 * @param sb: String builder that is added too
	 * @param level: level of indentation
	 * @param content: string that is added after indentation
	 */
    private void appendIndented(StringBuilder sb, int level, String content) {
        for (int i = 0; i < level; i++) {
            sb.append("\t");
        }
        sb.append(content + "\n");
    }
}