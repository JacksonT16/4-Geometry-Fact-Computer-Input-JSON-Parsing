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

	public ComponentNode parse(String str) throws ParseException, JSONException, NotInDatabaseException
	{
		// Parsing is accomplished via the JSONTokenizer class.
		JSONTokener tokenizer = new JSONTokener(str);
		JSONObject  JSONroot = (JSONObject)tokenizer.nextValue();

		String description = JSONroot.getString("Description");
		PointNodeDatabase points = new PointNodeDatabase();
		SegmentNodeDatabase segments = new SegmentNodeDatabase();
		
		JSONArray pointlist = JSONroot.getJSONArray("Points");
		PointNode prevNode = null;
		for(int i = 0; i < pointlist.length(); i++) {
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

		return new FigureNode(description, points, segments);

		// TODO: Build the whole AST, check for return class object, and return the root
	}

	// TODO: implement supporting functionality

}