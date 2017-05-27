package application;

import java.util.HashMap;
import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Engine {
	
	public static final int tileWidth = 32;
	public static final int tileHeight = 32;
	
	private static final int width = 400; //Note: Rounded down to nearest multiple of tileWidth and tileHeight respectively.
	private static final int height = 400;
	
	private static final int tilesAcross = width/tileWidth;
	private static final int tilesDown = height/tileHeight;

	private HashMap<Position,Tile> grid; //Note: Y goes from top to bottom, 0-tilesDown. 
	
	private Random random;
	
	public Engine(Stage primaryStage){
		
		double roundedWidth = Math.floor(tileWidth*tilesAcross);
		double roundedHeight = Math.floor(tileHeight*tilesDown);
		
		TilePane root = new TilePane();
		Scene scene = new Scene(root,roundedWidth,roundedHeight);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		grid = new HashMap<Position,Tile>((int) (tilesAcross * tilesDown));
		random = new Random();
		initialise();
	}
	
	private void initialise(){
		Position pos;
		for (int y = 0; y < tilesDown; y++){
			for (int x = 0; x < tilesAcross; x++){
				pos = new Position(x,y);
				grid.put(pos, new Tile(random.nextBoolean(),pos));
				System.out.println(grid.get(pos));
			}
		}
	}
	
}
