package org.example.movieClasses;

public class Coordinates {

    /**
     * Класс координат фильма.
     */

    private Integer x; //Значение поля должно быть больше -252, Поле не может быть null
    private Integer y; //Поле не может быть null

    /**
     * Конструктор по умолчанию. Необходим для парсинга входных файлов.
     */

    public Coordinates() {
        Integer x;
        Integer y;
    }

    /**
     * Конструктор, устанавливающий координаты.
     * @param x
     * @param y
     */

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод, возвращающий координату X.
     * @return
     */

    public Integer getX() {
        return x;
    }

    /**
     * Метод, возвращающий координату Y.
     * @return
     */

    public Integer getY() {
        return y;
    }

    /**
     * Метод, возвращающий координаты в строковом представлении.
     * @return
     */

    @Override
    public String toString() {
        return "(" + x.toString() + ", " + y.toString() + ")\n";
    }
}
