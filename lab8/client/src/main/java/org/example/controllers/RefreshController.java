package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.RuntimeManager;
import org.example.functionalClasses.SetValues;
import org.example.functionalClasses.Settings;
import org.example.movieClasses.Movie;
import org.example.validators.Validator;
import org.example.validators.ValidatorList;

import java.util.*;

public class RefreshController {

    private RuntimeManager runtimeManager;
    private Stage stage;
    private Movie movie;
    private HashMap<Integer, Validator> validatorList = new HashMap<>(new ValidatorList().getValidatorList());
    private ArrayList<SetValues> settings = new ArrayList<SetValues>(new Settings().getSettings());
    private ResourceBundle bundle;


    public RefreshController(RuntimeManager runtimeManager, Stage secondStage, Optional opt) {
        this.runtimeManager = runtimeManager;
        this.stage = secondStage;
        this.movie = (Movie) opt.get();
        this.bundle = ResourceBundle.getBundle("MAIN_Bundle", Locale.getDefault());
    }


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
    private Label addScrName;

    @FXML
    private Label addScrWeight;

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
    private Text refreshTitle;

    @FXML
    private TextField screenwriterBirthdayField;

    @FXML
    private TextField screenwriterCoordXField;

    @FXML
    private TextField screenwriterCoordYField;

    @FXML
    private TextField screenwriterCountryField;

    @FXML
    private TextField screenwriterNameField;

    @FXML
    private TextField screenwriterWeightField;

    @FXML
    private Button sendBtn;

    @FXML
    private Label validatorLabel;

    @FXML
    void sendBtnOnAction(ActionEvent event) {
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
            data.put(12, movie.getId().toString());
            runtimeManager.executeCommand("update_by_id", getArrayFromHashMap(data));
            stage.close();
        }
        else {
            validatorLabel.setText(validateResult);
        }
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

    @FXML
    public void initialize() {
        nameField.setText(movie.getName());
        coordXField.setText(movie.getCoordinates().getX().toString());
        coordYField.setText(movie.getCoordinates().getY().toString());
        oscarsCountField.setText(String.valueOf(movie.getOscarsCount()));
        genreField.setText(movie.getGenre().toString());
        mpaaRatingField.setText(movie.getMpaaRating().toString());
        screenwriterNameField.setText(movie.getScreenwriter().getName());
        screenwriterBirthdayField.setText(movie.getScreenwriter().getBirthday().toString());
        screenwriterWeightField.setText(movie.getScreenwriter().getWeight().toString());
        screenwriterCoordXField.setText(movie.getScreenwriter().getLocation().getX().toString());
        screenwriterCoordYField.setText(movie.getScreenwriter().getLocation().getY().toString());
        screenwriterCountryField.setText(movie.getScreenwriter().getLocation().getName());
    }

}
