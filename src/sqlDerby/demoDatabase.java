package sqlDerby;

import java.sql.*;

/**
 * Demonstrate how to create and access a SQL database.
 *
 * @author Marcus
 */
public class demoDatabase {
    //    private static StringBuilder builder;
    public static void main(String[] args) {
        String databaseURL = "jdbc:derby:FirstDatabase;create=true";

        try (Connection connection = DriverManager.getConnection(databaseURL);
             Statement statement = connection.createStatement()) {

//            Student Table
            statement.execute(SqlStudent.dropTable());
            statement.execute(SqlStudent.createTable());
            statement.execute(SqlStudent.fillTable());
            printTableData(statement, SqlStudent.selectData());

//            College Table
            statement.execute(SqlCollege.dropTable());
            statement.execute(SqlCollege.createTable());
            statement.execute(SqlCollege.fillTable());
            printTableData(statement, SqlCollege.selectData());

//            StudentCollege Table
            statement.execute(SqlStudentCollege.dropTable());
            statement.execute(SqlStudentCollege.createTable());
            statement.execute(SqlStudentCollege.fillTable());
            printTableData(statement, SqlStudentCollege.selectData());

            printTableData(statement, SqlStudentCollege.studentCollegeInfo());

            System.out.println("Done.");
        } catch (SQLException e) {
            System.out.println("There was a problem accessing the database");
            e.printStackTrace();
        }

    }

    /**
     * Prints data contained in a SQL Table
     *
     * @param statement object that is used to change and update a SQL database.
     * @param action    String` that describes what kind of query desired.
     * @throws SQLException because yeah
     */
    private static void printTableData(Statement statement, String action) throws SQLException {
        ResultSet resultSet = statement.executeQuery(action);
        ResultSetMetaData meta = resultSet.getMetaData();
//      Print Header
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.print(meta.getColumnLabel(i) + " ");
        }
//      Print Data
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= meta.getColumnCount(); i++) { // there isn't a 0th index in SQL.
                System.out.printf("%-" + meta.getColumnLabel(i).length() + "s ", resultSet.getObject(i).toString());
                // TODO get the length of whichever is longest; columnLabel or fields.
            }
            System.out.println();
        }
        System.out.println();
    }
}
