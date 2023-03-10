package a02;

import java.sql.*;

public class JSQLDatabase {
    public static String DATABASE_NAME;

    private static String DB_URL;

//    public JSQLDatabase(String db_url) {
//        DB_URL = db_url;
//    }

//    public static void createNatDexTable(boolean drop) {
//        try (Connection connection = DriverManager.getConnection(DB_URL);
//             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
//            if (drop)
//                statement.execute("TODO"); // TODO: 2/10/2023 -> drop table
//
//            statement.execute("TODO"); // TODO: 2/10/2023 -> create table
//            statement.execute("TODO"); // TODO: 2/10/2023 -> fill table
//        } catch (SQLException e) {
//            System.out.println("There was a problem creating NatDex table");
//            e.printStackTrace();
//        }
//    }
//
//    public static void createNextStageTable(boolean drop) {
//        try (Connection connection = DriverManager.getConnection(DB_URL);
//             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
//            if (drop)
//                statement.execute("TODO"); // TODO: 2/10/2023 -> drop table
//
//            statement.execute("TODO"); // TODO: 2/10/2023 -> create table
//            statement.execute("TODO"); // TODO: 2/10/2023 -> fill table
//        } catch (SQLException e) {
//            System.out.println("There was a problem creating NextStage table");
//            e.printStackTrace();
//        }
//    }
//
//    public static void createRegionTable(boolean drop) {
//        try (Connection connection = DriverManager.getConnection(DB_URL);
//             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
//            if (drop)
//                statement.execute("TODO"); // TODO: 2/10/2023 -> drop table
//
//            statement.execute("TODO"); // TODO: 2/10/2023 -> create table
//            statement.execute("TODO"); // TODO: 2/10/2023 -> fill table
//        } catch (SQLException e) {
//            System.out.println("There was a problem creating Region table");
//            e.printStackTrace();
//        }
//    }
//
//    public static void createAllTables(boolean drop) {
//        createNatDexTable(drop);
//        createNextStageTable(drop);
//        createRegionTable(drop);
//
//    }

    public JSQLDatabase(String dbName) {
        DATABASE_NAME = dbName;
        DB_URL = "jdbc:derby:"+ dbName +";create=true";
    }

    public static String[][] executeQuery(String action) throws SQLException {
        return getStrings(action, DB_URL);
    }

    static String[][] getStrings(String action, String dbUrl) {
        String[][] resultList = new String[0][];

        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

//            Setup where to get results
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

            resultList = new String[rowCount + 1][meta.getColumnCount()];
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

        } catch (SQLException e) {
            System.out.println("There was a problem creating Student table");
            e.printStackTrace();
        }


        return resultList;
    }

    public static int getColumnCount(String table) {
        int out = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

//            Make the action for execute query because this is only parametrized for a table.
            String action = "SELECT * FROM " + table + ";";

//            Setup where to get results
            ResultSet resultSet = statement.executeQuery(action);
            ResultSetMetaData meta = resultSet.getMetaData();

            out = meta.getColumnCount();

        } catch (SQLException e) {
            System.out.println("There was a problem creating Student table");
            e.printStackTrace();
        }

        return out;
    }

    public static String getDbUrl() {
        return DB_URL;
    }
}
