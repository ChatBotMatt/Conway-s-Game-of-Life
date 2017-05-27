package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents an active or inactive Tile on the grid.
 * @author Matthew Maufe
 *
 */
public class Tile extends ImageView{

	private static final Image tileActive = new Image("assets/Active Tile.png");
	private static final Image tileInactive = new Image("assets/Inactive Tile.png");
	//private ImageView tileView;
	
	private boolean alive; //Whether the Tile is alive or dead.
	private boolean newAlive; //The state of the Tile, next iteration.
	private Position position;
	
	/**
	 * 
	 * @param alive True, if the Tile is initially alive (green). 
	 */
	public Tile(boolean alive, Position pos){
		setAlive(alive);
		this.newAlive = alive;
		this.position = pos;
	}
	
	public Tile(boolean alive, int x, int y){
		this(alive,new Position(x,y));
	}
	
	/**
	 * Sets the image ("Active Tile.png" or "Inactive Tile.png"), based on the "alive" boolean.
	 */
	private void setImageByAlive(){
		Image image = (alive) ? tileActive : tileInactive;
		this.setImage(image);
	}
	
	public void toggleAlive(){
		setAlive(!alive);
	}
	
	/**
	 * Changes newAlive (if apt) based on how many neighbours are living.
	 * @param livingNeighbours How many of the tile's neighbours are alive.
	 * @return newAlive after reacting.
	 */
	public boolean react(int livingNeighbours){
		
		/**
		 * At each step in time, the following transitions occur:

			Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
			Any live cell with two or three live neighbours lives on to the next generation.
			Any live cell with more than three live neighbours dies, as if by overpopulation.
			Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
		 */
		
		if (alive){
			if ((livingNeighbours < 2) || (livingNeighbours > 3)){
				newAlive = false;
			}
			else{
				newAlive = true;
			}
		}
		else{
			if (livingNeighbours == 3){
				newAlive = true;
			}
			else{
				newAlive = false;
			}
		}
		
		return newAlive;
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
	
	public boolean isNewAlive(){
		return newAlive;
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
		return true;
	}
	
}
