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
 * This class will read a JSON data file and create an abstract syntax tree structure 
	for a geometry figure in the form of a figureNode
 * @author Jackson Tedesco, Case Riddle
 * @date 2/15/2024
 */
public class JSONParser
{
	protected ComponentNode  _astRoot;

	public JSONParser()
	{
		_astRoot = null;
	}

	private void error(String message)
	{
		throw new ParseException("Parse error: " + message);
	}

	/**
	 * creates an FigureNode out of the inputed JSON file
	 * @param str: JSON file file in the form of a string
	 * @return abstract syntax tree structure 
		for a geometry figure in the form of a figureNode
	 * @throws ParseException
	 * @throws JSONException
	 * @throws NotInDatabaseException
	 */
	public ComponentNode parse(String str) throws ParseException, JSONException, NotInDatabaseException
	{
		if(str.equals("{}")) error("JSON is empty");// 

		// Parsing is accomplished via the JSONTokenizer class.
		JSONTokener tokenizer = new JSONTokener(str);
		JSONObject  JSONroot = ((JSONObject)tokenizer.nextValue()).getJSONObject("Figure");

		String description = getDescription(JSONroot);
		PointNodeDatabase points = getPoints(JSONroot);
		SegmentNodeDatabase segments = getSegmentDatabase(JSONroot.getJSONArray("Segments"), points);

		_astRoot = new FigureNode(description, points, segments);
		return _astRoot;
	}

	/**
	 * @param JSONroot: The JSONObject
	 * @return Description from inputed JSON object
	 */
	private String getDescription(JSONObject JSONroot) {
		return  JSONroot.getString("Description");
	}

	/**
	 * @param JSONroot: The JSONObject
	 * @return PointNodeDatabase from the JSONObject
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
	 * @param node: JSONObject for a singular node
	 * @return a completed point node from the JSONObject data
	 */
	private PointNode getPoint(JSONObject node) {
		String name = node.getString("name");
		double x = node.getInt("x");
		double y = node.getInt("y");

		return new PointNode(name, x, y);
	}

	/**
	 * @param segmentList: JSONOArray of the overall segments list
	 * @param points: PointNodeDatabase that stores the points that will make up the segments
	 * @return a completed SegmentNodeDatabase from the JSONObject data
	 * @throws NotInDatabaseException 
	 * @throws JSONException 
	 * @throws NullPointerException 
	 */
	private SegmentNodeDatabase getSegmentDatabase(JSONArray segmentList, PointNodeDatabase points) 
			throws NullPointerException, JSONException, NotInDatabaseException {

		SegmentNodeDatabase outPut = new SegmentNodeDatabase();

		for(int i = 0; i < segmentList.length(); i++) {
			JSONObject subList = segmentList.getJSONObject(i);
			getSegment(subList, outPut, points);
		}

		return outPut;
	}

	/**
	 * Creates segments from the PointNode JSONArray from the 
	 * JSONObject segment and adds them to inputed SegmentNodeDatabase.
	 * @param segment: Object that value stores the JSONArray of points
	 * @param output: The SegmentNodeDatabase that the created segments are added too
	 * @param points: PointNodeDatabase that stores the points that will make up the segment
	 * @throws NotInDatabaseException 
	 * @throws JSONException 
	 * @throws NullPointerException 
	 */
	private void getSegment(JSONObject segmentList, SegmentNodeDatabase output, PointNodeDatabase points) 
			throws NullPointerException, JSONException, NotInDatabaseException {

		String headNode = segmentList.toString().substring(2, 3);
		JSONArray segmentPoints = segmentList.getJSONArray(headNode);

		for(int i = 0; i < segmentPoints.length(); i++) {
			output.addUndirectedEdge(points.getPoint(headNode), points.getPoint(segmentPoints.getString(i)));
		}
	}
}