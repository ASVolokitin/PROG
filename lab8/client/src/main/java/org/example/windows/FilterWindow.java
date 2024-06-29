package org.example.windows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class FilterWindow extends Stage {

    private Label label = new Label();
    private TextField textField = new TextField();
    private Button okButton = new Button("OK");
    private Integer coordX;

    public Integer getCoordX() {
        return coordX;
    }

    public FilterWindow() {
        setTitle("Filters");
        initModality(Modality.APPLICATION_MODAL);
        setMinWidth(250);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(label, 0, 0);
        gridPane.add(textField, 0, 1);
        gridPane.add(okButton, 0, 2);

        okButton.setOnAction(e -> {
            String value = textField.getText();
            coordX = Integer.valueOf(value);
            // Здесь вы можете обработать введенное значение
//            System.out.println("Введено значение: " + value);
            close(); // Закрыть окно после обработки
        });

        Scene scene = new Scene(gridPane);
        setScene(scene);
    }
}
