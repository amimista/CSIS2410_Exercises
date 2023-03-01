package a02;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class JDBCApplication {
    private JPanel rootPanel;
    private JSplitPane splitPane;
    private JTabbedPane tabbedPane;
    private JTable table1;
    private JPanel table1Tab;
    private JPanel table2Tab;
    private JTable table2;
    private JPanel table3Tab;
    private JTable table3;

//    User created fields

    private static String[][] fullData;
    private static Object[][] rowData;

    private static DefaultTableModel dataModel;

    private static JDBCApplication application;

    private static JSQLDatabase jsqlDatabase;

    public static void main(String[] args) {

        jsqlDatabase = new JSQLDatabase("pokemon");

//        try {
//            String[][] pokemon = JSQLDatabase.executeQuery("SELECT * FROM POKEMON");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        changeDataModel("SELECT * FROM POKEMON");

        application = new JDBCApplication();
        application.table1.setModel(dataModel);
        JFrame frame = new JFrame("JDBCApplication");
        frame.setContentPane(application.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void changeDataModel(String action) {
        try (Connection connection = DriverManager.getConnection(JSQLDatabase.getDbUrl());
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            fullData = JSQLDatabase.executeQuery(action);
            rowData = new Object[fullData.length - 1][fullData[0].length];

            for (int i = 1; i < fullData.length; i++) {
                System.arraycopy(fullData[i], 0, rowData[i - 1], 0, fullData[0].length);
            }


        } catch (SQLException e) {
            System.out.println("There was a problem accessing the database");
            e.printStackTrace();
        }

        dataModel = new DefaultTableModel(rowData, fullData[0]);

    }
}
