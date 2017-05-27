package application;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Engine {
	
	public static final int tileWidth = 32;
	public static final int tileHeight = 32;
	
	private static final int width = 400; //Note: Rounded down to nearest multiple of tileWidth and tileHeight respectively.
	private static final int height = 400;
	
	private static final int tilesAcross = width/tileWidth;
	private static final int tilesDown = height/tileHeight;
	
	private Scene scene;
	private TilePane root;

	private HashMap<Position,Tile> grid; //Note: Y goes from top to bottom, 0-tilesDown. 
	private boolean seedable = true; //Whether the tiles can be clicked to change the seed still, or not.
	
	private Random random;
	
	public Engine(Stage primaryStage){
		
		double roundedWidth = Math.floor(tileWidth*tilesAcross);
		double roundedHeight = Math.floor(tileHeight*tilesDown);
		
		root = new TilePane();
		scene = new Scene(root,roundedWidth,roundedHeight);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		grid = new HashMap<Position,Tile>((int) (tilesAcross * tilesDown));
		random = new Random();
		initialise();
		
	}
	
	private void initialise(){
		Position pos;
		Tile tile;
		
		for (int y = 0; y < tilesDown; y++){
			for (int x = 0; x < tilesAcross; x++){
				pos = new Position(x,y);
				tile = new Tile(random.nextBoolean(),pos);
				
				tile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { //Tiles are initially toggleable, to act as a visual "seed".

				     @Override
				     public void handle(MouseEvent event) {
				    	 Tile tile = (Tile) event.getSource();
				    	 if (seedable){
				    		 tile.toggleAlive();
				    	 }         
				         event.consume(); //Stops the event from propagating up to the scene containing it.
				     }
				});
				
				grid.put(pos, tile);
				root.getChildren().add(tile);
			}
		}
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                	Optional<ButtonType> op = new Alert(AlertType.CONFIRMATION,"Are you sure you're finished setting the seed?").showAndWait();
                	if (op.isPresent()){
                		if (op.get().equals(ButtonType.OK)){
                			seedable = false;
                			start();
                		}
                	}
                }
                else if (event.getCode() == KeyCode.TAB){ //For debugging.
                	if (seedable)
                	for (Tile tile: grid.values()){
                		tile.setAlive(false);
                	}
                }
            }
        });
	}
	
	/**
	 * Starts the game, when the user is finished seeding. 
	 */
	private void start(){
		int tilesChanged = 0;
		Position pos;
		
		Tile current;
		Tile check; //The neighbour being checked.
		
		int alive = 0; //Number of living neighbours that "current" has. Starts at -1 since they count themselves.
		do { //Main simulation loop.
			tilesChanged = 0;
			for (int y = 0; y < tilesDown; y++){
				for (int x = 0; x < tilesAcross; x++){
					pos = new Position(x,y);
					
					Optional<Tile> op = getTileAt(pos);
					if (op.isPresent()){
						current = op.get();
						for (int yDelta = -1; yDelta < 2; yDelta++){
							for (int xDelta = -1; xDelta < 2; xDelta++){
								op = getTileAt(new Position(x+xDelta,y+yDelta));
								if (op.isPresent()){
									check = op.get();
									if ((check.isAlive()) && !(check.equals(current))){
										alive++;
									}
								}
								else{
									continue;
								}
							}
						}
						current.react(alive);
					}
					alive = 0;
				}
			}
			for (Tile tile: grid.values()){
				tilesChanged += ( (tile.isNewAlive() == tile.isAlive()) ? 0 : 1 );
				tile.setAlive(tile.isNewAlive()); //Changes states accordingly.
			}
		} while (tilesChanged > 0);
	}
	
	/**
	 * Returns an Optional containing either the tile at the specified position or null if it doesn't exist.
	 * @param pos The position to check.
	 * @return
	 */
	private Optional<Tile> getTileAt(Position pos){
		return Optional.ofNullable(grid.get(pos));
	}
	
}
