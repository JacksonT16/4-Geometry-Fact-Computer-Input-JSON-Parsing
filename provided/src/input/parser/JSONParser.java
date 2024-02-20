package input.parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	//think about using Constants
	// think about possable exception

	public JSONParser()
	{
		_astRoot = null;
	}

	private void error(String message)
	{
		throw new ParseException("Parse error: " + message);// where to use this????????
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
		// Parsing is accomplished via the JSONTokenizer class.
		if(str.equals("{}")) {
			error("JSON is empty");
		}
		
		JSONTokener tokenizer = new JSONTokener(str);
		JSONObject  JSONroot = ((JSONObject)tokenizer.nextValue()).getJSONObject("Figure");//?
		//JSONroot = JSONroot.getJSONObject("Figure");

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

		SegmentNodeDatabase segments = new SegmentNodeDatabase();
		
		JSONArray pointlist = JSONroot.getJSONArray("Points");
		PointNode prevNode = null;
		for(int i = 0; i < pointlist.length(); i++) {
			points.put(getPoint(pointlist.getJSONObject(i)));
			String name = pointlist.getJSONObject(i).getString("name");
			double x = pointlist.getJSONObject(i).getInt("x");
			double y = pointlist.getJSONObject(i).getInt("y");
			
			PointNode tempNode = new PointNode(name, x, y);
			points.put(tempNode);
			
			if(prevNode != null) {
				segments.addUndirectedEdge(tempNode, prevNode);
			}else {
				prevNode = tempNode;
			}	
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
		// TODO: Build the whole AST, check for return class object, and return the root
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
		SegmentNodeDatabase segments = new SegmentNodeDatabase();
		
		for(int i = 0; i < segmentList.length(); i++) {
			JSONObject subList = segmentList.getJSONObject(i);
			
			getSegment(subList, segments, subList.toString().substring(2, 3), points);
		}
		
		return segments;
	}
	
	/**
	 * Creates segments from the PointNode JSONArray from the JSONObject segment and adds them to 
	 * inputed SegmentNodeDatabase.
	 * @param segment: Object that value stores the JSONArray of points
	 * @param output: The SegmentNodeDatabase that the created segments are added to0
	 * @param headNode: The string version of the key of the object. Used as one point on all segments
	 * @param points: PointNodeDatabase that stores the points that will make up the segment
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