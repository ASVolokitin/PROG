package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.RuntimeManager;

import javafx.event.ActionEvent;
import org.example.functionalClasses.SetValues;
import org.example.functionalClasses.Settings;
import org.example.validators.Validator;
import org.example.validators.ValidatorList;

import java.util.*;

import static com.sun.javafx.event.EventUtil.fireEvent;

public class AddController {

    private ResourceBundle bundle;
    private Stage stage;
    private RuntimeManager runtimeManager;
    private HashMap<Integer, Validator> validatorList = new HashMap<>(new ValidatorList().getValidatorList());
    private ArrayList<SetValues> settings = new ArrayList<SetValues>(new Settings().getSettings());

    @FXML
    private Text addTitle;

    @FXML
    private TextField coordXField;

    @FXML
    private TextField coordYField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField mpaaRatingField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField oscarsCountField;


    @FXML
    private TextField screenwriterNameField;

    @FXML
    private TextField screenwriterBirthdayField;

    @FXML
    private TextField screenwriterWeightField;

    @FXML
    private TextField screenwriterCoordXField;

    @FXML
    private TextField screenwriterCoordYField;

    @FXML
    private TextField screenwriterCountryField;

    @FXML
    private Label validatorLabel;

    @FXML
    private Button sendBtn;

    @FXML
    private Label addCoordX;

    @FXML
    private Label addCoordY;

    @FXML
    private Label addGenre;

    @FXML
    private Label addMpaa;

    @FXML
    private Label addName;

    @FXML
    private Label addOscars;

    @FXML
    private Label addScrBirthday;

    @FXML
    private Label addScrCoordX;

    @FXML
    private Label addScrCoordY;

    @FXML
    private Label addScrCountry;

    @FXML
    private Label addScrName;

    @FXML
    private Label addScrWeight;

    @FXML
    void initialize() {
        addTitle.setText(bundle.getString("add_title"));
        addName.setText(bundle.getString("column.1"));
        addCoordX.setText(bundle.getString("column.2"));
        addCoordY.setText(bundle.getString("column.3"));
        addOscars.setText(bundle.getString("column.5"));
        addGenre.setText(bundle.getString("column.6"));
        addMpaa.setText(bundle.getString("column.7"));
        addScrName.setText(bundle.getString("column.8"));
        addScrBirthday.setText(bundle.getString("column.9"));
        addScrWeight.setText(bundle.getString("column.10"));
        addScrCoordX.setText(bundle.getString("column.11"));
        addScrCoordY.setText(bundle.getString("column.12"));
        addScrCountry.setText(bundle.getString("column.13"));
        sendBtn.setText(bundle.getString("send_btn"));


    }

    @FXML
    public void sendBtnOnAction(ActionEvent event) {
        HashMap<Integer, String> data = new HashMap<>();
        data.put(0, nameField.getText());
        data.put(1, coordXField.getText());
        data.put(2, coordYField.getText());
        data.put(3, oscarsCountField.getText());
        data.put(4, genreField.getText());
        data.put(5, mpaaRatingField.getText());
        data.put(6, screenwriterNameField.getText());
        data.put(7, screenwriterBirthdayField.getText());
        data.put(8, screenwriterWeightField.getText());
        data.put(9, screenwriterCoordXField.getText());
        data.put(10, screenwriterCoordYField.getText());
        data.put(11, screenwriterCountryField.getText());
        System.out.println(data);
        String validateResult = validateMovieData(data);
        System.out.println(validateResult);
        if (validateResult.equals("")) {
            runtimeManager.executeCommand("add", getArrayFromHashMap(data));
            stage.close();
        }
        else {
            validatorLabel.setText(validateResult);
        }

    }

    public AddController(RuntimeManager runtimeManager, Stage stage) {
        this.runtimeManager = runtimeManager;
        this.stage = stage;
        this.bundle = ResourceBundle.getBundle("MAIN_Bundle", Locale.getDefault());
    }

    public String validateMovieData(HashMap<Integer, String> input) {
        HashMap<Integer, String> data = new HashMap<>();
        String errorString = "";
        int step = 0;
        try {
            while (step < input.size()) {
                String line = input.get(step);
                System.out.println("Проверяю");
                if (validatorList.get(step).validate(line)) {
                    line = line.replaceAll(">", "&gt;");
                    line = line.replaceAll("<", "&lt;");

                    if (line.equals("")) line = null;
                    data.put(step, line);
                    step++;
                    System.out.println(step);
                } else {
                    errorString += bundle.getString("validate_wrong_format") + "\n";
                    errorString += settings.get(step).getComment();
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
            errorString += bundle.getString("validate_wrong_format_comment") + " " + settings.get(step).getValueType();
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


}

