package org.example.functionalClasses;

import org.example.movieClasses.Movie;

import java.nio.file.Paths;
import java.util.*;

public class CollectionManager {

    /**
     * Класс, управляющий коллекцией фильмов.
     */

    private LinkedList<Movie> collection;
    private Date initializationDate;
    private Date modificationDate;
    private long lastId;
    private String inputStream;

    /**
     * Конструктор объекта коллекции.
     */

    public CollectionManager() {
        this.collection = new LinkedList<>();
        this.initializationDate = new Date();
        this.modificationDate = new Date();
        this.lastId = 0;
        this.inputStream = "";
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

    public long incrementId() { return ++lastId; }

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

    public void add(Movie movie) {
        collection.add(movie);
        this.modificationDate = new Date();
        String line = movie.getName();
        line = line.replaceAll("&gt;", ">");
        line = line.replaceAll("&lt;", "<");
        System.out.println("Информация о фильме '" + line + "' сохранена.");
    }

    /**
     * Метод, возвращающий элемент коллекции по его id.
     * @param id
     * @return
     */

    public Movie getById(long id) {
        for (Movie movie : collection) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Метод, возвращающий содержимое коллекции.
     * @return
     */

    public LinkedList<Movie> getCollection() {
        return new LinkedList<>(collection);
    }

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
     * @param id
     * @param movie
     * @return
     */

    public boolean updateById(long id, Movie movie) {
        for (Movie curmovie : collection) {
            if (curmovie.getId() == id) {
                collection.remove(curmovie);
                collection.add(movie);
                this.modificationDate = new Date();
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, очищающий коллекцию.
     */

    public void clear() {
        collection.clear();
        initializationDate = new Date();
        modificationDate = new Date();
        lastId = 0;
    }

    /**
     * Метод, удаляющий из коллекции элемент по его значению.
     * @param id
     * @return
     */

    public boolean removeById(long id) {
        for (Movie movie : collection) {
            if (movie.getId() == id) {
                collection.remove(movie);
                modificationDate = new Date();
                return true;
            }
        }
        return false;
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
