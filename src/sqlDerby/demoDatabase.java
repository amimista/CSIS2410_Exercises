package sqlDerby;

import java.sql.*;

/**
 * Demonstrate how to create and access a SQL database.
 *
 * @author Marcus
 */
public class demoDatabase {
    String databaseName;
    ResultSet resultSet;
    ResultSetMetaData metaData;

    public demoDatabase(String dbName, boolean dropCurrent) {
        this.databaseName = dbName;

        String databaseURL = "jdbc:derby:" + this.databaseName + ";create=true";
        try (Connection connection = DriverManager.getConnection(databaseURL);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

//            this.resultSet = statement.executeQuery(action);
//            this.metaData = resultSet.getMetaData();
            System.out.println(">> Database Created.");
        } catch (SQLException e) {
            System.out.println("There was a problem accessing the database");
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        String databaseURL = "jdbc:derby:FirstDatabase;create=true";

        try (Connection connection = DriverManager.getConnection(databaseURL);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

//            Student Table
//            statement.execute(SqlStudent.dropTable());
//            statement.execute(SqlStudent.createTable());
//            statement.execute(SqlStudent.fillTable());
//            printTableData(statement, SqlStudent.selectData());

//            College Table
//            statement.execute(SqlCollege.dropTable());
//            statement.execute(SqlCollege.createTable());
//            statement.execute(SqlCollege.fillTable());
//            printTableData(statement, SqlCollege.selectData());

//            StudentCollege Table
//            statement.execute(SqlStudentCollege.dropTable());
//            statement.execute(SqlStudentCollege.createTable());
//            statement.execute(SqlStudentCollege.fillTable());
//            printTableData(statement, SqlStudentCollege.selectData());
//
//            printTableData(statement, SqlStudentCollege.studentCollegeInfo());

//            String[][] table = saveTableData(statement, SqlStudentCollege.studentCollegeInfo());
//            String[] data = new String[table[0].length];
//            for (int i = 0; i < data.length; i++) {
//                data[i] = table[0][i];
//            }

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

    public static String[][] saveTableData(Statement statement, String action) throws SQLException {
        ResultSet resultSet = statement.executeQuery(action);
        ResultSetMetaData meta = resultSet.getMetaData();
//        getting amount of rows.
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount++;
        }
        while (resultSet.previous()) {
            resultSet.previous();
        }

        String[][] resultList = new String[rowCount + 1][meta.getColumnCount()];
//        Save Header
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            resultList[0][i - 1] = meta.getColumnLabel((i));
        }

//        Save Data
        for (int i = 1; resultSet.next(); i++) {
            for (int j = 1; j <= meta.getColumnCount(); j++) {
                resultList[i][j - 1] = resultSet.getObject(j).toString();
            }
        }

        return resultList;
    }
}
