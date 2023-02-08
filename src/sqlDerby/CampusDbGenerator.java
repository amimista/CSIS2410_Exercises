package sqlDerby;

import java.sql.*;

/**
 * Provides methods to create database table with or without
 * dropping the tables first.
 * @author Marcus
 */
public class CampusDbGenerator {
    public static final String DB_URL = "jdbc:derby:FirstDatabase;create=true";

    public static void createStudentTable(boolean drop) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            if (drop)
                statement.execute(SqlStudent.dropTable());

            statement.execute(SqlStudent.createTable());
            statement.execute(SqlStudent.fillTable());
        } catch (SQLException e) {
            System.out.println("There was a problem creating Student table");
            e.printStackTrace();
        }
    }

    public static void createCollegeTable(boolean drop) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            if (drop)
                statement.execute(SqlCollege.dropTable());

            statement.execute(SqlCollege.createTable());
            statement.execute(SqlCollege.fillTable());
        } catch (SQLException e) {
            System.out.println("There was a problem creating College table");
            e.printStackTrace();
        }
    }

    public static void createStudentCollegeTable(boolean drop) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            if (drop)
                statement.execute(SqlStudentCollege.dropTable());

            statement.execute(SqlStudentCollege.createTable());
            statement.execute(SqlStudentCollege.fillTable());
        } catch (SQLException e) {
            System.out.println("There was a problem creating StudentCollege table");
            e.printStackTrace();
        }
    }

    public static void createAllTables(boolean drop) {
        createStudentTable(drop);
        createCollegeTable(drop);
        createStudentCollegeTable(drop);

    }
}
