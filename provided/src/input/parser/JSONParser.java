package input.parser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import input.components.ComponentNode;
import input.components.*;
import input.components.point.NotInDatabaseException;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;
import input.exception.ParseException;

/**
 * 
 */
public class JSONParser
{
	protected ComponentNode  _astRoot;
	
	//think about using Constants
	// think about possable exception
	public JSONParser()
	{
		_astRoot = null;
	}

	private void error(String message)
	{
		throw new ParseException("Parse error: " + message);// where to use this
	}

	public ComponentNode parse(String str) throws ParseException, JSONException, NotInDatabaseException
	{
		// Parsing is accomplished via the JSONTokenizer class.
		if(str.equals("{}")) {
			error("JSON is empty");
		}
		
		JSONTokener tokenizer = new JSONTokener(str);
		JSONObject  JSONroot = (JSONObject)tokenizer.nextValue();
		JSONroot = JSONroot.getJSONObject("Figure");

		String description = getDescription(JSONroot);
		PointNodeDatabase points = getPoints(JSONroot);
		SegmentNodeDatabase segments = getSegmentNodeDatabase(JSONroot.getJSONArray("Segments")
				, points);

		_astRoot = new FigureNode(description, points, segments);
		return _astRoot;
	}

	/**
	 * @param str
	 * @param JSONroot
	 * @return
	 */
	private String getDescription(JSONObject JSONroot) {
		return  JSONroot.getString("Description");
	}

	/**
	 * 
	 * @return
	 */
	private PointNodeDatabase getPoints(JSONObject JSONroot) {
		PointNodeDatabase points = new PointNodeDatabase();

		JSONArray pointlist = JSONroot.getJSONArray("Points");
		for(int i = 0; i < pointlist.length(); i++) {
			points.put(getPoint(pointlist.getJSONObject(i)));
		}

		return points;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private PointNode getPoint(JSONObject node) {
		String name = node.getString("name");
		double x = node.getInt("x");
		double y = node.getInt("y");

		return new PointNode(name, x, y);
	}

	/**
	 * @throws NotInDatabaseException 
	 * @throws JSONException 
	 * @throws NullPointerException 
	 * 
	 */
	private SegmentNodeDatabase getSegmentNodeDatabase(JSONArray segmentlist, PointNodeDatabase points) 
			throws NullPointerException, JSONException, NotInDatabaseException {
		SegmentNodeDatabase segments = new SegmentNodeDatabase();
		
		for(int i = 0; i < segmentlist.length(); i++) {
			JSONObject subList = segmentlist.getJSONObject(i);
			
			getSegment(subList, segments, subList.toString().substring(2, 3), points);
		}
		
		return segments;
	}
	
	/**
	 * 
	 * @return
	 * @throws NotInDatabaseException 
	 * @throws JSONException 
	 * @throws NullPointerException 
	 */
	private void getSegment(JSONObject segment, SegmentNodeDatabase output, String headNode, PointNodeDatabase points) throws NullPointerException, JSONException, NotInDatabaseException {
		JSONArray segmentPoints = segment.getJSONArray(headNode);
		
		for(int i = 0; i < segmentPoints.length(); i++) {
			output.addUndirectedEdge(points.getPoint(headNode), points.getPoint(segmentPoints.getString(i)));
		}
	}
}
