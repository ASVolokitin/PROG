package org.example.functionalClasses;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Settings {

    /**
     * Класс, определяющий формат полей данных ввода.
     */
    private final ArrayList<SetValues> settings = new ArrayList<>();

    /**
     * Конструктор списка форматов полей ввода.
     */

    public Settings() {
        settings.add(new SetValues(0, "String", true, "Название фильма. Непустая строка"));
        settings.add(new SetValues(1, "Integer", true, "Координата Х. Целое число, больше -252"));
        settings.add(new SetValues(2, "Integer", true, "Координата Y. Целое число"));
        settings.add(new SetValues(3, "Long", true, "Количество премий 'Оскар'. Целое неотрицательное число"));
        settings.add(new SetValues(4, "MovieGenre", false, "Жанр фильма на выбор: 'ACTION', 'WESTERN, 'DRAMA', 'ADVENTURE', 'FANTASY'"));
        settings.add(new SetValues(5, "MpaaRating", true, "Рейтинг фильма по шкале MPAA ('G', 'PG', 'PG_13', 'R', 'NC_17')"));
        settings.add(new SetValues(6, "String", true, "Укажите имя сценариста"));
        settings.add(new SetValues(7, "LocalDate", false, "Дата рождения. Формат yyyy-mm-dd"));
        settings.add(new SetValues(8, "Double", true, "Вес сценариста (можно дробное)"));
        settings.add(new SetValues(9, "Integer", true, "Координата X места рождения сценариста. Целое число"));
        settings.add(new SetValues(10, "Long", true, "Координата Y места рождения сценариста. Целое число"));
        settings.add(new SetValues(11, "String", true, "Страна происхождения сценариста"));
    }

    /**
     * Метод, возвращающий список форматов полей ввода.
     * @return
     */

    public ArrayList<SetValues> getSettings() {return settings; }
}
