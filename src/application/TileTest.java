package application;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests that Tiles change their state correctly.
 * 
 * Note: Requires commenting out JavaFX code (ImageView-related things, although inheritance still works) in Tile to work properly with FX.
 * @author Matthew
 *
 */
public class TileTest {
	
	Tile current;

	@Before
	public void setUp() throws Exception {
		current = new Tile(false, new Position(3,3));
	}

	@Test
	public void testStayDead(){
		for (int i = 0; i < 3; i++){
			assert(current.react(i) == false); //Should set newAlive to false, since dead and less than 3 living neighbours.
		}
		assert(current.react(4) == false); //Should stay dead.
	}
	
	@Test
	public void testResurrect(){
		assert(current.react(3) == true); //Should resurrect.
	}

}
