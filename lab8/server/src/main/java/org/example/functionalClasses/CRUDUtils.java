package org.example.functionalClasses;

import org.example.enums.MovieGenre;
import org.example.enums.MpaaRating;
import org.example.movieClasses.Coordinates;
import org.example.movieClasses.Location;
import org.example.movieClasses.Movie;
import org.example.movieClasses.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

//    public static List<Movie> getMovieData(String query) {
//        List<Movie> movies = new ArrayList<>();
//        try (Connection connection = DBUtils.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                Long id = rs.getLong("id");
//                String name = rs.getString("name");
//                int coordinateX = rs.getInt("coordinateX");
//                int  coordinateY = rs.getInt("coordinateY");
//                LocalDateTime time = LocalDateTime.parse(rs.getString("local_date_time"));
//                Long oscarsCount = rs.getLong("oscars_count");
//                MovieGenre genre = MovieGenre.valueOf(rs.getString("genre"));
//                MpaaRating rating = MpaaRating.valueOf(rs.getString("rating"));
//                String screenwriterName = rs.getString("screenwriter_name");
//                LocalDate birthday = LocalDate.parse(rs.getString("birthday"));
//                double weight = rs.getDouble("weight");
//                int locationX = rs.getInt("locationX");
//                long locationY = rs.getLong("locationY");
//                String locationName = rs.getString("location_name");
//                movies.add(new Movie(id, name, new Coordinates(coordinateX, coordinateY), time, oscarsCount, genre, rating, new Person(screenwriterName, birthday, weight, new Location(locationX, locationY, locationName))));
//            }
//        } catch (SQLException e) {
//            System.out.println("Не удалось выполнить SQL-запрос: " + e.getMessage());
//        }
//        return movies;
//    }
}
