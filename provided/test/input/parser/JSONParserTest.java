package input.parser;
import org.json.JSONException;
import input.components.point.NotInDatabaseException;


import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import input.components.ComponentNode;
import input.components.FigureNode;
import input.exception.ParseException;

class JSONParserTest
{
	public static ComponentNode runFigureParseTest(String filename) throws ParseException, JSONException, NotInDatabaseException
	{
		JSONParser parser = new JSONParser();

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		return parser.parse(figureStr);
	}

	@Test
	void empty_json_string_test()
	{
		JSONParser parser = new JSONParser();

		assertThrows(ParseException.class, () -> { parser.parse("{}"); });
	}

	@Test
	void single_triangle_test() throws ParseException, JSONException, NotInDatabaseException
	{
		//
		// The input String ("single_triangle.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/single_triangle.json"
		//
		ComponentNode node = JSONParserTest.runFigureParseTest("single_triangle.json");

		assertTrue(node instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void collinear_line_segments_test() throws ParseException, JSONException, NotInDatabaseException
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("collinear_line_segments.json");

		assertTrue(node instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void crossing_symmetric_triangle_test() throws ParseException, JSONException, NotInDatabaseException
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("crossing_symmetric_triangle.json");

		assertTrue(node instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void fully_connected_irregular_polygon_test() throws ParseException, JSONException, NotInDatabaseException
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("fully_connected_irregular_polygon.json");

		assertTrue(node instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void square_four_interior_triangles_test() throws ParseException, JSONException, NotInDatabaseException
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("square_four_interior_triangle.json");

		assertTrue(node instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void four_point_star_test() throws ParseException, JSONException, NotInDatabaseException
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("four_point_star.json");

		assertTrue(node instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void traingle_with_three_triangles_inside_test() throws ParseException, JSONException, NotInDatabaseException
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("triangle_with_three_triangles_inside.json");

		assertTrue(node instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void arrow_pointing_right_test() throws ParseException, JSONException, NotInDatabaseException
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("arrow_pointing_right.json");

		assertTrue(node instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void grid_test() throws ParseException, JSONException, NotInDatabaseException
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("grid.json");

		assertTrue(node instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void two_separate_triangles_test() throws ParseException, JSONException, NotInDatabaseException
	{
		//
		// The input String ("single_triangle.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/single_triangle.json"
		//
		ComponentNode node = JSONParserTest.runFigureParseTest("two_separate_triangles.json");

		assertTrue(node instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
}
