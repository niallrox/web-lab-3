package Database;

import Foundation.Point;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

@Named("Database")
@SessionScoped
public class Database implements Serializable {
    private Connection connect;
    private PreparedStatement ps;

    /**
     * Подключается к базе данных
     *
     * @param file
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public synchronized void connectSQL(String file) throws IOException, ClassNotFoundException, SQLException {
        FileInputStream bd = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(bd);
        String url = properties.getProperty("location");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        Class.forName("org.postgresql.Driver");
        connect = DriverManager.getConnection(url, login, password);
    }

    /**
     * возвращает коллекцию результатов принадлежащих этой сессии
     *
     * @return
     * @throws SQLException
     * @throws NullPointerException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public  LinkedList<Point> loadFromSQL(String session) throws SQLException, NullPointerException, IOException, ClassNotFoundException {
        LinkedList<Point> col = new LinkedList<>();
        ps = connect.prepareStatement("SELECT * FROM data WHERE session = ?;");
        ps.setString(1, session);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            Point point = new Point();
            point.setX(res.getDouble("x"));
            point.setY(String.valueOf(res.getDouble("y")));
            point.setR(res.getDouble("r"));
            point.setResult(res.getString("result"));
            point.setTime(res.getString("time"));
            col.add(point);
        }
        return col;
    }

    /**
     * добавляет элемент в бд
     *
     * @param data
     * @throws SQLException
     * @throws NullPointerException
     */
    public void addToSQL(Point data) throws SQLException {
        ps = connect.prepareStatement("INSERT INTO data (x, y, r, " +
                "result, time, session) " +
                " VALUES ( ?, ?, ?, ?, ?, ?);");
        ps.setDouble(1, data.getX());
        ps.setDouble(2, Double.parseDouble(data.getY()));
        ps.setDouble(3, data.getR());
        ps.setString(4, data.getResult());
        ps.setString(5, data.getTime());
        ps.setString(6, data.getSession());
        ps.execute();
    }

    /**
     * очищаются элемент принадлижащие этой сессии
     *
     * @param session
     * @throws SQLException
     */
    public void clearSQL(String session) throws SQLException {
        ps = connect.prepareStatement("DELETE FROM data WHERE session = ?;");
        ps.setString(1, session);
        ps.execute();
    }
}