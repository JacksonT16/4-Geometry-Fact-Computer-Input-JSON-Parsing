package input.components.point;

import utilities.math.MathUtilities;

/**
 * A 2D Point (x, y).
 * @date 1/22/2024
 * @author Jackson Tedesco
 * @author Tony Song
 * @author Case Riddle
 */
public class PointNode
{
	protected static final String ANONYMOUS = "__UNNAMED";

	protected double _x;
	public double getX() { return this._x; }

	protected double _y; 
	public double getY() { return this._y; }

	protected String _name; 
	public String getName() { return _name; }

	/**
	 * Create a new Point with the specified coordinates.
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	public PointNode(double x, double y)
	{
			this(ANONYMOUS, x,y);
	}

	/**
	 * Create a new Point with the specified coordinates.
	 * @param name -- The name of the point. (Assigned by the UI)
	 * @param x -- The X coordinate
	 * @param y -- The Y coordinate
	 */
	public PointNode(String name, double x, double y)
	{
		_x = x;
		_y = y;
		_name = name;
	}

	@Override
	public int hashCode()
	{
		return Double.valueOf(_x).hashCode() + Double.valueOf(_y).hashCode();
	}
	
	/**
	 * @return True if objects are equal; False otherwise
	 * @param obj: point node being compared 
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof PointNode)) return false;
		
		if(this == obj) return true;
		
		PointNode node = (PointNode) obj;
		return MathUtilities.doubleEquals(this.getX(), node.getX())&&
			   MathUtilities.doubleEquals(this.getY(), node.getY());
	}
	
	/**
	 * @return String version of node
	 */
    @Override
    public String toString()
    {
		 return _name + "(" + _x + ", " + _y + ")";
	}
    
    @Override
    public void unparse(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }

        unparse(sb, level);
        sb.append("\n");
    }
}