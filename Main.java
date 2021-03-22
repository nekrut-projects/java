package lesson2;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement statement;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:accessList.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connect();

        String createTable = String.format(
                "CREATE TABLE IF NOT EXISTS %s " +
                "(%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "  %s TEXT NOT NULL," +
                "  %s TEXT NOT NULL," +
                "  %s TEXT NOT NULL" +
                ");", "users", "id", "name", "nickname", "accessKey");

        try {
            statement.executeUpdate(createTable);
            String str1 = "INSERT INTO users (name, nickname, accessKey) VALUES ('User1', 'bob1', 'kj2ghhfo')";
            String str2 = "INSERT INTO users (name, nickname, accessKey) VALUES ('User2', 'bob2', 'jghhjk8')";

            statement.executeUpdate(str1);
            statement.executeUpdate(str2);

            ResultSet resultSet = readFieldTableFromDB("users", "*");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString("name") + " "
                        + resultSet.getString("nickname") + " " + resultSet.getString("accessKey"));
            }

            String str3 = "DELETE FROM users WHERE name = 'User2'";
            statement.executeUpdate(str3);

            String str4 = "DELETE FROM users WHERE '*'";
            statement.executeUpdate(str4);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static ResultSet readFieldTableFromDB(String nameTable, String field) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(String.format("SELECT %s FROM %s;", field, nameTable));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

}
