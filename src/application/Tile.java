package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents an active or inactive Tile on the grid.
 * @author Matthew Maufe
 *
 */
public class Tile {

	private static final Image tileActive = new Image("assets/Active Tile.png");
	private static final Image tileInactive = new Image("assets/Inactive Tile.png");
	private ImageView tileView;
	
	private boolean alive; //Whether the Tile is alive or dead.
	private Position position;
	
	/**
	 * 
	 * @param alive True, if the Tile is initially alive (green). 
	 */
	public Tile(boolean alive, Position pos){
		this.tileView = new ImageView();
		setImageByAlive();
		this.alive = alive;
		this.position = pos;
	}
	
	/**
	 * Sets the image ("Active Tile.png" or "Inactive Tile.png"), based on the "alive" boolean.
	 */
	private void setImageByAlive(){
		Image image = (alive) ? tileActive : tileInactive;
		tileView.setImage(image);
		this.tileView = new ImageView(image);
	}
	
	public Tile(boolean alive, int x, int y){
		this(alive,new Position(x,y));
	}

	public ImageView getTileView() {
		return tileView;
	}

	public void setTileView(ImageView tileView) {
		this.tileView = tileView;
	}

	public boolean isAlive() {
		return alive;
	}

	/**
	 * Sets the boolean "alive", and automatically sets changes the Tile's ImageView image accordingly.
	 * @param alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
		setImageByAlive();
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public String toString(){
		return "This tile is " + ((alive) ? "alive" : "not alive") + " at " + position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alive ? 1231 : 1237);
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((tileView == null) ? 0 : tileView.hashCode());
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
		Tile other = (Tile) obj;
		if (alive != other.alive)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (tileView == null) {
			if (other.tileView != null)
				return false;
		} else if (!tileView.equals(other.tileView))
			return false;
		return true;
	}
	
}
