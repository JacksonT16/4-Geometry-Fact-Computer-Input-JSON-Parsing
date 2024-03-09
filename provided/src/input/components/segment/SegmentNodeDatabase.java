package input.components.segment;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import input.components.ComponentNode;
import input.components.point.PointNode;
import utilities.io.StringUtilities;

/**
 * Database base of segment nodes
 * @author Jackson Tedesco, Case Riddle
 * @date 2/20/2024
 */
public class SegmentNodeDatabase implements ComponentNode{
	private Map<PointNode, Set<PointNode>> _adjLists;

	public SegmentNodeDatabase()
	{
		_adjLists = new LinkedHashMap<>();
	}

	/**
	 * creates a new SegmentNodeDatabase out of another map
	 * @param MySegmentNode: the pre-made map
	 */
	public SegmentNodeDatabase(Map<PointNode , Set<PointNode>> mySegmentNode)
	{
		_adjLists = new LinkedHashMap<>(mySegmentNode);
	}

	/**
	 * @return number of unique edges
	 */
	public int numUndirectedEdges()
	{	
		return asUniqueSegmentList().size();
	}

	/**
	 * adds a undirected edge
	 * @param b: one point of edge
	 * @param x: other point of edge
	 */
	public void addUndirectedEdge(PointNode b, PointNode x) throws NullPointerException
	{
		if(b == null || x == null) throw new NullPointerException();

		addDirectedEdge(b, x);
		addDirectedEdge(x, b);
	}

	/**
	 * adds multiple edges at once   
	 * @param b: origin point 
	 * @param MyList: list of other points
	 */
	public void addAdjacencyList(PointNode b, List<PointNode> MyList )
	{
		for(PointNode node: MyList) {
			addUndirectedEdge(b, node);
		}
	}

	/**
	 * @return List of all the segment in the database
	 */
	public List <SegmentNode> asSegmentList()
	{
		Set<PointNode> keys = _adjLists.keySet();
		List <SegmentNode> segments = new LinkedList<>();

		for(PointNode key: keys) {
			for(PointNode value: _adjLists.get(key)) {
				segments.add(new SegmentNode(key, value));
			}
		}

		return segments;
	}

	/**
	 * @return List of all the unique segment in the database
	 */
	public List <SegmentNode> asUniqueSegmentList() 
	{
		List <SegmentNode> allSegments = asSegmentList();
		List <SegmentNode> uniqueSegments = new LinkedList<>();

		for(int i = 0; i < allSegments.size(); i++) {
			if(!uniqueSegments.contains(allSegments.get(i))) {
				uniqueSegments.add(allSegments.get(i));
			}
		}

		return uniqueSegments;
	}

	/**
	 * Creates an key and associated values; if key add value to the set associated with the key    
	 * @param Key
	 * @param Value
	 */
	private void addDirectedEdge(PointNode Key, PointNode value) {
		if(Key.equals(value)) return;

		if(_adjLists.containsKey(Key)) {
			_adjLists.get(Key).add(value);
		}else {
			_adjLists.put(Key, new LinkedHashSet<PointNode>(Arrays.asList(value)));
		}
	}

	/**
	 * Utilizes two for-each loops to iterate through a string and complete our format.
	 * @param sb: String builder that is added too.
	 * @param level: level of indentation 
	 */
	@Override
	public void unparse(StringBuilder sb, int level) throws IllegalArgumentException{
		if(level < 0) throw new IllegalArgumentException("level is negtive number");

		Set<PointNode> keys = _adjLists.keySet();

		for(PointNode key: keys) {
			
			sb.append(StringUtilities.indent(level));
			sb.append(key.getName() +": ");

			for(PointNode value: _adjLists.get(key)) {
				sb.append(value.getName() + " ");
			}

			sb.append("\n");
		}
	}
}
