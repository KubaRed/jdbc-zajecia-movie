import jdbc.Book;

import java.sql.*;

public class MainOld {
    public static void main(String[] args) throws SQLException {
        //create database books;
        //Wersja dla MySql
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "jakubowy22");
        String createDataBaseSql = "create database movies";
        //PreparedStatement preparedStatement = connection.prepareStatement(createDataBaseSql);
        if(!connection.isValid(5)){
            //preparedStatement.execute();
        }

        // DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb",
        // "jdbc:mysql://localhost:3306/sonoo","root","root"
        //           "postgres", "MyPassword123");

        //tabela na ksiazki
        String createTableSql = """
                CREATE TABLE books (
                id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                title VARCHAR(255) NOT NULL,
                author VARCHAR(255) NOT NULL,
                pages int NOT NULL
                );
                """;

//        connection.createStatement().execute(createTableSql);

        //dodanie ksiazki:
        Book book = new Book("Harry Potter", "J.K.Rowling", 380);
        Statement statement = connection.createStatement();

        //formatyowany String - patrz reminder
        String insertBookSql = String.format("INSERT INTO books VALUES (0, '%s', '%s', %d);",
                book.getTitle(), book.getAuthor(), book.getPages());

        System.out.println(insertBookSql);
        //statement.execute(insertBookSql);

        //UPDATE:
        String updateBook = """
                UPDATE books
                SET author = 'Philip K.'
                WHERE title = 'Harry Potter'
                """;

        //statement.execute(updateBookSql);

        //READ:
        String selectAllSql = "SELECT * FROM books";
        ResultSet resultSet = statement.executeQuery(selectAllSql);
        while (resultSet.next()) {
            resultSet.next();
            int id = resultSet.getInt("id");
            System.out.println(id);
            String title = resultSet.getString("title");
            System.out.println(title);
            String author = resultSet.getString("author");
            System.out.println(author);
            int pages = resultSet.getInt("pages");
            System.out.println(pages);

            Book dbBook = new Book(id, title, author, pages);
            System.out.println(dbBook);
        }


        //DELETE
        String deleteSql = "DELETE FROM * books WHERE ID = 1";
        //statement.execute(deleteSql);

        //aby uniknąć SQL injecction attack - zawsze należy używać PreparedStatement a nie Statement
        String insertSql = "INSERT INTO books VALUES(0,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, "Czysta Architektura");
        preparedStatement.setString(2, "Robert Martin");
        preparedStatement.setInt(3, 350);
        preparedStatement.execute();

        //Zadanie 2.
//        //String createTableGame = """
//                CREATE TABLE game (
//                id int AUTO_INCREMENT PRIMARY KEY,
//                title VARCHAR(255) NOT NULL,
//                price int NOT NULL
//                platform VARCHAR(255) NOT NULL
//                );
//                """;
////       //preparedStatement = connection.prepareStatement(createTableSql);
////       //preparedStatement.execute();
////       //String addGameSql = """
////                //INSERT INTO game (id, title, price, platform)
////                // (0,?,?,?);
////                """;
////        //preparedStatement.setString(1, "World of Warcraft");
////        //preparedStatement.setString(2, "200.0");
//        //preparedStatement.setString(3, "PC");
//        //preparedStatement.execute();


        /*Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb",
                "postgres", "MyPassword123");

        connection.createStatement().execute("create table test(num int);");*/
    }
}

//Bazy danych:
// SQL / NoSQL (np. MongoDB)

//1. Samodzielny SQL
//2. JDBC - java wysyła zapytania SQL
//zainstalowana i odpalona baza
//sterownik (maven)
//-miejsce na pisanie kodu - main?
//Uzyskujemy Connection przez DriverManager
//3. Hibernate - zapisujemy OBIEKTY w bazie danych (ORM)
//4. Spring JPA - automatyzacja pracy z Hibernate przy Spring


