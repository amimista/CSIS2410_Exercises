package sqlDerby;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.sql.*;

public class SqlTableViewer {
    private JTable targetTable;
    private JLabel tableLabel;
    private JPanel rootPanel;
    private JScrollPane targetTableScrollPane;

//    Displaying Table data via 2d arrays
    private static String[][] fullData;
    private static Object[][] rowData;

    public static void main(String[] args) {
//        Setup Precursor Data
        String databaseURL = "jdbc:derby:FirstDatabase;create=true";

        try (Connection connection = DriverManager.getConnection(databaseURL);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            fullData = saveTableData(statement, SqlStudentCollege.studentCollegeInfo());


        } catch (SQLException e) {
            System.out.println("There was a problem accessing the database");
            e.printStackTrace();
        }

//        Setup Data Structure for Table
        TableModel dataModel = new
                AbstractTableModel() {
                    public int getColumnCount() {
                        return fullData[0].length;
                    }

                    public int getRowCount() {
                        return fullData.length-2;
                    }

                    public Object getValueAt(int row, int col) {
                        return rowData[row][col];
                    }
                };

//        Setup GUI
        SqlTableViewer viewer = new SqlTableViewer();
        viewer.targetTable.setModel(dataModel);
        JFrame frame = new JFrame("SqlTableViewer");
        frame.setContentPane(viewer.rootPanel);
        viewer.targetTable.setFillsViewportHeight(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
