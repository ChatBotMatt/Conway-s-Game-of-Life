package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Engine engine;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			engine = new Engine(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
