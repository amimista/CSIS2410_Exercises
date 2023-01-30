package sqlDerby;

/**
 * SQL Statements to change and update the StudentCollege Table
 *
 * @author Marcus
 */
public class SqlStudentCollege {
    public static String createTable() {
        return ("CREATE TABLE StudentCollege (" +
                "Student_ID int," +
                "College_ID int)"
        );
    }

    public static String fillTable() {
        return ("INSERT INTO StudentCollege "
                + "(Student_ID, College_ID) VALUES "
                + "(21, 124),"
                + "(21, 123),"
                + "(22, 126),"
                + "(22, 127),"
                + "(23, 125),"
                + "(24, 123),"
                + "(24, 125)"

        );
    }

    public static String dropTable() {
        return ("DROP TABLE StudentCollege");
    }

    public static String selectData() {
        return ("SELECT * FROM StudentCollege");
    }

    public static String studentCollegeInfo() {
        return ("SELECT s.First_Name, s.Last_Name, c.Name "
                        + "FROM Student s "
                        + "INNER JOIN StudentCollege sc ON s.Student_ID = sc.Student_ID "
                        + "INNER JOIN College c ON sc.College_ID = c.College_ID "
        );
    }
}
