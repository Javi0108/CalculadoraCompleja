package dad.javafx.calculadoraCompleja;

import com.sun.javafx.scene.control.DoubleField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Complejo {
	
	private Stage primaryStage;
	private ComboBox<String> operacionCombo;
	private DoubleField real, imaginario;

	@Override
	public void start(Stage primaryStage) throws Exception {
	
	HBox pesoBox = new HBox();
	pesoBox.setAlignment(Pos.BASELINE_CENTER);
	pesoBox.setSpacing(5);
	pesoBox.getChildren().addAll();
	
	real = new DoubleField();
	real.setPrefColumnCount(4);
	
	VBox comboBox = new VBox();
	comboBox.setAlignment(Pos.BASELINE_CENTER);
	comboBox.setSpacing(5);
	comboBox.getChildren().addAll(operacionCombo);		
	
	VBox root = new VBox();
	root.setSpacing(5);
	root.setAlignment(Pos.CENTER);
	root.getChildren().addAll(pesoBox, alturaBox);
	
	Scene scene = new Scene(root, 320, 200);
	
	primaryStage.setTitle("Índice de Masa Corporal");
	primaryStage.setScene(scene);
	primaryStage.show();
	
	}
}
