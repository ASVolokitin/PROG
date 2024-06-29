//package org.example.commands;
//
//import org.example.functionalClasses.CollectionManager;
//import org.example.movieClasses.Movie;
//
//public class AverageOfOscarsCount extends Command {
//
//    /**
//     * Команда average_of_oscars_count. Выводит в консоль среднее значение количества премий 'Оскар' по всем фильмам коллекции.
//     */
//
//    private CollectionManager collectionManager;
//
//    /**
//     * Конструктор объекта команды AverageOfOscarsAmount
//     * @param collectionManager
//     */
//
//    public AverageOfOscarsCount(CollectionManager collectionManager) {
//        super("average_of_oscars_count", "Вывести среднее значение поля oscarsCount для всех элементов коллекции.");
//        this.collectionManager = collectionManager;
//    }
//
//    /**
//     * Метод, запускающий выполнение команды.
//     * @param arg
//     */
//
//    @Override
//    public void execute(String arg) {
//        System.out.print("Среднее значение по количеству премий 'Оскар' для элементов коллекции: ");
//        if (collectionManager.getCollectionSize() == 0) {
//            System.out.println(0);
//            return;
//        }
//        double aver = 0;
//        for (Movie movie : collectionManager.getCollection()) {
//            aver += movie.getOscarsCount();
//        }
//        System.out.println(aver / collectionManager.getCollectionSize());
//    }
//}
