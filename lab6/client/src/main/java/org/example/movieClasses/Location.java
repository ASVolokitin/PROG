package org.example.movieClasses;

public class Location {

    /**
     * Класс места рождения сценариста.
     */

    private Integer x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private String name; //Поле может быть null

    /**
     * Конструктор по умолчанию. Необходим для парсинга входных файлов.
     */

    public Location() {
        Integer x;
        Long y;
        String name;
    }

    /**
     * Констуктор, устанавливающий координаты и название места.
     * @param x
     * @param y
     * @param name
     */

    public Location(Integer x, Long y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
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

    public Long getY() {
        return y;
    }

    /**
     * Метод, возвращающий название места.
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий информацию о месте в строковом представлении.
     * @return
     */

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ", " + ((name == null || name.equals("null") || name.equals("")) ? "--информация отсутствует--" : name) + ")";

    }
}
