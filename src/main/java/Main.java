import sqlPreparedStatements.SqlPreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/movies",
                "root",
                "jakubowy22");

        SqlPreparedStatement sqlPreparedStatement = new SqlPreparedStatement();

        sqlPreparedStatement.createTableMovie(connection);
        sqlPreparedStatement.addToTableMovie(connection,
                "Piła",
                2000,
                "horror",
                5
        );
        sqlPreparedStatement.getAllMoviesString(connection);
    }
}
