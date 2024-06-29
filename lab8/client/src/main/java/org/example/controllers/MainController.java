package org.example.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import org.example.functionalClasses.*;

import org.example.movieClasses.Movie;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;
import org.example.responses.ShowResponse;
import org.example.validators.Validator;
import org.example.validators.ValidatorList;
import org.example.windows.VisualisationWindow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainController {

    private boolean filterIsOn = false;
    private RuntimeManager runtimeManager;
    private TCPClient client;
    private ResourceBundle bundle;
    private Stage primaryStage;
    private ScheduledExecutorService executor;
    private HashMap<Integer, Validator> validatorList = new HashMap<>(new ValidatorList().getValidatorList());
    private ArrayList<SetValues> settings = new ArrayList<SetValues>(new Settings().getSettings());
    private String creationDateFormat = "yyyy/MM/dd hh:mm:ss";
    private String birtdayDateFormat = "yyyy/MM/dd";
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    private DateTimeFormatter creationDateFormatter = DateTimeFormatter.ofPattern(creationDateFormat);
    private DateTimeFormatter birthdayDateFormatter = DateTimeFormatter.ofPattern(birtdayDateFormat);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

    public MainController(RuntimeManager runtimeManager, Stage primaryStage) {
        this.runtimeManager = runtimeManager;
        this.client = runtimeManager.getClient();
        this.primaryStage = primaryStage;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button averageOscarsBtn;

    @FXML
    private Button uploadFileBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextArea deleteMovieID;

    @FXML
    private Button filterBtn;

    @FXML
    private Button getFirstBtn;

    @FXML
    private Button getLeastBtn;

    @FXML
    private Label greetingLabel;

    @FXML
    private Button infoBtn;

    @FXML
    private Button insertBtn;

    @FXML
    private ComboBox languageSelector;

    @FXML
    private TableView<Movie> mainTable;


    @FXML
    private Button refreshAllBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private TextArea refreshMovieID;

    @FXML
    private Button visualisationBtn;

    @FXML
    void averageOscarsBtnOnAction(ActionEvent event) {
        List<Movie> movies = mainTable.getItems().stream().toList();
        double coordX = 0;
        double coordY = 0;
        double oscarsCount = 0;
        double weight = 0;
        double coordXScreen = 0;
        double coordYScreen = 0;

        for (Movie movie : movies) {
            coordX += movie.getCoordinates().getX() == null ? 0 : movie.getCoordinates().getX();
            coordY += movie.getCoordinates().getY() == null ? 0 : movie.getCoordinates().getY();
            oscarsCount += movie.getOscarsCount();
            weight += movie.getScreenwriter().getWeight() == null ? 0 : movie.getScreenwriter().getWeight();
            coordXScreen += movie.getScreenwriter().getLocation().getX() == null ? 0 : movie.getScreenwriter().getLocation().getX();
            coordYScreen += movie.getScreenwriter().getLocation().getY() == null ? 0 : movie.getScreenwriter().getLocation().getY();
        }

        coordX /= movies.size();
        coordY /= movies.size();
        oscarsCount /= movies.size();
        weight /= movies.size();
        coordXScreen /= movies.size();
        coordYScreen /= movies.size();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("avg_title"));

        String info = bundle.getString("column.2") + ": " + decimalFormat.format(coordX) + ";\n"
                + bundle.getString("column.3") + ": " + decimalFormat.format(coordY) + ";\n"
                + bundle.getString("column.5") + ": " + decimalFormat.format(oscarsCount) + ";\n"
                + bundle.getString("column.10") + ": " + decimalFormat.format(weight) + ";\n"
                + bundle.getString("column.11") + ": " + decimalFormat.format(coordXScreen) + ";\n"
                + bundle.getString("column.12") + ": " + decimalFormat.format(coordYScreen) + ";";
        alert.setHeaderText(info);
        alert.initModality(Modality.APPLICATION_MODAL);

        ButtonType customOkButtonT = new ButtonType(bundle.getString("ok_btn"), ButtonBar.ButtonData.OK_DONE);
        alert.getDialogPane().getButtonTypes().add(customOkButtonT);
        alert.getDialogPane().getButtonTypes().remove(ButtonType.OK);

        alert.showAndWait();

    }

    @FXML
    void clearBtnOnAction(ActionEvent event) {
        filterIsOn = false;
        System.out.println("Очищаю коллекцию ...");
        runtimeManager.executeCommand("clear", new String[0]);
        fillTable();
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        filterIsOn = false;
        try {
            Long id = Long.parseLong(deleteMovieID.getText());
            Response response = runtimeManager.executeCommand("remove_by_id", new String[]{String.valueOf(id)});
            fillTable();
        } catch (NumberFormatException e) {
            System.out.println("Введённое значение не является корректным ID.");
        }
    }

    @FXML
    void filterBtnOnAction(ActionEvent event) {

        filterIsOn = true;
        try {
            FXMLLoader addLoader = new FXMLLoader(Class.forName("org.example.Main").getClassLoader().getResource("filter.fxml"));
            Stage secondStage = new Stage();
            addLoader.setControllerFactory(param -> new FilterController(runtimeManager, secondStage));
            Parent root = addLoader.load();
            secondStage.setTitle(bundle.getString("filters_title"));
            secondStage.setScene(new Scene(root));
            secondStage.showAndWait();
//            fillTable();
            HashMap<Integer, String> dataFrom = new HashMap<>();
            dataFrom.put(0, ((TextField) root.lookup("#idFrom")).getText() == "" ? null : ((TextField) root.lookup("#idFrom")).getText());
            dataFrom.put(1, ((TextField) root.lookup("#nameFrom")).getText() == "" ? null : ((TextField) root.lookup("#nameFrom")).getText());
            dataFrom.put(2, ((TextField) root.lookup("#coordXFrom")).getText() == "" ? null : ((TextField) root.lookup("#coordXFrom")).getText());
            dataFrom.put(3, ((TextField) root.lookup("#coordYFrom")).getText() == "" ? null : ((TextField) root.lookup("#coordYFrom")).getText());
            dataFrom.put(4, ((TextField) root.lookup("#oscarsFrom")).getText() == "" ? null : ((TextField) root.lookup("#oscarsFrom")).getText());
            dataFrom.put(5, ((TextField) root.lookup("#genreFrom")).getText() == "" ? null : ((TextField) root.lookup("#genreFrom")).getText());
            dataFrom.put(6, ((TextField) root.lookup("#mpaaFrom")).getText() == "" ? null : ((TextField) root.lookup("#mpaaFrom")).getText());
            dataFrom.put(7, ((TextField) root.lookup("#scrNameFrom")).getText() == "" ? null : ((TextField) root.lookup("#scrNameFrom")).getText());
            dataFrom.put(8, ((DatePicker) root.lookup("#scrBirthdayFrom")).getValue() == null ? null : ((DatePicker) root.lookup("#scrBirthdayFrom")).getValue().toString());
            dataFrom.put(9, ((TextField) root.lookup("#scrWeightFrom")).getText() == "" ? null : ((TextField) root.lookup("#scrWeightFrom")).getText());
            dataFrom.put(10, ((TextField) root.lookup("#scrCoordXFrom")).getText() == "" ? null : ((TextField) root.lookup("#scrCoordXFrom")).getText());
            dataFrom.put(11, ((TextField) root.lookup("#scrCoordYFrom")).getText() == "" ? null : ((TextField) root.lookup("#scrCoordYFrom")).getText());
            dataFrom.put(12, ((TextField) root.lookup("#scrCountryFrom")).getText() == "" ? null : ((TextField) root.lookup("#scrCountryFrom")).getText());
            dataFrom.put(13, ((TextField) root.lookup("#usernameFrom")).getText() == "" ? null : ((TextField) root.lookup("#usernameFrom")).getText());

            HashMap<Integer, String> dataTo = new HashMap<>();
            dataTo.put(0, ((TextField) root.lookup("#idTo")).getText() == "" ? null : ((TextField) root.lookup("#idTo")).getText());
            dataTo.put(1, ((TextField) root.lookup("#nameTo")).getText() == "" ? null : ((TextField) root.lookup("#nameTo")).getText());
            dataTo.put(2, ((TextField) root.lookup("#coordXTo")).getText() == "" ? null : ((TextField) root.lookup("#coordXTo")).getText());
            dataTo.put(3, ((TextField) root.lookup("#coordYTo")).getText() == "" ? null : ((TextField) root.lookup("#coordYTo")).getText());
            dataTo.put(4, ((TextField) root.lookup("#oscarsTo")).getText() == "" ? null : ((TextField) root.lookup("#oscarsTo")).getText());
            dataTo.put(5, ((TextField) root.lookup("#genreTo")).getText() == "" ? null : ((TextField) root.lookup("#genreTo")).getText());
            dataTo.put(6, ((TextField) root.lookup("#mpaaTo")).getText() == "" ? null : ((TextField) root.lookup("#mpaaTo")).getText());
            dataTo.put(7, ((TextField) root.lookup("#scrNameTo")).getText() == "" ? null : ((TextField) root.lookup("#scrNameTo")).getText());
            dataTo.put(8, ((DatePicker) root.lookup("#scrBirthdayTo")).getValue() == null ? null : ((DatePicker) root.lookup("#scrBirthdayTo")).getValue().toString());
            dataTo.put(9, ((TextField) root.lookup("#scrWeightTo")).getText() == "" ? null : ((TextField) root.lookup("#scrWeightTo")).getText());
            dataTo.put(10, ((TextField) root.lookup("#scrCoordXTo")).getText() == "" ? null : ((TextField) root.lookup("#scrCoordXTo")).getText());
            dataTo.put(11, ((TextField) root.lookup("#scrCoordYTo")).getText() == "" ? null : ((TextField) root.lookup("#scrCoordYTo")).getText());
            dataTo.put(12, ((TextField) root.lookup("#scrCountryTo")).getText() == "" ? null : ((TextField) root.lookup("#scrCountryTo")).getText());
            dataTo.put(13, ((TextField) root.lookup("#usernameTo")).getText() == "" ? null : ((TextField) root.lookup("#usernameTo")).getText());


            filterBtn.setStyle("-fx-background-color: #b0d6fc;");
            getFirstBtn.setStyle("-fx-background-color: #ffffff;");
            getLeastBtn.setStyle("-fx-background-color: #ffffff;");
            LinkedList<Movie> collection = getLinkedCollection();
            int movieSum = 0;
            mainTable.getItems().clear();
            for (Movie movie : collection) {
                boolean flag = true;
                if ((dataFrom.get(0) != null && movie.getId() < Long.parseLong(dataFrom.get(0))) || (dataTo.get(0) != null && movie.getId() > Long.parseLong(dataTo.get(0)))) flag = false;
                if ((dataFrom.get(1) != null && movie.getName().compareTo(dataFrom.get(1)) < 0) || (dataTo.get(1) != null && movie.getName().compareTo(dataTo.get(1)) > 0)) flag = false;
                if ((dataFrom.get(2) != null && movie.getCoordinates().getX() < Long.parseLong(dataFrom.get(2))) || (dataTo.get(2) != null && movie.getCoordinates().getX() > Long.parseLong(dataTo.get(2)))) flag = false;
                if ((dataFrom.get(3) != null && movie.getCoordinates().getY() < Long.parseLong(dataFrom.get(3))) || (dataTo.get(3) != null && movie.getCoordinates().getY() > Long.parseLong(dataTo.get(3)))) flag = false;
                if ((dataFrom.get(4) != null && movie.getOscarsCount() < Long.parseLong(dataFrom.get(4))) || (dataTo.get(4) != null && movie.getOscarsCount() > Long.parseLong(dataTo.get(4)))) flag = false;
                if ((dataFrom.get(5) != null && movie.getGenre().toString().compareTo(dataFrom.get(5)) < 0) || (dataTo.get(5) != null && movie.getGenre().toString().compareTo(dataTo.get(5)) > 0)) flag = false;
                if ((dataFrom.get(6) != null && movie.getMpaaRating().toString().compareTo(dataFrom.get(6)) < 0) || (dataTo.get(6) != null && movie.getMpaaRating().toString().compareTo(dataTo.get(6)) > 0)) flag = false;
                if ((dataFrom.get(7) != null && movie.getScreenwriter().getName().compareTo(dataFrom.get(7)) < 0) || (dataTo.get(7) != null && movie.getScreenwriter().getName().compareTo(dataTo.get(7)) > 0)) flag = false;
                if ((dataFrom.get(8) != null && movie.getScreenwriter().getBirthday().isBefore(LocalDate.parse(dataFrom.get(8)))) || (dataTo.get(8) != null && movie.getScreenwriter().getBirthday().isAfter(LocalDate.parse(dataTo.get(8))))) flag = false;
                if ((dataFrom.get(9) != null && movie.getScreenwriter().getWeight() < Long.parseLong(dataFrom.get(9))) || (dataTo.get(9) != null && movie.getScreenwriter().getWeight() > Long.parseLong(dataTo.get(9)))) flag = false;
                if ((dataFrom.get(10) != null && movie.getScreenwriter().getLocation().getX() < Long.parseLong(dataFrom.get(10))) || (dataTo.get(10) != null && movie.getScreenwriter().getLocation().getX() > Long.parseLong(dataTo.get(10)))) flag = false;
                if ((dataFrom.get(11) != null && movie.getScreenwriter().getLocation().getY() < Long.parseLong(dataFrom.get(11))) || (dataTo.get(11) != null && movie.getScreenwriter().getLocation().getY() > Long.parseLong(dataTo.get(11)))) flag = false;
                if ((dataFrom.get(12) != null && movie.getScreenwriter().getLocation().getName().compareTo(dataFrom.get(12)) < 0) || (dataTo.get(12) != null && movie.getScreenwriter().getLocation().getName().compareTo(dataTo.get(12)) > 0)) flag = false;
                if ((dataFrom.get(13) != null && movie.getLogin().compareTo(dataFrom.get(13)) < 0) || (dataTo.get(13) != null && movie.getLogin().compareTo(dataTo.get(13)) > 0)) flag = false;

                if (flag) {
                    mainTable.getItems().add(movie);
                    movieSum++;
                }
            }
            if (movieSum == collection.size()) filterBtn.setStyle("-fx-background-color: #ffffff;");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void getFirstBtnOnAction(ActionEvent event) {
        filterIsOn = true;
        LinkedList<Movie> movies = getLinkedCollection();

        Optional<Movie> result = movies.stream()
                .filter(movie -> movie.getLogin().equals(client.getLogin()))
                .findFirst();

        mainTable.getItems().clear();

        if (result.isPresent()) {
            mainTable.getItems().add(result.get());
        }
        getFirstBtn.setStyle("-fx-background-color: #b0d6fc;");
        getLeastBtn.setStyle("-fx-background-color: #ffffff;");
        filterBtn.setStyle("-fx-background-color: #ffffff;");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(bundle.getString("confirm_deleting_head_title"));
        alert.setHeaderText(bundle.getString("confirm_deleting_head"));
        alert.initModality(Modality.APPLICATION_MODAL);

        ButtonType customOkButtonT = new ButtonType(bundle.getString("confirm_btn"), ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType customCancelButtonT = new ButtonType(bundle.getString("cancel_btn"), ButtonBar.ButtonData.OK_DONE);
        alert.getDialogPane().getButtonTypes().add(customOkButtonT);
        alert.getDialogPane().getButtonTypes().add(customCancelButtonT);
        alert.getDialogPane().getButtonTypes().remove(ButtonType.CANCEL);
        alert.getDialogPane().getButtonTypes().remove(ButtonType.OK);

        alert.showAndWait().ifPresent(res -> {
            if (res == customOkButtonT) {
                System.out.println("OK button clicked");
                runtimeManager.executeCommand("remove_by_id", new String[]{String.valueOf(result.get().getId())});
            } else if (res == customCancelButtonT) {
                System.out.println("Cancel button clicked");
            }
        });
    }

    @FXML
    void getLeastBtnOnAction(ActionEvent event) {
        filterIsOn = true;
        LinkedList<Movie> movies = getLinkedCollection();

        Optional<Movie> result = movies.stream()
                .min((movie1, movie2) -> Long.compare(movie1.getId(), movie2.getId()));

        mainTable.getItems().clear();

        if (result.isPresent()) {
            mainTable.getItems().add(result.get());
        }
        getLeastBtn.setStyle("-fx-background-color: #b0d6fc;");
        getFirstBtn.setStyle("-fx-background-color: #ffffff;");
        filterBtn.setStyle("-fx-background-color: #ffffff;");
    }

    @FXML
    void infoBtnOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("info_title"));
        DefaultResponse response = (DefaultResponse) runtimeManager.executeCommand("info", new String[0]);
        String collection_type = response.getData()[0];
        String element_type = response.getData()[1];
        String creation_time = response.getData()[2];
        String modification_time = response.getData()[3];
        String elements_count = response.getData()[4];

        String info = bundle.getString("info_collection_type") + " " + collection_type + ";\n"
                + bundle.getString("info_element_type") + " " + element_type + ";\n"
                + bundle.getString("info_creation_time") + " " + creation_time + ";\n"
                + bundle.getString("info_modification_time") + " " +modification_time + ";\n"
                + bundle.getString("info_elements_count") + " " + elements_count + ";";
        alert.setHeaderText(info);
        alert.initModality(Modality.APPLICATION_MODAL);

        ButtonType customOkButtonT = new ButtonType(bundle.getString("ok_btn"), ButtonBar.ButtonData.OK_DONE);
        alert.getDialogPane().getButtonTypes().add(customOkButtonT);
        alert.getDialogPane().getButtonTypes().remove(ButtonType.OK);

        alert.showAndWait();
    }

    @FXML
    void insertBtnOnAction(ActionEvent event) {
        filterIsOn = false;
        System.out.println("Запись нового элемента в коллекцию ...");
        try {
            FXMLLoader addLoader = new FXMLLoader(Class.forName("org.example.Main").getClassLoader().getResource("add.fxml"));
            Stage secondStage = new Stage();
            addLoader.setControllerFactory(param -> new AddController(runtimeManager, secondStage));
            Parent root = addLoader.load();
            secondStage.setScene(new Scene(root));
            secondStage.setTitle(bundle.getString("add_header"));
            secondStage.showAndWait();
            fillTable();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void languageSelectorOnAction(ActionEvent event) {
        String s = (String) languageSelector.getValue();
        if (s.equals("RU")) {
            switchLanguage(new Locale("ru", "RU"));
            creationDateFormat = "dd.MM.yyyy hh:mm:ss";
            simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
            birtdayDateFormat = "dd.MM.yyyy";
            decimalFormat = new DecimalFormat("#,##0.00");
        }
        if (s.equals("IT")) {
            switchLanguage(new Locale("it", "IT"));
            creationDateFormat = "dd/MM/yyyy hh:mm:ss";
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            birtdayDateFormat = "dd/MM/yyyy";
            decimalFormat = new DecimalFormat("#,##0.00");
        }
        if (s.equals("EN")) {
            switchLanguage(new Locale("en", "EN"));
            creationDateFormat = "dd-MM-yy hh:mm:ss";
            simpleDateFormat = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
            birtdayDateFormat = "dd-MM-yy";
            decimalFormat = new DecimalFormat("###0.00");
        }
        if (s.equals("CZ")) {
            switchLanguage(new Locale("cz", "CZ"));
            creationDateFormat = "yyyy-MM-dd hh:mm:ss";
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            birtdayDateFormat = "yyyy-MM-dd";
            decimalFormat = new DecimalFormat("###0.00");
        }
        List<Movie> tempCollection = mainTable.getItems().stream().toList();
        creationDateFormatter = DateTimeFormatter.ofPattern(creationDateFormat);
        birthdayDateFormatter = DateTimeFormatter.ofPattern(birtdayDateFormat);
        mainTable.getItems().clear();
        for (Movie movie : tempCollection) mainTable.getItems().add(movie);
    }

    @FXML
    void refreshAllBtnOnAction(ActionEvent event) {
        filterBtn.setStyle("-fx-background-color: #ffffff;");
        getLeastBtn.setStyle("-fx-background-color: #ffffff;");
        getFirstBtn.setStyle("-fx-background-color: #ffffff;");
        filterIsOn = false;
        mainTable.getItems().clear();
        fillTable();
    }

    @FXML
    void refreshBtnOnAction(ActionEvent event) {
        filterIsOn = false;
        try {
            FXMLLoader addLoader = new FXMLLoader(Class.forName("org.example.Main").getClassLoader().getResource("refresh.fxml"));
            Stage secondStage = new Stage();
            addLoader.setControllerFactory(param -> new RefreshController(runtimeManager, secondStage, getLinkedCollection().stream().filter(movie -> movie.getId() == Long.parseLong(refreshMovieID.getText())).findFirst()));
            Parent root = addLoader.load();
            secondStage.setScene(new Scene(root));
            secondStage.showAndWait();
            fillTable();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void visualisationBtnOnAction(ActionEvent event) {
        LinkedList<Movie> collection = new LinkedList<>();
        for (Movie movie : mainTable.getItems()) {
            collection.add(movie);
            System.out.println(movie.getId());
        }
        VisualisationWindow window = new VisualisationWindow(bundle, collection);
        window.showAndWait();
        filterBtn.setStyle("-fx-background-color: #ffffff;");
        getLeastBtn.setStyle("-fx-background-color: #ffffff;");
        getFirstBtn.setStyle("-fx-background-color: #ffffff;");
    }

    void onDoubleClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Проверяем, что произошло двойное нажатие
            Node node = (Node) event.getSource();
            Movie selectedMovie = (Movie) mainTable.getSelectionModel().getSelectedItem();
            if (selectedMovie!= null && client.getLogin().equals(selectedMovie.getLogin())) {
                try {
                    FXMLLoader addLoader = new FXMLLoader(Class.forName("org.example.Main").getClassLoader().getResource("refresh.fxml"));
                    Stage secondStage = new Stage();
                    addLoader.setControllerFactory(param -> new RefreshController(runtimeManager, secondStage, getLinkedCollection().stream().filter(movie -> movie.getId() == Long.parseLong(refreshMovieID.getText())).findFirst()));
                    Parent root = addLoader.load();
                    secondStage.setScene(new Scene(root));
                    secondStage.showAndWait();
                    fillTable();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @FXML
    void uploadFileBtnOnAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            try {
                List<String> allLines = Files.readAllLines(Path.of(selectedFile.getAbsolutePath()));
                Response response;
                for (String line : allLines) {
                    try {
                        String[] parts = line.split(" ");
                        if (parts.length > 1) {
                            String[] args = new String[parts.length];
                            for (int i = 1; i < parts.length; ++i) args[i - 1] = parts[i];
                            args[parts.length] = "script";
                            System.out.println(parts[0]);
                            System.out.println(args);
                            response = runtimeManager.executeCommand(parts[0], args);

                        } else {
                            System.out.println(line.split(" ")[0]);
                            response = runtimeManager.executeCommand(line.split(" ")[0], new String[]{"script"});
                        }
                        if (line.split(" ")[0].equals("add")) fillTable();
                    } catch (NullPointerException e) {
                        System.out.println("Illegal command.");
                        break;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void initialize() {
        filterIsOn = false;
        this.bundle = ResourceBundle.getBundle("MAIN_Bundle", Locale.getDefault());
        languageSelector.getItems().addAll("RU", "CZ", "IT", "EN");
        languageSelector.getSelectionModel().select(bundle.getLocale().getCountry());

        TableColumn<Movie, Long> idColumn = new TableColumn<>(bundle.getString("column.0"));
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        mainTable.getColumns().add(idColumn);
        idColumn.setId("0");

        TableColumn<Movie, String> nameColumn = new TableColumn<>("column.1");
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        mainTable.getColumns().add(nameColumn);
        nameColumn.setId("1");

        TableColumn<Movie, Integer> coordX = new TableColumn<>("column.2");
        coordX.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCoordinates().getX()));
        mainTable.getColumns().add(coordX);
        coordX.setId("2");

        TableColumn<Movie, Integer> coordY = new TableColumn<>("column.3");
        coordY.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCoordinates().getY()));
        mainTable.getColumns().add(coordY);
        coordY.setId("3");

        TableColumn<Movie, String> dateTimeColumn = new TableColumn<>("column.4");
        dateTimeColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCreationDate().format(creationDateFormatter)));
        mainTable.getColumns().add(dateTimeColumn);
        dateTimeColumn.setId("4");

        TableColumn<Movie, Long> oscarsAmount = new TableColumn<>("column.5");
        oscarsAmount.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getOscarsCount()));
        mainTable.getColumns().add(oscarsAmount);
        oscarsAmount.setId("5");

        TableColumn<Movie, String> genre = new TableColumn<>("column.6");
        genre.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getGenre() == null ? null : cellData.getValue().getGenre().toString()));
        mainTable.getColumns().add(genre);
        genre.setId("6");

        TableColumn<Movie, String> MPAA = new TableColumn<>("column.7");
        MPAA.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMpaaRating().toString()));
        mainTable.getColumns().add(MPAA);
        MPAA.setId("7");

        TableColumn<Movie, String> screenwriter = new TableColumn<>("column.8");
        screenwriter.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getScreenwriter().getName()));
        mainTable.getColumns().add(screenwriter);
        screenwriter.setId("8");

        TableColumn<Movie, String> birthday = new TableColumn<>("column.9");
        birthday.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getScreenwriter().getBirthday().format(birthdayDateFormatter)));
        mainTable.getColumns().add(birthday);
        birthday.setId("9");

        TableColumn<Movie, String> weight = new TableColumn<>("column.10");
        weight.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(decimalFormat.format(cellData.getValue().getScreenwriter().getWeight())));
        mainTable.getColumns().add(weight);
        weight.setId("10");

        TableColumn<Movie, Integer> sc_coordX = new TableColumn<>("column.11");
        sc_coordX.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getScreenwriter().getLocation().getX()));
        mainTable.getColumns().add(sc_coordX);
        sc_coordX.setId("11");

        TableColumn<Movie, Long> sc_coordY = new TableColumn<>("column.12");
        sc_coordY.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getScreenwriter().getLocation().getY()));
        mainTable.getColumns().add(sc_coordY);
        sc_coordY.setId("12");

        TableColumn<Movie, String> country = new TableColumn<>("column.13");
        country.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getScreenwriter().getLocation().getName()));
        mainTable.getColumns().add(country);
        country.setId("13");

        TableColumn<Movie, String> user = new TableColumn<>("column.14");
        user.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLogin()));
        mainTable.getColumns().add(user);
        user.setId("14");

        setLabels();
        fillTable();

        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::renewTable, 0, 5, TimeUnit.SECONDS);

        mainTable.getSelectionModel().selectedItemProperty().addListener((obs, oldMovie, newMovie) -> {
            if (newMovie != null) {
//                mpaaRatingField.setText(newMovie.getMpaaRating().toString());
                if (client.getLogin().equals(newMovie.getLogin())) {
                    refreshMovieID.setText(newMovie.getId().toString());
                    deleteMovieID.setText(newMovie.getId().toString());
                } else {
                    refreshMovieID.clear();
                    deleteMovieID.clear();
                }
            }

        });

        mainTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                onDoubleClick(event); // Вызов метода, который обрабатывает двойной клик
            }
        });


    }


    public void setLabels() {
        greetingLabel.setText(bundle.getString("greetingLabel") + " " + runtimeManager.getClient().getLogin() + "!");
        ObservableList<TableColumn<Movie, ?>> columns = mainTable.getColumns();
        for (int i = 0; i < columns.size(); ++i) {
            TableColumn<?, ?> column = columns.get(i);
            String columnName = bundle.getString("column." + column.getId());
            column.setText(columnName);
        }

        insertBtn.setText(bundle.getString("insertBtn"));
        clearBtn.setText(bundle.getString("clearBtn"));
        deleteBtn.setText(bundle.getString("deleteBtn"));
        filterBtn.setText(bundle.getString("filterBtn"));
        refreshAllBtn.setText(bundle.getString("refreshAllBtn"));
        getFirstBtn.setText(bundle.getString("getFirstBtn"));
        getLeastBtn.setText(bundle.getString("getLeastBtn"));
        uploadFileBtn.setText(bundle.getString("uploadFileBtn"));
        infoBtn.setText(bundle.getString("infoBtn"));
        averageOscarsBtn.setText(bundle.getString("averageOscarsBtn"));
        refreshBtn.setText(bundle.getString("refreshBtn"));
        visualisationBtn.setText(bundle.getString("visualisationBtn"));
    }

    public void switchLanguage(Locale newLocale) {
        Locale.setDefault(newLocale);
        this.bundle = ResourceBundle.getBundle("MAIN_Bundle", newLocale);
        setLabels();
        mainTable.refresh();
    }

    public void fillTable() {
        ShowResponse response = (ShowResponse) runtimeManager.executeCommand("show", new String[0]);
        LinkedList<HashMap<Integer, String>> collection = response.getData();
        mainTable.getItems().clear();
        for (int i = 0; i < collection.size(); ++i) {
            Movie movie = new Movie(collection.get(i));
            mainTable.getItems().add(movie);
        }
    }


    public LinkedList<Movie> getLinkedCollection() {
        ShowResponse response = (ShowResponse) runtimeManager.executeCommand("show", new String[0]);
        LinkedList<HashMap<Integer, String>> collection = response.getData();
        LinkedList<Movie> movies = new LinkedList<>();
        for (int i = 0; i < collection.size(); ++i) {
            Movie movie = new Movie(collection.get(i));
            movies.add(movie);
        }
        return movies;
    }

    public void renewTable() {
        if (mainTable.getItems().size() != getLinkedCollection().size() && !filterIsOn) {
            fillTable();
        }
    }

    public String validateMovieData(HashMap<Integer, String> input) {
        HashMap<Integer, String> data = new HashMap<>();
        String errorString = "";
        int step = 0;
        try {
            while (step < 15) {
                String line = input.get(step);
                if (validatorList.get(step).validate(line)) {
                    line = line.replaceAll(">", "&gt;");
                    line = line.replaceAll("<", "&lt;");

                    if (line.equals("")) line = null;
                    data.put(step, line);
                    step++;
                } else {
                    errorString += "Неверный формат ввода.";
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
                errorString += "Введите значение в правильном формате: " + settings.get(step).getValueType();
//            }
        } catch (IllegalArgumentException e) {
//            if (!Reader.isReadingConsole()) {
//                System.out.println("Значение не из списка допустимых значений, дальнейшее выполнение скрипта невозможно.");
//                Reader.finishFile();
//            } else {
                errorString += "Введите значение из списка допустимых значений.";
//            }
        } catch (NoSuchElementException e) {
            System.out.println("В файле не хватает информации об элементе коллекции, дальнейшее выполнение скрипта невозможно.");
            Reader.finishFile();
        }

        return errorString;
    }




}

