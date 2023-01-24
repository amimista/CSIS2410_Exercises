package sqlDerby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Demonstrate how to create and access a SQL database.
 *
 * @author Marcus
 */
public class demoDatabase {
    public static String sqlCreateStudentTable = """
                    CREATE TABLE Student (
                        StudentID int,
                        LastName varchar(255),
                        FirstName varchar(255),
                        Major varchar(255),
                        GradYear int)
                    """;

    private static String sqlFillStudentTable = "INSERT INTO Student "
            + "(StudentID, LastName, FirstName, Major, GradYear) VALUES "
            + "(1234, 'Moor', 'Don', 'EE', 2023), "
            + "(1235, 'Bell', 'Jen', 'CS', 2022), "
            + "(1236, 'Bush', 'Tim', 'FA', 2025), "
            + "(1237, 'Cole', 'Rob', 'CS', 2024)"
            ;

    private static String sqlDropStudentTable = "DROP TABLE Student";
    public static void main(String[] args) {
        String  databaseURL = "jdbc:derby:FirstDatabase;create=true";

        try(Connection connection = DriverManager.getConnection(databaseURL);
            Statement statement = connection.createStatement()){
//            statement.execute(sqlDropStudentTable);
//            statement.execute(sqlCreateStudentTable);
            statement.execute(sqlFillStudentTable);

        } catch (SQLException e) {
            System.out.println("There was a problem accessing the database");
            e.printStackTrace();
        }

        System.out.println("done.");
    }
}
