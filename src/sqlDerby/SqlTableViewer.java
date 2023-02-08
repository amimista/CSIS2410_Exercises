package sqlDerby;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;

public class SqlTableViewer {
//    Swing fields
    private JTable targetTable;
    private JLabel tableLabel;
    private JPanel rootPanel;
    private JScrollPane targetTableScrollPane;
    private JPanel filterPanel;
    private JLabel filterLabel;
    private JComboBox selectComboBox;
    private JComboBox tableComboBox;
    private JLabel selectLabel;
    private JLabel tableFilterLabel;
    private JTextField whereField;

    //    Displaying Table data via 2d arrays
    private static String[][] fullData;
    private static Object[][] rowData;

    public static void main(String[] args) {
//          Change the look and feel of the app.
        try {
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); // Cross-Platform
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Windows
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

//        Setup Precursor Data
        String databaseURL = "jdbc:derby:FirstDatabase;create=true";

        try (Connection connection = DriverManager.getConnection(databaseURL);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            fullData = saveTableData(statement, SqlStudentCollege.studentCollegeInfo());
            rowData = new Object[fullData.length - 1][fullData[0].length];

            for (int i = 1; i < fullData.length; i++) {
                for (int j = 0; j < fullData[0].length; j++) {
                    rowData[i - 1][j] = fullData[i][j];
                }
            }


        } catch (SQLException e) {
            System.out.println("There was a problem accessing the database");
            e.printStackTrace();
        }

//        Setup Data Structure for Table
        DefaultTableModel dataModel = new DefaultTableModel(rowData, fullData[0]);

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
