//package org.example.windows;
//
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.effect.BlendMode;
//import javafx.scene.effect.GaussianBlur;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import org.example.movieClasses.Movie;
//
//import java.awt.*;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Random;
//import java.util.ResourceBundle;
//
//public class VisualisationWindow extends Stage {
//
//    private LinkedList<Movie> movies;
//    private ResourceBundle bundle;
//    private HashMap<String, Color> colors = new HashMap<>();
//    private final int RADIUS = 70;
//    private final int WINDOW_WIDTH = 800;
//    private final int WINDOW_HEIGHT = 600;
//    private Group root = new Group();
//    private final int ANIM_LENGTH = 500;
//    private final double ANIM_STEP = 0.001;
//    private double curve = ANIM_STEP * 0.01;
//
//    public VisualisationWindow(ResourceBundle bundle, LinkedList<Movie> movies) {
//        this.bundle = bundle;
//        this.movies = movies;
////        ANIM_LENGTH = movies.size() * 10;
//        setTitle(bundle.getString("anim_title"));
//
//
//        GaussianBlur blur = new GaussianBlur();
//        blur.setRadius(ANIM_LENGTH / 50);
//
//        for (Movie movie : movies) {
//            if (!colors.containsKey(movie.getLogin())) colors.put(movie.getLogin(), getRandomColor());
//            Circle circle = new Circle(RADIUS);
//            circle.setCenterX((movie.getCoordinates().getX() + Math.random() * 300) % (WINDOW_WIDTH - 3 * RADIUS) + RADIUS);
//            circle.setCenterY((movie.getCoordinates().getY() + Math.random() * 300) % (WINDOW_HEIGHT - 3 * RADIUS) + RADIUS);
//            Text name = new Text(movie.getLogin());
//            name.setX(circle.getCenterX() - (name.getLayoutBounds().getWidth() / 2));
//            name.setY(circle.getCenterY());
//            name.setTextAlignment(TextAlignment.CENTER);
//            name.setFont(Font.font("Sitka Display Bold", 14));
//            name.setFill(colors.get(movie.getLogin()).invert());
////            name.setBlendMode(BlendMode.DIFFERENCE);
//            circle.setFill(colors.get(movie.getLogin()));
//            circle.setEffect(blur);
////            circle.setStroke(colors.get(movie.getLogin()).darker());
//            circle.setBlendMode(BlendMode.DARKEN);
//
//            circle.setSmooth(true);
//
//            root.getChildren().addAll(circle, name);
//
////            pane.getChildren().add(circle);
//
//            double curve = ANIM_STEP;
//            Timeline timeline = new Timeline(
//                    new KeyFrame(Duration.seconds(ANIM_STEP), e -> {
//                        circle.setRadius(circle.getRadius() - curveCalc());
//                        blur.setRadius(blur.getRadius() - ANIM_STEP);
//                        System.out.println(blur.getRadius());
//                        circle.setEffect(blur);
////
//                    })
//            );
//            timeline.setCycleCount(ANIM_LENGTH);
//            timeline.play();
//        }
//
////        for (int i = 0; i < 5; i++) { // Создаем 5 объектов
////            Circle circle = new Circle(RADIUS);
////            circle.setCenterX(pane.getMaxWidth() / 2); // X координата внутри круга
////            rectangle.setY(movies); // Y координата внутри круга
////            rectangle.setFill(Color.BLUE); // Цвет объектов
////            root.getChildren().add(rectangle);
////        }
//
//        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
//        setScene(scene);
//    }
//
//
//    private Color getRandomColor() {
//        int r = (int) (Math.random() * 256);
//        int g = (int) (Math.random() * 256);
//        int b = (int) (Math.random() * 256);
//        return Color.rgb(r, g, b);
//    }
//
//    private Text createUserLabel(Movie movie) {
//        Text label = new Text(String.valueOf(movie.getId()));
//        label.setFont(new Font("Sitka Display", 12)); // Установка шрифта и размера текста
//        return label;
//    }
//
//    private Color getColorById(String id) {
//        // Простой способ генерации уникальных цветов - использовать хэш код id
//        int hash = id.hashCode();
//        int r = Math.abs(hash % 256);
//        int g = Math.abs((hash / 256) % 256);
//        int b = Math.abs(hash / 65536 % 256);
//        return Color.rgb(r, g, b);
//    }
//
//    private double curveCalc() {
//        curve += ANIM_STEP * 0.1;
//        return curve*curve;
//    }
//
//}


package org.example.windows;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.movieClasses.Movie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;

public class VisualisationWindow extends Stage {

    private LinkedList<Movie> movies;
    private ResourceBundle bundle;
    private HashMap<String, Color> colors = new HashMap<>();
    private final int RADIUS = 70;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private Group root = new Group();
    private final int ANIM_LENGTH;
    private final double ANIM_STEP;
    private double curve;
    private LinkedList<Circle> circles = new LinkedList<>();

    public VisualisationWindow(ResourceBundle bundle, LinkedList<Movie> movies) {
        this.bundle = bundle;
        this.movies = movies;
        ANIM_STEP = 0.0001;
        ANIM_LENGTH = 2500;
        curve = 3;
        setTitle(bundle.getString("anim_title"));

        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(ANIM_LENGTH / 50);

        for (Movie movie : movies) {
            if (!colors.containsKey(movie.getLogin())) colors.put(movie.getLogin(), getRandomColor());
            Circle circle = new Circle(RADIUS);
            circle.setCenterX((movie.getCoordinates().getX() + Math.random() * 300) % (WINDOW_WIDTH - 3 * RADIUS) + RADIUS);
            circle.setCenterY((movie.getCoordinates().getY() + Math.random() * 300) % (WINDOW_HEIGHT - 3 * RADIUS) + RADIUS);
            Text name = new Text(movie.getLogin());
            name.setX(circle.getCenterX() - (name.getLayoutBounds().getWidth() / 2));
            name.setY(circle.getCenterY());
            name.setTextAlignment(TextAlignment.CENTER);
            name.setFont(Font.font("Sitka Display Bold", 14));
            name.setFill(colors.get(movie.getLogin()).invert());
            name.setBlendMode(BlendMode.MULTIPLY);
            circle.setFill(colors.get(movie.getLogin()));
            circles.add(circle);
//            circle.setEffect(blur);
            circle.setBlendMode(BlendMode.DARKEN);
            circle.setSmooth(true);

            root.getChildren().addAll(circle, name);
        }

        // Создание одного общего Timeline для анимации всех элементов
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(ANIM_STEP), e -> {
                    blur.setRadius(blur.getRadius() - 0.02);
                    for (Circle circle: circles) {
                        if (circle!= null) {
                            circle.setRadius(circle.getRadius() - 0.0001 * curveCalc());
//                            System.out.println("radius: " + circle.getRadius());
//                            System.out.println("blur: " + blur.getRadius());
                            circle.setEffect(blur);
                        }
                    }
                })
        );
        timeline.setCycleCount(ANIM_LENGTH);
        timeline.play();

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        setScene(scene);
    }

    private Color getRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return Color.rgb(r, g, b);
    }

    private Circle findCircleByLogin(String login) {
        // Реализуйте логику поиска круга по логину
        // Например, можно сохранять ссылки на круги в HashMap
        return null;
    }

    private double curveCalc() {
        curve *= (curve % 10);
        return curve;
    }
}