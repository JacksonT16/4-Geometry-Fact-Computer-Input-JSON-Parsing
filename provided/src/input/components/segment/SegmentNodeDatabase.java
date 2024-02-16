package input.components.segment;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import input.components.point.PointNode;

/**
 * Database base of segment nodes
 * @author Jackson Tedesco
 * @author	Tony Song
 * @author Case Riddle
 * @date 1/23/2024
 */
public class SegmentNodeDatabase {
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

	public void unparse(StringBuilder sb, int level) {
		List<SegmentNode> uniqueSegments = asUniqueSegmentList();
		//SegmentNodeDatabase s1 = new SegmentNodeDatabase();

		Set<PointNode> keys = _adjLists.keySet();

		for(PointNode key: keys) {
			sb.append(key.getName() +": ");
			for(PointNode value: _adjLists.get(key)) {
				sb.append(value.getName() + " ");
			}
			sb.append("\n");
		}

		/*for (SegmentNode s2 : s1) {
	    	sb.append(s2 +": ");
	    	for(PointNode value: _adjLists.get(s2)) {
	    		sb.append(value + " ");
	    	}
	    	sb.append("\n");*/
		//  segment.unparse(sb, level);
	}
}
