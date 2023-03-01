package a02;

import sqlDerby.SqlCollege;
import sqlDerby.SqlStudent;
import sqlDerby.SqlStudentCollege;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DatabaseGUI {
    private JPanel rootPanel;
    private JLabel windowLabel;
    private JTabbedPane mainTabbedPane;
    private JPanel table1Panel;
    private JScrollPane table1ScrollPane;
    private JTable table1;
    private JPanel table2Panel;
    private JScrollPane table2ScrollPane;
    private JTable table2;
    private JPanel table3Panel;
    private JScrollPane table3ScrollPane;
    private JTable table3;
    private JComboBox tableComboBox;
    private JLabel comboBoxLabel;
    private JRadioButton insertRadioButton;
    private JRadioButton dropRadioButton;
    private JRadioButton modifyRadioButton;
    private JPanel modifyPanel;
    private JComboBox modifyTargetComboBox;
    private JTextArea rowTextMod;
    private JPanel rowModsPanel;
    private JButton saveButton;

    //    Displaying Table data via 2d arrays
    private static String[][] fullData;
    private static Object[][] rowData;

    // Database Target
    private static final String DBURL = "jdbc:derby:FirstDatabase;create=true";

    private static DefaultTableModel dataModel;

    private static DatabaseGUI gui;


    public DatabaseGUI() {
        mainTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
//                System.out.println("Selected Tab: " + mainTabbedPane.getSelectedIndex());
                switch (mainTabbedPane.getSelectedIndex()) {
                    case 0:
                        changeDataModel(SqlStudent.selectData());
                        gui.table1.setModel(dataModel);
                        break;
                    case 1:
                        changeDataModel(SqlCollege.selectData());
                        gui.table2.setModel(dataModel);
                        break;
                    case 2:
                        changeDataModel(SqlStudentCollege.selectData());
                        gui.table3.setModel(dataModel);
                        break;
                    default:
                        System.out.println("There was an issue with getting what to do at tab index " + mainTabbedPane.getSelectedIndex() + ".");
                        break;
                }

            }
        });
        modifyTargetComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modifyTargetComboBox.getSelectedIndex() == 0) {
                    rowModsPanel.setVisible(true);
                }    // TODO: 2/26/2023

            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = gui.rowTextMod.getText();
                try (Connection connection = DriverManager.getConnection(DBURL);
                     Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                    statement.execute(command);

//                    Trigger change listener to update table.
                    int tabPlacement = gui.mainTabbedPane.getTabPlacement();
                    if (tabPlacement == 0) {
                        gui.mainTabbedPane.setTabPlacement(1);
                        gui.mainTabbedPane.setTabPlacement(tabPlacement);
                    } else {
                        gui.mainTabbedPane.setTabPlacement(0);
                        gui.mainTabbedPane.setTabPlacement(tabPlacement);
                    }
                } catch (SQLException sqlException) {
                    System.out.println("There was a problem accessing the database");
                    gui.rowTextMod.append("\n" + sqlException);
                }
            }
        });
    }

    public static void main(String[] args) {

        //          Change the look and feel of the app.
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Windows
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); // Cross-Platform
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

//        Change the model to what the first table should be
        changeDataModel(SqlStudent.selectData());

        gui = new DatabaseGUI();
        gui.table1.setModel(dataModel);
        JFrame frame = new JFrame("DatabaseGUI");
        frame.setContentPane(gui.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void changeDataModel(String action) {
        try (Connection connection = DriverManager.getConnection(DBURL);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

//            fullData = executeQuery(action);
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
