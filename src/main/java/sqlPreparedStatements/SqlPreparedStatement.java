package sqlPreparedStatements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class SqlPreparedStatement {
    private PreparedStatement preparedStatement;
    final private String createTableMovieString = """
            CREATE TABLE IF NOT EXISTS movie (
            id int AUTO_INCREMENT PRIMARY KEY,
            title VARCHAR(255) NOT NULL,
            premierYear int NOT NULL,
            genre VARCHAR(255) NOT NULL,
            rate int
            );
            """;

    final private  String addToTableMovieString = "INSERT INTO movie VALUES(0,?,?,?,?);";

    final private String getAllMoviesString = """
            SELECT * FROM movie;
            """;
    public void createTableMovie(Connection c) throws SQLException {
        preparedStatement = c.prepareStatement(createTableMovieString);
        preparedStatement.execute(createTableMovieString);
    }

    public void addToTableMovie(Connection c, String title, int premiereYear, String genre, int rate) throws SQLException {
        preparedStatement = c.prepareStatement(addToTableMovieString);
        preparedStatement.setString(1, title);
        preparedStatement.setInt(2, premiereYear);
        preparedStatement.setString(3, genre);
        preparedStatement.setInt(4, rate);
        preparedStatement.execute();
    }

    public void getAllMoviesString(Connection c) throws SQLException {
        preparedStatement = c.prepareStatement(getAllMoviesString);
        preparedStatement.executeQuery(getAllMoviesString);

    }
}
