package dad.calculadora.compleja;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Calculadora extends Application {

	private TextField real1Text;
	private TextField imaginario1Text;
	private Label signo1Label;
	private TextField real2Text;
	private TextField imaginario2Text;
	private Label signo2Label;
	private TextField resultadoRealText;
	private TextField resultadoImaginarioText;
	private Label signo3Label;
	private Label i1;
	private Label i2;
	private Label i3;
	private ComboBox<String> signo = new ComboBox<String>();	
	
	private Complejo complejo1 = new Complejo();
	private Complejo complejo2 = new Complejo();
	private Complejo resultado = new Complejo();
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		signo.getItems().addAll(
				"+",
				"-",
				"/",
				"x"
				);
		signo.getSelectionModel().selectFirst();;
		VBox signoBox = new VBox(5, signo);
		
		real1Text = new TextField();
		real1Text.setMaxWidth(60);
		signo1Label = new Label();
		imaginario1Text = new TextField();
		imaginario1Text.setMaxWidth(60);
		i1 = new Label("i");
		HBox operando1 = new HBox(5 );
		operando1.getChildren().addAll(real1Text, signo1Label, imaginario1Text, i1);
		
		//Bindeos		
		Bindings.bindBidirectional(real1Text.textProperty(), complejo1.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(imaginario1Text.textProperty(), complejo1.imaginarioProperty(), new NumberStringConverter());
		
		real2Text = new TextField();
		real2Text.setMaxWidth(60);
		signo2Label = new Label();
		imaginario2Text = new TextField();
		imaginario2Text.setMaxWidth(60);
		i2 = new Label("i");
		HBox operando2 = new HBox(5);
		operando2.getChildren().addAll(real2Text, signo2Label, imaginario2Text, i2);
		
		//Bindeos		
		Bindings.bindBidirectional(real2Text.textProperty(), complejo2.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(imaginario2Text.textProperty(), complejo2.imaginarioProperty(), new NumberStringConverter());
		
		Separator sep = new Separator();
		
		resultadoRealText = new TextField();
		resultadoRealText.setMaxWidth(60);
		resultadoRealText.setDisable(true);
		signo3Label = new Label();
		resultadoImaginarioText = new TextField();
		resultadoImaginarioText.setMaxWidth(60);
		resultadoImaginarioText.setDisable(true);
		i3 = new Label("i");
		HBox operando3 = new HBox(5);
		operando3.getChildren().addAll(resultadoRealText, signo3Label, resultadoImaginarioText, i3);
		
		//Bindeos		
		Bindings.bindBidirectional(resultadoRealText.textProperty(), resultado.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(resultadoImaginarioText.textProperty(), resultado.imaginarioProperty(), new NumberStringConverter());
		
		VBox operacion = new VBox(5, operando1, operando2, sep, operando3);
		
		HBox root = new HBox(5, signoBox, operacion);
		root.setFillHeight(false);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 320, 200);
		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		signo1Label.textProperty().bind(signo.valueProperty());
		signo2Label.textProperty().bind(signo.valueProperty());
		signo3Label.textProperty().bind(signo.valueProperty());
		
		signo.valueProperty().addListener(e -> {
			if (signo.valueProperty().getValue() == "+") {
				resultado.realProperty().bind(complejo1.realProperty().add(complejo2.realProperty()));
				resultado.imaginarioProperty().bind(complejo1.imaginarioProperty().add(complejo2.imaginarioProperty()));
				
			} else if (signo.valueProperty().getValue() == "-") {
				resultado.realProperty().bind(complejo1.realProperty().subtract(complejo2.realProperty()));
				resultado.imaginarioProperty()
						.bind(complejo1.imaginarioProperty().subtract(complejo2.imaginarioProperty()));
				
			} else if (signo.valueProperty().getValue() == "x") {
				resultado.realProperty().bind(complejo1.realProperty().multiply(complejo2.realProperty())
						.subtract(complejo1.imaginarioProperty().multiply(complejo2.imaginarioProperty())));

				resultado.imaginarioProperty().bind(complejo1.realProperty().multiply(complejo2.imaginarioProperty())
						.add(complejo1.imaginarioProperty().multiply(complejo2.realProperty())));
				
			} else if (signo.valueProperty().getValue() == "/") {
				resultado.realProperty().bind((complejo1.realProperty().multiply(complejo2.realProperty())
						.add(complejo1.imaginarioProperty().multiply(complejo2.imaginarioProperty())))
								.divide((complejo2.realProperty().multiply(complejo2.realProperty()).add(
										complejo2.imaginarioProperty().multiply(complejo2.imaginarioProperty())))));

				resultado.imaginarioProperty().bind((complejo1.imaginarioProperty().multiply(complejo2.realProperty())
						.subtract(complejo1.realProperty().multiply(complejo2.imaginarioProperty())))
								.divide((complejo2.realProperty().multiply(complejo2.realProperty()).add(
										complejo2.imaginarioProperty().multiply(complejo2.imaginarioProperty())))));
			}
		});
				
	}

	public static void main(String[] args) {
		launch(args);
	}

}
