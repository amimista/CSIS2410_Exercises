package sqlDerby;
/**
 * SQL Statements to change and update the Student Table
 * @author Marcus
 */
public class SqlStudent {
    public static String createTable() {
            return ("CREATE TABLE Student (" +
                    "Student_ID int not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 21, INCREMENT BY 1)," +
                    "Last_Name varchar(255)," +
                    "First_Name varchar(255)," +
                    "Major varchar(255)," +
                    "Grad_Year int)");
    }

    public static String fillTable() {
        return ("INSERT INTO Student "
                + "(Last_Name, First_Name, Major, Grad_Year) VALUES "
                + "('Moor', 'Don', 'EE', 2023), "
                + "('Bell', 'Jen', 'CS', 2022), "
                + "('Bush', 'Tim', 'FA', 2025), "
                + "('Cole', 'Rob', 'CS', 2024)"
        );
    }

    public static String dropTable() {
        return ("DROP TABLE Student");
    }

    public static String selectData() {
        return ("SELECT * FROM Student");
    }
}
