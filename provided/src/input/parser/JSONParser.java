package input.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import input.components.ComponentNode;
import input.components.*;
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

	public ComponentNode parse(String str) throws ParseException
	{
		// Parsing is accomplished via the JSONTokenizer class.
		JSONTokener tokenizer = new JSONTokener(str);
		JSONObject  JSONroot = (JSONObject)tokenizer.nextValue();

		String description = JSONroot.getString("Description");
		PointNodeDatabase points = new PointNodeDatabase();
		SegmentNodeDatabase segments = new SegmentNodeDatabase();
		
		//ArrayList<PointNode> pointlist = JSONroot.getJSONArray("Points")
		
		for(PointNode point:JSONroot.getJSONArray("Points")) {
			String name = JSONroot.getString("name");
			double x = JSONroot.getInt("x");
			double y = JSONroot.getInt("y");
			
			points.put(new PointNode(name, x, y));
		}
		
		for(Object Segment:JSONroot.getJSONArray("Segments")) {
			segments.addUndirectedEdge(new SegmentNode());
		}
		
		
		
		
		/*while(tokenizer.more()) {
			String next = tokenizer.nextString('"');

			if(next.equals("Description")) {
				description = tokenizer.nextString('"');
			}

			if(next.equals("Points")) {
				String name = "";
				double x = 0;
				double y = 0;

				if(tokenizer.nextString('"').equals("name")) {
					name = tokenizer.nextString('"');
				}
				if(tokenizer.nextString('"').equals("x")){
					x = (double) tokenizer.nextValue();//test to make sure this does not get the ":"
				}
				if(tokenizer.nextString('"').equals("y")){
					y = (double) tokenizer.nextValue();// test to make sure this does not get the ":"
				}
				
				points.put(new PointNode(name, x, y));
			}
			
			if(next.equals("Segments")) {
				
			}
		}*/
		// TODO: Build the whole AST, check for return class object, and return the root
	}

	// TODO: implement supporting functionality

}