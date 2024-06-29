package org.example.functionalClasses;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Settings {

    /**
     * Класс, определяющий формат полей данных ввода.
     */

    private ResourceBundle bundle;
    private final ArrayList<SetValues> settings = new ArrayList<>();

    /**
     * Конструктор списка форматов полей ввода.
     */

    public Settings() {
        this.bundle = ResourceBundle.getBundle("MAIN_Bundle", Locale.getDefault());
        settings.add(new SetValues(0, "String", true, bundle.getString("settings_movie_title")));
        settings.add(new SetValues(1, "Integer", true, bundle.getString("settings_coordX")));
        settings.add(new SetValues(2, "Integer", true, bundle.getString("settings_coordY")));
        settings.add(new SetValues(3, "Long", true, bundle.getString("settings_oscars_amount")));
        settings.add(new SetValues(4, "MovieGenre", false, bundle.getString("settings_movie_genre")));
        settings.add(new SetValues(5, "MpaaRating", true, bundle.getString("settings_movie_mpaa_rating")));
        settings.add(new SetValues(6, "String", true, bundle.getString("settings_movie_scr_name")));
        settings.add(new SetValues(7, "LocalDate", false, bundle.getString("settings_movie_scr_birthday")));
        settings.add(new SetValues(8, "Double", true, bundle.getString("settings_movie_scr_weight")));
        settings.add(new SetValues(9, "Integer", true, bundle.getString("settings_movie_scr_coordX")));
        settings.add(new SetValues(10, "Long", true, bundle.getString("settings_movie_coordY")));
        settings.add(new SetValues(11, "String", true, bundle.getString("settings_movie_scr_location")));
    }

    /**
     * Метод, возвращающий список форматов полей ввода.
     * @return
     */

    public ArrayList<SetValues> getSettings() {return settings; }
}
