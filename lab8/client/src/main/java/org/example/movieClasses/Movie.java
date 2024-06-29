package org.example.movieClasses;

import org.example.enums.MovieGenre;
import org.example.enums.MpaaRating;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

public class Movie implements Comparable <Movie>, Serializable {

    /**
     * Класс объекта фильма.
     */
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long oscarsCount; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле может быть null
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person screenwriter;
    private String login;

    /**
     * Конструктор по умолчанию. Необходим для парсинга входных файлов.
     */

    public Movie() {
        String name;
        Coordinates coordinates;
        long oscarsCount;
        MovieGenre genre;
        MpaaRating mpaaRating;
        Person screenwriter;
        String login;
    }

    /**
     * Конструктор, задающий параметры фильма по переданным раздельно значениям.
     * @param id
     * @param name
     * @param coordinates
     * @param creationDate
     * @param oscarsCount
     * @param genre
     * @param mpaaRating
     * @param screenwriter
     */

    public Movie(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, long oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person screenwriter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.screenwriter = screenwriter;

    }

    /**
     * Конструктор, задающий параметры фильма по данным, переданным в виде словаря.
     * @param id
     * @param data
     */

    public Movie (long id, HashMap data) {
        this.id = id;
        this.name = (String) data.get(0);
        this.coordinates = new Coordinates(Integer.parseInt(data.get(1).toString()), Integer.parseInt(data.get(2).toString()));
        this.creationDate = LocalDateTime.now();
        this.oscarsCount = Integer.parseInt(data.get(3).toString());
        if (data.get(4) == null || data.get(4) == "null") {
            this.genre = null;
        } else {
            this.genre = Enum.valueOf(MovieGenre.class, data.get(4).toString());
        }
        this.mpaaRating = Enum.valueOf(MpaaRating.class, data.get(5).toString());
        this.screenwriter = new Person((String) data.get(6), ((data.get(7) == null || data.get(7).equals("null")) ? null : LocalDate.parse(data.get(7).toString())), Double.parseDouble(data.get(8).toString()), new Location(Integer.parseInt(data.get(9).toString()), Long.parseLong(data.get(10).toString()), ((data.get(11) == null || data.get(11).equals("null") ? null : (String) data.get(11)))));
        this.login = data.get(12).toString();
    }

    public Movie (HashMap<Integer, String> data) {
        this.id = Long.valueOf(data.get(0));
        this.name = data.get(1);
        this.coordinates = new Coordinates(Integer.parseInt(data.get(2)), Integer.parseInt(data.get(3)));
        this.creationDate = LocalDateTime.parse(data.get(4));
        this.oscarsCount = Integer.parseInt(data.get(5));
        if (data.get(6) == null || Objects.equals(data.get(6), "null")) {
            this.genre = null;
        } else {
            this.genre = Enum.valueOf(MovieGenre.class, data.get(6));
        }
        this.mpaaRating = Enum.valueOf(MpaaRating.class, data.get(7));
        this.screenwriter = new Person(data.get(8), ((data.get(9) == null || data.get(9).equals("null")) ? null : LocalDate.parse(data.get(9))), Double.parseDouble(data.get(10)), new Location(Integer.parseInt(data.get(11)), Long.parseLong(data.get(12)), ((data.get(13) == null || data.get(13).equals("null") ? null : data.get(13)))));
        this.login = data.get(14);
    }
    /**
     * Метод, обновляющий значения параметров фильма.
     * @param data
     */

    public void updateMovie (HashMap data) {
        this.name = (String) data.get(0);
        this.coordinates = new Coordinates(Integer.parseInt(data.get(1).toString()), Integer.parseInt(data.get(2).toString()));
        this.creationDate = LocalDateTime.now();
        this.oscarsCount = Integer.parseInt(data.get(3).toString());
        if (data.get(4) == null) {
            this.genre = null;
        } else {
            this.genre = Enum.valueOf(MovieGenre.class, data.get(4).toString());
        }
        this.mpaaRating = Enum.valueOf(MpaaRating.class, data.get(5).toString());
        this.screenwriter = new Person((String) data.get(6), (data.get(7) != null ? LocalDate.parse(data.get(7).toString()) : null), Double.parseDouble(data.get(8).toString()), new Location(Integer.parseInt(data.get(9).toString()), Long.parseLong(data.get(10).toString()), (String) data.get(11)));
        this.login = (String) data.get(12);
    }

    /**
     * Метод, возвращающий id фильма.
     * @return
     */

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    /**
     * Метод, возвращающий название фильма.
     * @return
     */

    public String getName() {return name;}

    /**
     * Метод, возвращающий координаты фильма.
     * @return
     */

    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод, возвращающий дату записи фильма в коллекцию.
     * @return
     */

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Метод, возвращающий количество полученных за фильм премий 'Оскар' .
     * @return
     */

    public long getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Метод, возвращающий жанр фильма.
     * @return
     */

    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * Метод, возвращающий рейтинг фильма по шкале MPAA.
     * @return
     */

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Метод, возвращающий информацию о сценаристе.
     * @return
     */

    public Person getScreenwriter() {
        return screenwriter;
    }

    /**
     * Метод, возвращающий информацию о фильме в строковом представлении.
     * @return
     */

    @Override
    public String toString() {
        return "\nID: " + id.toString() + "\nНазвание: " + name + "\nКоординаты: " + coordinates.toString() + "Местное время: " + creationDate + "\nКоличество премий 'Оскар': " + oscarsCount + "\nЖанр: " + (genre == null ? "--информация отсутствует--" : genre.toString()) + "\nРейтинг MPAA: " + mpaaRating.toString() + "\nИнформация о сценаристе: " + screenwriter.toString();
    }

    /**
     * Метод, устанавливающий правило сортировки фильмов в коллекции.
     * @param o the object to be compared.
     * @return
     */

    public HashMap<Integer, String> toHashMap() {
        HashMap<Integer, String> data = new HashMap<>();
        data.put(0, this.id.toString());
        data.put(1, this.name);
        data.put(2, this.coordinates.getX().toString());
        data.put(3, this.coordinates.getY().toString());
        data.put(4, String.valueOf(this.oscarsCount));
        data.put(5, this.genre.toString());
        data.put(6, this.mpaaRating.toString());
        data.put(7, this.screenwriter.getName());
        data.put(8, this.screenwriter.getBirthday().toString());
        data.put(9, this.screenwriter.getWeight().toString());
        data.put(10, this.screenwriter.getLocation().getX().toString());
        data.put(11, this.screenwriter.getLocation().getY().toString());
        data.put(12, this.screenwriter.getLocation().getName());
        data.put(13, this.login);

        return data;
    }

    @Override
    public int compareTo(Movie o) {
        return name.compareTo(o.name);
    }
}
