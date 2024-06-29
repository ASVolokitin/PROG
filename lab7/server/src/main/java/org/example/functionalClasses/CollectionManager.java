package org.example.functionalClasses;

import org.checkerframework.checker.units.qual.C;
import org.example.enums.MovieGenre;
import org.example.enums.MpaaRating;
import org.example.movieClasses.Coordinates;
import org.example.movieClasses.Location;
import org.example.movieClasses.Movie;
import org.example.movieClasses.Person;
import org.hibernate.annotations.processing.SQL;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CollectionManager {

    /**
     * Класс, управляющий коллекцией фильмов.
     */

    private LinkedList<Movie> collection;
    private Date initializationDate;
    private Date modificationDate;
    private long lastId;
    private String inputStream;
    private final ReentrantReadWriteLock lock;

    /**
     * Конструктор объекта коллекции.
     */

    public CollectionManager() {
        this.collection = new LinkedList<>();
        this.initializationDate = new Date();
        this.modificationDate = new Date();
        this.lastId = 0;
        this.inputStream = "";
        this.lock = new ReentrantReadWriteLock();
    }

    public void fillFromDB() {
        try (Connection connection = DBUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movie;");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                collection.add(new Movie(rs.getLong(1), rs.getString(2), new Coordinates(rs.getInt(3), rs.getInt(4)), LocalDateTime.parse(rs.getString(5)), rs.getLong(6), MovieGenre.valueOf(rs.getString(7)), MpaaRating.valueOf(rs.getString(8)), new Person(rs.getString(9), LocalDate.parse(rs.getString(10)), rs.getDouble(11), new Location(rs.getInt(12), rs.getLong(13), rs.getString(14))), rs.getString(15)));
            }
            System.out.println("Коллекция в памяти восстановлена из базы данных (%d добавлено).".formatted(collection.size()));
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    /**
     * Метод, задающий коллекции пользовательские метаданные.
     * @param initializationDate
     * @param modificationDate
     * @param lastID
     */

    public void setMeta(Date initializationDate, Date modificationDate, long lastID) {
        this.initializationDate = initializationDate;
        this.modificationDate = modificationDate;
        this.lastId = lastID;
    }

    /**
     * Метод, инкрементирующий последний использованный id.
     * @return
     */

    /**
     * Метод, возвращающий количество фильмов в коллекции.
     * @return
     */

    public int getCollectionSize() {
        return collection.size();
    }

    /**
     * Метод, возвращающий тип данных коллекции.
     * @return
     */

    public Class getType() {
        return collection.getClass();
    }

    /**
     * Метод, возвращающий последний использованный в коллекции id.
     * @return
     */

    public long getLastId() {
        return lastId;
    }

    /**
     * Метод, возвращающий дату и время инициализации коллекции.
     * @return
     */

    public Date getInitializationDate() {
        return initializationDate;
    }

    /**
     * Метод, возвращающий дату и время последнего обновления коллекции.
     * @return
     */

    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * Метод, возвращающий источник ввода информации.
     * @return
     */

    public String getInputStream() {return inputStream; }

    /**
     * Метод, устанавливающий источник ввода информации.
     * @param inputStream
     */

    public void setInputStream(String inputStream) {this.inputStream = inputStream; }

    /**
     * Метод, добавляющий в коллекцию новый элемент.
     * @param movie
     */

    public void add(Movie movie, String login) {
        try(Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO movie(id, name, coordinate_x, coordinate_y, local_date_time, oscars_count, genre, rating, screenwriter_name, birthday, weight, location_x, location_y, location_name, visitor_login) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setInt(2, movie.getCoordinates().getX());
            preparedStatement.setInt(3, movie.getCoordinates().getY());
            preparedStatement.setString(4, movie.getCreationDate().toString());
            preparedStatement.setLong(5, movie.getOscarsCount());
            preparedStatement.setString(6, movie.getGenre().toString());
            preparedStatement.setString(7, movie.getMpaaRating().toString());
            preparedStatement.setString(8, movie.getScreenwriter().getName());
            preparedStatement.setString(9, movie.getScreenwriter().getBirthday().toString());
            preparedStatement.setDouble(10, movie.getScreenwriter().getWeight());
            preparedStatement.setInt(11, movie.getScreenwriter().getLocation().getX());
            preparedStatement.setLong(12, movie.getScreenwriter().getLocation().getY());
            preparedStatement.setString(13, movie.getScreenwriter().getLocation().getName());
            preparedStatement.setString(14, movie.getLogin());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("SELECT currval(pg_get_serial_sequence('movie', 'id')) AS currval");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) movie.setId(rs.getLong("currval"));
            lock.writeLock().lock();
            collection.add(movie);
            lock.writeLock().unlock();
            this.modificationDate = new Date();
            String line = movie.getName();
            line = line.replaceAll("&gt;", ">");
            line = line.replaceAll("&lt;", "<");
            System.out.println("Информация о фильме '" + line + "' сохранена.");
            } catch (SQLException e) {
            System.out.println("Ошибка отправки SQL-запроса: " + e.getMessage());
        }
    }

    /**
     * Метод, возвращающий элемент коллекции по его id.
     * @param id
     * @return
     */

    public Movie getById(long id) {
        return collection.stream().filter(movie -> movie.getId() == id).findFirst().orElse(null);
    }

    /**
     * Метод, возвращающий содержимое коллекции.
     * @return
     */

    public LinkedList<Movie> getCollection() {
        return new LinkedList<>(collection);}

    /**
     * Метод, возвращающий коллекцию фильмов, остортированных по названию.
     * @return
     */

    public LinkedList<Movie> getSortedCollection() {
        LinkedList<Movie> sortedList = new LinkedList<>(collection);
        Collections.sort(sortedList);
        return sortedList;
    }

    /**
     * Метод, обновляющий поля элемента коллекции по его id.
     * @param movie
     * @return
     */

    public void updateById(Movie movie) {
        try(Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE movie SET name = ?, coordinate_x = ?, coordinate_y = ?, local_date_time = ?, oscars_count = ?, genre = ?, rating = ?, screenwriter_name = ?, birthday = ?, weight = ?, location_x = ?, location_y = ?, location_name = ?, visitor_login = ? WHERE id = ?");
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setInt(2, movie.getCoordinates().getX());
            preparedStatement.setInt(3, movie.getCoordinates().getY());
            preparedStatement.setString(4, movie.getCreationDate().toString());
            preparedStatement.setLong(5, movie.getOscarsCount());
            preparedStatement.setString(6, movie.getGenre().toString());
            preparedStatement.setString(7, movie.getMpaaRating().toString());
            preparedStatement.setString(8, movie.getScreenwriter().getName());
            preparedStatement.setString(9, movie.getScreenwriter().getBirthday().toString());
            preparedStatement.setDouble(10, movie.getScreenwriter().getWeight());
            preparedStatement.setInt(11, movie.getScreenwriter().getLocation().getX());
            preparedStatement.setLong(12, movie.getScreenwriter().getLocation().getY());
            preparedStatement.setString(13, movie.getScreenwriter().getLocation().getName());
            preparedStatement.setString(14, movie.getLogin());
            preparedStatement.setLong(15, movie.getId());
            preparedStatement.executeUpdate();
            lock.writeLock().lock();
            collection.removeIf(x -> x.getId().equals(movie.getId()));
            collection.add(movie);
            lock.writeLock().unlock();
            this.modificationDate = new Date();
            String line = movie.getName();
            line = line.replaceAll("&gt;", ">");
            line = line.replaceAll("&lt;", "<");
            System.out.println("Информация о фильме '" + line + "' сохранена.");
        } catch (SQLException e) {
            System.out.println("Ошибка отправки SQL-запроса: " + e.getMessage());
        }
    }

    /**
     * Метод, очищающий коллекцию.
     */

    public void clear(String login) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName());
        try(Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM movie WHERE visitor_login='%s'".formatted(login));
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS amount FROM movie;");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int amount = rs.getInt("amount");
                if (amount == 0) {
                    System.out.println("База данных пуста, счётчик serial перезапущен.");
                    connection.prepareStatement("TRUNCATE movie RESTART IDENTITY CASCADE").executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Не удалось отчистить базу данных: " + e.getMessage());
        }
        collection.removeIf(movie -> movie.getLogin().equals(login));
        initializationDate = new Date();
        modificationDate = new Date();
        lock.writeLock().unlock();
    }

    /**
     * Метод, удаляющий из коллекции элемент по его значению.
     * @param id
     * @return
     */
    public String removeById(long id, String login) {
        lock.writeLock().lock();
        try(Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM movie WHERE id=%d AND visitor_login='%s'".formatted(id, login));
            preparedStatement.executeUpdate();
            long checkIDExistance = collection.stream().filter(movie ->  movie.getId().equals(id)).count();
            if (checkIDExistance == 0) return "Элемента с таким ID нет в коллекции.";
            long checkLogin = collection.stream().filter(movie ->  movie.getId().equals(id)  && movie.getLogin().equals(login)).count();
            if (checkLogin == 0) return "У Вас нет доступа к этому элементу.";
            collection.removeIf(movie -> movie.getId().equals(id) && movie.getLogin().equals(login));
            return "Фильм с индексом %s успешно удалён.".formatted(id);
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
        lock.writeLock().unlock();
        return "Не удалось удалить фильм с указанным ID.";
    }

    /**
     * Метод, возвращающий первый элемент коллекции.
     * @return
     */

    public Movie getFirst() {
        return collection.peekFirst();
    }


    /**
     * Метод, возвращающий максимальное количест
     * @return
     */

    public long getMaxOscarsCount() {
        if (collection.isEmpty()) return -1;
        long maxOscarsCount = collection.getFirst().getOscarsCount();
        for (Movie movie : collection) {
            if (movie.getOscarsCount() > maxOscarsCount) maxOscarsCount = movie.getOscarsCount();
        }
        return maxOscarsCount;
    }


}
