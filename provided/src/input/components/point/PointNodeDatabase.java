package input.components.point;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Methods that facilitate the PointNode.
 * @date 1/23/2024
 * @author Case Riddle
 * @author Tony Song
 * @author Jackson Tedesco
 **/

public class PointNodeDatabase {
	private Set<PointNode> _points;

	public PointNodeDatabase() {
		_points = new LinkedHashSet<>();
	}
	
	/**
	 * adds a list of PointNodes
	 * @param pointsNodes list
	 */
	public PointNodeDatabase(List<PointNode> points) {
		_points = new LinkedHashSet<>(points);
	}
	
	/**
	 * add point to database
	 * @param point added
	 */
	public void put(PointNode point) {
		_points.add(point);
	}
	
	/**
	 * @param point being tested
	 * @return true if PointNode is in database; false otherwise
	 */
	public boolean contains(PointNode point) {
		return _points.contains(point);
	}
	
	/**
	 * @param x coordinate of a PointNode
	 * @param y	coordinate of a PointNode
	 * @return true if PointNode is in database; false otherwise
	 */
	public boolean contains(double x, double y) {
		
		return _points.contains(new PointNode(x, y));
	}

	/**
	 * Finds the stored point in _point and returns its name if it equals the specified point.
	 * @return the name of the PointNode
	 * @param point: PointNode to get name from
	 **/
	public String getName(PointNode point) throws NotInDatabaseException{
		for (PointNode storedPoint : _points) {
			if (storedPoint.equals(point)) {
				return storedPoint.getName();
			}
		}

		throw new NotInDatabaseException();
	}
	
	/**
	 * Finds the stored point in _point though coordinates and returns its name if it equals the specified point.
	 * @param x coordinate 
	 * @param y	coordinate
	 * @return the name of the PointNode
	 */
	public String getName(double x, double y) throws NotInDatabaseException{
        return getName(new PointNode(x, y));
	}

	/**
	 * Will find the stored point in _point and return if it equals the specified point.
	 * @return the PointNode
	 * @param point: PointNode to be found
	 **/
	public PointNode getPoint(PointNode point) throws NotInDatabaseException{
		for (PointNode storedPoint : _points) {
			if (storedPoint.equals(point)) return storedPoint;
		}
		
		throw new NotInDatabaseException();
	}

	/**
	 * Finds the element in _point and returns if it equals the intended element.
	 * @param x coordinate 
	 * @param y	coordinate
	 * @return the PointNode
	 * @throws NotInDatabase 
	 **/
	public PointNode getPoint(String name) throws NotInDatabaseException {
		for (PointNode storedPoint : _points) {
			if (storedPoint.getName().equals(name)) return storedPoint;
		}
		
		throw new NotInDatabaseException();
	}
	
	
	public void unparse(StringBuilder sb, int level) {
	    for (PointNode point : _points) {
	        point.unparse(sb, level);
	    }
	}
}