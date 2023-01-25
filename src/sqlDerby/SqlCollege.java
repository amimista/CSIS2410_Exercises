package sqlDerby;

/**
 * SQL Statements to change and update the College Table
 * @author Marcus
 */
public class SqlCollege {
    public static String createTable() {
        return ("CREATE TABLE College (" +
                "College_ID int not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 123, INCREMENT BY 1)," +
                "Name varchar(255)," +
                "City varchar(255))");
    }

    public static String fillTable() {
        return ("INSERT INTO College "
                + "(Name, City) VALUES "
                + "('SLCC', 'SLC'), "
                + "('UofU', 'SLC'), "
                + "('Duke', 'Durham'), "
                + "('Yale', 'New Haven'), "
                + "('UCLA', 'LA')"

        );
    }

    public static String dropTable() {
        return ("DROP TABLE College");
    }

    public static String selectData() {
        return ("SELECT * FROM College");
    }
}
