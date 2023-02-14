package sqlDerby;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentID;
    private String lastName;
    private String firstName;
    private String major;
    private String gradYear;

    public Student(String studentID, String lastName, String firstName, String major, String gradYear) {
        this.studentID = studentID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.major = major;
        this.gradYear = gradYear;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMajor() {
        return major;
    }

    public String getGradYear() {
        return gradYear;
    }

    public static List<Student> studentDataToList(ResultSet resultSet) throws SQLException {
        ResultSetMetaData meta = resultSet.getMetaData();
        List<Student> out = new ArrayList<>();

        String[] row = new String[meta.getColumnCount()];
        while(resultSet.next()) {
            for (int j = 1; j <= row.length; j++) {
                row[j - 1] = resultSet.getObject(j).toString();
            }
            out.add(new Student(row[0], row[1], row[2], row[3], row[4]));
//            System.out.println("added: " + Arrays.toString(row));
        }

        return out;
    }

    @Override
    public String toString() {
        return "#" + studentID + " " + firstName + " " + lastName + " " + major + " " + gradYear;
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:derby:FirstDatabase;create=true");
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet resultSet = statement.executeQuery(SqlStudent.selectData());
            List<Student> students = studentDataToList(resultSet);
//            LIST OF STUDENTS JDBC PART 4
            System.out.println("LIST OF STUDENTS:");
            System.out.println("------------------");
            System.out.println(students);
            System.out.println();

            //            College Print
            System.out.println("LIST OF Colleges:");
            System.out.println("------------------");
            demoDatabase.printTableData(statement, SqlCollege.selectData());

            //            StudentCollege Print
            System.out.println("LIST OF Student-College Relationship:");
            System.out.println("------------------");
            demoDatabase.printTableData(statement, SqlStudentCollege.selectData());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
