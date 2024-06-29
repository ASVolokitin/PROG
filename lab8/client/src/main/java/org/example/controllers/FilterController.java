package org.example.controllers;

import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.RuntimeManager;
import org.example.functionalClasses.SetValues;
import org.example.functionalClasses.Settings;
import org.example.validators.Validator;
import org.example.validators.ValidatorList;

public class FilterController {

    private RuntimeManager runtimeManager;
    private Stage stage;
    private HashMap<Integer, Validator> validatorList = new HashMap<>(new ValidatorList().getValidatorList());
    private ArrayList<SetValues> settings = new ArrayList<SetValues>(new Settings().getSettings());
    private ResourceBundle bundle;


    public FilterController(RuntimeManager runtimeManager, Stage stage) {
        this.runtimeManager = runtimeManager;
        this.stage = stage;
        this.bundle = ResourceBundle.getBundle("MAIN_Bundle", Locale.getDefault());
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ApplyBtn;

    @FXML
    private TextField coordXFrom;

    @FXML
    private Label coordXLabel;

    @FXML
    private TextField coordXTo;

    @FXML
    private TextField coordYFrom;

    @FXML
    private Label coordYLabel;

    @FXML
    private TextField coordYTo;

    @FXML
    private DatePicker creationTimeFrom;

    @FXML
    private Label creationTimeLabel;

    @FXML
    private DatePicker creationTimeTo;

    @FXML
    private Text filtersLabel;

    @FXML
    private Label fromLabel;

    @FXML
    private TextField genreFrom;

    @FXML
    private Label genreLabel;

    @FXML
    private TextField genreTo;

    @FXML
    private GridPane grid;

    @FXML
    private TextField idFrom;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTo;

    @FXML
    private TextField mpaaFrom;

    @FXML
    private Label mpaaLabel;

    @FXML
    private TextField mpaaTo;

    @FXML
    private TextField nameFrom;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTo;

    @FXML
    private TextField oscarsFrom;

    @FXML
    private Label oscarsLabel;

    @FXML
    private TextField oscarsTo;

    @FXML
    private TextField scrWeightFrom;

    @FXML
    private TextField scrWeightTo;

    @FXML
    private DatePicker scrBirthdayFrom;

    @FXML
    private Label scrBirthdayLabel;

    @FXML
    private DatePicker scrBirthdayTo;

    @FXML
    private TextField scrCoordXFrom;

    @FXML
    private Label scrCoordXLabel;

    @FXML
    private TextField scrCoordXTo;

    @FXML
    private TextField scrCoordYFrom;

    @FXML
    private Label scrCoordYLabel;

    @FXML
    private TextField scrCoordYTo;

    @FXML
    private TextField scrCountryFrom;

    @FXML
    private Label scrCountryLabel;

    @FXML
    private TextField scrCountryTo;

    @FXML
    private TextField scrNameFrom;

    @FXML
    private Label scrNameLabel;

    @FXML
    private TextField scrNameTo;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameFrom;

    @FXML
    private TextField usernameTo;

    @FXML
    private Text errorLabel;

    @FXML
    void applyBtnOnAction(ActionEvent event) {
        HashMap<Integer, String> dataFrom = new HashMap<>();
        dataFrom.put(0, idFrom.getText() == "" ? null : idFrom.getText());
        dataFrom.put(1, nameFrom.getText() == "" ? null : nameFrom.getText());
        dataFrom.put(2, coordXFrom.getText() == "" ? null : coordXFrom.getText());
        dataFrom.put(3, coordYFrom.getText() == "" ? null : coordYFrom.getText());
        dataFrom.put(4, oscarsFrom.getText() == "" ? null : oscarsFrom.getText());
        dataFrom.put(5, genreFrom.getText() == "" ? null : genreFrom.getText());
        dataFrom.put(6, mpaaFrom.getText() == "" ? null : mpaaFrom.getText());
        dataFrom.put(7, scrNameFrom.getText() == "" ? null : scrNameFrom.getText());
        dataFrom.put(8, scrBirthdayFrom.getValue() == null ? null : scrBirthdayFrom.getValue().toString());
        dataFrom.put(9, scrWeightFrom.getText() == "" ? null : scrWeightFrom.getText());
        dataFrom.put(10, scrCoordXFrom.getText() == "" ? null : scrCoordXFrom.getText());
        dataFrom.put(11, scrCoordYFrom.getText() == "" ? null : scrCoordYFrom.getText());
        dataFrom.put(12, scrCountryFrom.getText() == "" ? null : scrCountryFrom.getText());
        dataFrom.put(13, usernameFrom.getText() == "" ? null : usernameFrom.getText());
        String validatingResult = validateMovieData(dataFrom);
        errorLabel.setText("(1): " + validatingResult);

        if (validatingResult.equals("")) {
            HashMap<Integer, String> dataTo = new HashMap<>();
            dataTo.put(0, idTo.getText() == "" ? null : idTo.getText());
            dataTo.put(1, nameTo.getText() == "" ? null : nameTo.getText());
            dataTo.put(2, coordXTo.getText() == "" ? null : coordXTo.getText());
            dataTo.put(3, coordYTo.getText() == "" ? null : coordYTo.getText());
            dataTo.put(4, oscarsTo.getText() == "" ? null : oscarsTo.getText());
            dataTo.put(5, genreTo.getText() == "" ? null : genreTo.getText());
            dataTo.put(6, mpaaTo.getText() == "" ? null : mpaaTo.getText());
            dataTo.put(7, scrNameTo.getText() == "" ? null : scrNameTo.getText());
            dataTo.put(8, scrBirthdayTo.getValue() == null ? null : scrBirthdayTo.getValue().toString());
            dataTo.put(9, scrWeightTo.getText() == "" ? null : scrWeightTo.getText());
            dataTo.put(10, scrCoordXTo.getText() == "" ? null : scrCoordXTo.getText());
            dataTo.put(11, scrCoordYTo.getText() == "" ? null : scrCoordYTo.getText());
            dataTo.put(12, scrCountryTo.getText() == "" ? null : scrCountryTo.getText());
            dataTo.put(13, usernameTo.getText() == "" ? null : usernameTo.getText());
            validatingResult = validateMovieData(dataTo);
            errorLabel.setText("(2): " + validatingResult);
            System.out.println(validatingResult);

            if (validatingResult.equals("")) stage.close();
        }

    }

    @FXML
    void coordXFromOnAction(ActionEvent event) {

    }

    @FXML
    void coordXToOnAction(ActionEvent event) {

    }

    @FXML
    void coordYFromOnAction(ActionEvent event) {

    }

    @FXML
    void coordYToOnAction(ActionEvent event) {

    }

    @FXML
    void creationTimeFromOnAction(ActionEvent event) {

    }

    @FXML
    void creationTimeToOnAction(ActionEvent event) {

    }

    @FXML
    void genreFromOnAction(ActionEvent event) {

    }

    @FXML
    void genreToOnAction(ActionEvent event) {

    }

    @FXML
    void gridOnAction(MouseEvent event) {

    }

    @FXML
    void idFromOnAction(ActionEvent event) {

    }

    @FXML
    void idToOnAction(ActionEvent event) {

    }

    @FXML
    void mpaaFromOnAction(ActionEvent event) {

    }

    @FXML
    void mpaaToOnAction(ActionEvent event) {

    }

    @FXML
    void nameFromOnAction(ActionEvent event) {

    }

    @FXML
    void nameToOnAction(ActionEvent event) {


    }

    @FXML
    void oscarsFromOnAction(ActionEvent event) {

    }

    @FXML
    void oscarsToOnAction(ActionEvent event) {

    }

    @FXML
    void scrBirthdayFromOnAction(ActionEvent event) {

    }

    @FXML
    void scrBirthdayToOnAction(ActionEvent event) {

    }

    @FXML
    void scrWeightFromOnAction(ActionEvent event) {

    }

    @FXML
    void scrWeightToOnAction(ActionEvent event) {

    }

    @FXML
    void scrCoordXFromOnAction(ActionEvent event) {

    }

    @FXML
    void scrCoordXToOnAction(ActionEvent event) {

    }

    @FXML
    void scrCoordYFromOnAction(ActionEvent event) {

    }

    @FXML
    void scrCoordYToOnAction(ActionEvent event) {

    }

    @FXML
    void scrCountryFromOnAction(ActionEvent event) {

    }

    @FXML
    void scrCountryToOnAction(ActionEvent event) {

    }

    @FXML
    void scrNameFromOnAction(ActionEvent event) {

    }

    @FXML
    void scrNameToOnAction(ActionEvent event) {

    }

    @FXML
    void usernameFromOnAction(ActionEvent event) {

    }

    @FXML
    void usernameToOnAction(ActionEvent event) {

    }

    @FXML
    private Label scrWeightLabel;

    @FXML
    private Label toLabel;

    @FXML
    void initialize() {
        filtersLabel.setText(bundle.getString("filters_header"));
        idLabel.setText(bundle.getString("column.0"));
        nameLabel.setText(bundle.getString("column.1"));
        coordXLabel.setText(bundle.getString("column.2"));
        coordYLabel.setText(bundle.getString("column.3"));
        oscarsLabel.setText(bundle.getString("column.5"));
        genreLabel.setText(bundle.getString("column.6"));
        mpaaLabel.setText(bundle.getString("column.7"));
        scrNameLabel.setText(bundle.getString("column.8"));
        scrBirthdayLabel.setText(bundle.getString("column.9"));
        scrWeightLabel.setText(bundle.getString("column.10"));
        scrCoordXLabel.setText(bundle.getString("column.11"));
        scrCoordXLabel.setText(bundle.getString("column.12"));
        scrCountryLabel.setText(bundle.getString("column.13"));
        usernameLabel.setText(bundle.getString("column.14"));
        fromLabel.setText(bundle.getString("from_label"));
        toLabel.setText(bundle.getString("to_label"));
        ApplyBtn.setText(bundle.getString("applyBtn"));

    }

    public String validateMovieData(HashMap<Integer, String> input) {
        HashMap<Integer, String> data = new HashMap<>();
        String errorString = "";
        int step = 1;
        errorString = validateID(input.get(0));
        if (!errorString.equals("")) return errorString;
        try {
            while (step < validatorList.size()) {
                String line = input.get(step);
                if (line == null || line.equals("")) {
                    step++;
                    continue;
                }
                if (validatorList.get(step - 1).validate(line)) {
                    line = line.replaceAll(">", "&gt;");
                    line = line.replaceAll("<", "&lt;");

                    if (line.equals("")) line = null;
                    data.put(step - 1, line);
                    step++;
                } else {
                    errorString += bundle.getString("validate_wrong_format") + "\n";
                    errorString += settings.get(step - 1).getComment();
                    break;
//                    if (!Reader.isReadingConsole()) {
//                        System.out.println(settings.get(step).getComment());
//                        System.out.println("Дальнейшее выполнение скрипта невозможно");
//                        Reader.finishFile();
//                        break;
//                    }
                }
            }
        } catch (NumberFormatException e) {
//            if (!Reader.isReadingConsole()) {
//                System.out.println("Неверный формат значения, дальнейшее выполнение скрипта невозможно");
//                Reader.finishFile();
//            } else {
            errorString += bundle.getString("validate_wrong_format_comment") +  " " + settings.get(step - 1).getValueType();
//            }
        } catch (IllegalArgumentException e) {
//            if (!Reader.isReadingConsole()) {
//                System.out.println("Значение не из списка допустимых значений, дальнейшее выполнение скрипта невозможно.");
//                Reader.finishFile();
//            } else {
            errorString += bundle.getString("validate_out_of_list");
//            }
        } catch (NoSuchElementException e) {
            System.out.println(bundle.getString("validate_impossible_script"));
            Reader.finishFile();
        }

        return errorString;
    }

    private static String[] getArrayFromHashMap(HashMap<Integer, String> map) {
        String[] array = new String[map.size()];
        for (int i = 0; i < map.size(); ++i) {
            array[i] = map.get(i);
        }
        return array;
    }

    public String validateID(String id) {
        if (id != null && !id.equals("")) {
            try {
                Long correctID = Long.parseLong(id);
            } catch (NumberFormatException e) {
                return bundle.getString("validate_integer_id");
            }
        }
        return "";
    }

}
