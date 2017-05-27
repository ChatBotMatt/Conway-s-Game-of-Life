package application;

/**
 * Represents an (x,y) coordinate on the Grid.
 * @author Matthew
 *
 */
public class Position {

	private int x;
	private int y;
	
	public Position(int x, int y){
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	/*/**
	 * Sets x to the value of parameter x, rounded down to the a multiple of Tile Width.
	 * @param x 
	 * @return The value of x that was set (post-rounding).
	 */
	public int setX(int x){
		//x = (int) (Engine.tileWidth * Math.floor((x/Engine.tileWidth)));
		this.x = x;
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/*/**
	 * Sets y to the value of parameter y, rounded down to the a multiple of Tile Height.
	 * @param y 
	 * @return The value of y that was set (post-rounding).
	 */
	public int setY(int y){
		//y = (int) Math.floor(Engine.tileHeight*(y/Engine.tileHeight)); //Round down to nearest multiple of Tile Width.
		this.y = y;
		return y;
	}
	
	/**
	 * Returns Position as a string in the form (x,y)
	 * 
	 * E.g. "(3,5)"
	 */
	@Override
	public String toString(){
		return "(" + x + ", " + y + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
